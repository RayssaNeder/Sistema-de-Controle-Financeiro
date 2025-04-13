package com.xpto.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
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

import com.xpto.demo.dto.ContaDTO;
import com.xpto.demo.dto.CreateContaDTO;
import com.xpto.demo.dto.PageConta;
import com.xpto.demo.dto.UpdateConta;
import com.xpto.demo.entity.ClienteDomainEntity;
import com.xpto.demo.entity.ClientePessoaFisicaDomainEntity;
import com.xpto.demo.entity.ContaDomainEntity;
import com.xpto.demo.mapper.ContaMapper;
import com.xpto.demo.repository.ClienteRepository;
import com.xpto.demo.repository.ContaRepository;

class ContaServiceImplTest {

	@InjectMocks
	private ContaServiceImpl contaService;

	@Mock
	private ContaRepository contaRepository;

	@Mock
	private ClienteRepository clienteRepository;

	@Mock
	private ContaMapper contaMapper;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreate() {
		CreateContaDTO dto = new CreateContaDTO();
		UUID uuid = UUID.randomUUID();
		dto.setUuidCliente(uuid);

		ClienteDomainEntity cliente = new ClientePessoaFisicaDomainEntity();
		ContaDomainEntity conta = new ContaDomainEntity();
		ContaDTO contaDTO = new ContaDTO();

		when(clienteRepository.findByUuid(uuid)).thenReturn(Optional.of(cliente));
		when(contaMapper.toEntity(dto)).thenReturn(conta);
		when(contaRepository.save(conta)).thenReturn(conta);
		when(contaMapper.toModel(conta)).thenReturn(contaDTO);

		ContaDTO result = contaService.create(dto);

		assertNotNull(result);
		verify(contaRepository).save(conta);
	}

	@Test
	void testRead() {
		UUID uuid = UUID.randomUUID();
		ContaDomainEntity conta = new ContaDomainEntity();
		ContaDTO contaDTO = new ContaDTO();

		when(contaRepository.findByUuid(uuid)).thenReturn(Optional.of(conta));
		when(contaMapper.toModel(conta)).thenReturn(contaDTO);

		ContaDTO result = contaService.read(uuid);

		assertEquals(contaDTO, result);
	}

	@Test
	void testUpdate() {
		UUID uuid = UUID.randomUUID();
		ContaDomainEntity conta = new ContaDomainEntity();
		UpdateConta update = new UpdateConta();
		ContaDTO contaDTO = new ContaDTO();

		when(contaRepository.findByUuid(uuid)).thenReturn(Optional.of(conta));
		doNothing().when(contaMapper).intoEntity(update, conta);
		when(contaMapper.toModel(conta)).thenReturn(contaDTO);

		ContaDTO result = contaService.update(uuid, update);

		assertEquals(contaDTO, result);
		verify(contaRepository).flush();
	}

	@Test
	void testDelete() {
		UUID uuid = UUID.randomUUID();
		ContaDomainEntity conta = mock(ContaDomainEntity.class);

		when(contaRepository.findByUuid(uuid)).thenReturn(Optional.of(conta));

		contaService.delete(uuid);

		verify(conta).excluir();
		verify(contaRepository).save(conta);
	}

	@Test
	void testList() {
		ContaDomainEntity contaEntity = new ContaDomainEntity();
		ContaDTO contaDTO = new ContaDTO();
		Page<ContaDomainEntity> page = new PageImpl<>(List.of(contaEntity));
		Page<ContaDTO> dtoPage = new PageImpl<>(List.of(contaDTO));

		PageConta pageConta = mock(PageConta.class);
		when(pageConta.getTotalElements()).thenReturn(1L);

		when(contaRepository.findAll(any(Pageable.class))).thenReturn(page);
		when(contaMapper.toModel(contaEntity)).thenReturn(contaDTO);
		when(contaMapper.toPageModel(any(Page.class))).thenReturn(pageConta);

		PageConta result = contaService.list(0, 10, List.of());

		assertEquals(1, result.getTotalElements());
		verify(contaRepository).findAll(any(Pageable.class));
	}

	@Test
	void testCreateContaInicial() {
		ClienteDomainEntity cliente = new ClientePessoaFisicaDomainEntity();
		ContaDomainEntity conta = new ContaDomainEntity();
		conta.setCliente(cliente);
		conta.setAgencia("1234");
		conta.setNumero("0001-1");

		when(contaRepository.save(any(ContaDomainEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

		ContaDomainEntity result = contaService.createContaInicial(cliente, "1234");

		assertNotNull(result);
		assertEquals("1234", result.getAgencia());
		assertEquals(cliente, result.getCliente());
		verify(contaRepository).save(any());
	}
}
