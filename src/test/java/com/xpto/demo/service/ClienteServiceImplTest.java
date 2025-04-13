package com.xpto.demo.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.xpto.demo.dto.Cliente;
import com.xpto.demo.dto.CreateClientePessoaFisicaDTO;
import com.xpto.demo.dto.MovimentacaoDTO;
import com.xpto.demo.dto.PageCliente;
import com.xpto.demo.dto.UpdateCliente;
import com.xpto.demo.entity.ClienteDomainEntity;
import com.xpto.demo.entity.ClientePessoaFisicaDomainEntity;
import com.xpto.demo.entity.ContaDomainEntity;
import com.xpto.demo.mapper.ClienteMapper;
import com.xpto.demo.repository.ClienteRepository;
import com.xpto.demo.service.utils.CreateUtils;

public class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @Mock
    private MovimentacaoService movimentacaoService;

    @Mock
    private ContaService contaService;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateClientePessoaFisica() {
        CreateClientePessoaFisicaDTO dto = CreateUtils.createCreateClientePessoaFIsicaDTO();
        ClientePessoaFisicaDomainEntity clientePessoaFisicaDomainEntity = CreateUtils.createClientePessoaFisicaEntity();
        Cliente cliente = CreateUtils.criarClientePessoaFisica();
        ContaDomainEntity contaDomainEntity = CreateUtils.criarContaDomainEntity();
        MovimentacaoDTO movimentacaoDTO = CreateUtils.criarMovimentacaoDTO();
                
        when(clienteMapper.toEntity(eq(dto))).thenReturn(clientePessoaFisicaDomainEntity);
        when(clienteRepository.save(any())).thenReturn(clientePessoaFisicaDomainEntity);
        when(contaService.createContaInicial(eq(clientePessoaFisicaDomainEntity), eq(dto.getAgencia()))).thenReturn(contaDomainEntity);
        when(movimentacaoService.createMovimentacaoInicial(eq(contaDomainEntity))).thenReturn(movimentacaoDTO);
        when(clienteMapper.toModel((ClienteDomainEntity) any())).thenReturn(cliente);

        Cliente result = clienteService.create(dto);

        assertEquals(clientePessoaFisicaDomainEntity.getNome(), result.getNome());
        verify(clienteRepository, times(1)).save(any(ClienteDomainEntity.class));
        verify(contaService, times(1)).createContaInicial(any(), eq(dto.getAgencia()));
        verify(movimentacaoService, times(1)).createMovimentacaoInicial(any());
    }
    
    @Test
    void testReadClientePessoaFisica() {
        ClientePessoaFisicaDomainEntity entity = CreateUtils.createClientePessoaFisicaEntity();
        Cliente cliente = CreateUtils.criarClientePessoaFisica();

        when(clienteRepository.findByUuid(eq(entity.getUuid()))).thenReturn(Optional.of(entity));
        when(clienteMapper.toModel(eq(entity))).thenReturn(cliente);

        Cliente result = clienteService.read(entity.getUuid());

        assertNotNull(result);
        assertEquals(cliente.getNome(), result.getNome());
        verify(clienteRepository).findByUuid(entity.getUuid());
    }
    
    @Test
    void testUpdateClientePessoaFisica() {
        UUID uuid = UUID.randomUUID();
        ClientePessoaFisicaDomainEntity entity = CreateUtils.createClientePessoaFisicaEntity();
        UpdateCliente updateCliente = CreateUtils.createUpdateCliente();
        Cliente clienteAtualizado = CreateUtils.criarClientePessoaFisica();

        when(clienteRepository.findByUuid(uuid)).thenReturn(Optional.of(entity));
        doNothing().when(clienteMapper).intoEntity(updateCliente, entity);
        when(clienteMapper.toModel(entity)).thenReturn(clienteAtualizado);

        Cliente result = clienteService.update(uuid, updateCliente);

        assertNotNull(result);
        assertEquals(clienteAtualizado.getNome(), result.getNome());
        verify(clienteRepository).flush();
    }
    
    @Test
    void testDeleteCliente() {
        ClientePessoaFisicaDomainEntity entity = CreateUtils.createClientePessoaFisicaEntity();

        when(clienteRepository.findByUuid(entity.getUuid())).thenReturn(Optional.of(entity));
        when(clienteRepository.save(any())).thenReturn(entity);

        clienteService.delete(entity.getUuid());

        verify(clienteRepository).save(entity);
    }
    
    @Test
    void testListClientes() {
        List<ClienteDomainEntity> entities = List.of(CreateUtils.createClientePessoaFisicaEntity());
        Page<ClienteDomainEntity> page = new PageImpl<>(entities);
        Cliente cliente = CreateUtils.criarClientePessoaFisica();
        Page<Cliente> pageCliente = new PageImpl<>(List.of(cliente));

        PageCliente pageClienteMock = mock(PageCliente.class);
        when(pageClienteMock.getTotalElements()).thenReturn(1L);

        when(clienteRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(clienteMapper.toModel(any(ClienteDomainEntity.class))).thenReturn(cliente);
        when(clienteMapper.toPageModel(any(Page.class))).thenReturn(pageClienteMock);

        PageCliente result = clienteService.list(0, 10, List.of());

        assertNotNull(result);
        assertEquals(1L, result.getTotalElements());
        verify(clienteRepository).findAll(any(Pageable.class));
    }



}
