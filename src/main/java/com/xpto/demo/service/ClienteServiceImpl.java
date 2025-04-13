package com.xpto.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xpto.demo.dto.Cliente;
import com.xpto.demo.dto.CreateCliente;
import com.xpto.demo.dto.CreateClientePessoaFisicaDTO;
import com.xpto.demo.dto.CreateClientePessoaJuridicaDTO;
import com.xpto.demo.dto.PageCliente;
import com.xpto.demo.dto.UpdateCliente;
import com.xpto.demo.entity.ClienteDomainEntity;
import com.xpto.demo.entity.ClientePessoaFisicaDomainEntity;
import com.xpto.demo.entity.ClientePessoaJuridicaDomainEntity;
import com.xpto.demo.mapper.ClienteMapper;
import com.xpto.demo.repository.ClienteRepository;
import com.xpto.demo.service.exception.ModelException;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

	private ClienteRepository clienteRepository;
	private MovimentacaoService movimentacaoService;
	private ContaService contaService;
	private EnderecoService enderecoService;

	private ClienteMapper clienteMapper;

	public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper,
			MovimentacaoService movimentacaoService, ContaService contaService) {
		this.clienteRepository = clienteRepository;
		this.clienteMapper = clienteMapper;
		this.movimentacaoService = movimentacaoService;
		this.contaService = contaService;
	}

	@Override
	public Cliente create(CreateCliente createCliente) {
		ClienteDomainEntity clienteEntity = null;

		if (createCliente instanceof CreateClientePessoaFisicaDTO dto) {
			clienteEntity = clienteMapper.toEntity(dto);
		} else if (createCliente instanceof CreateClientePessoaJuridicaDTO dto) {
			clienteEntity = clienteMapper.toEntity(dto);

		}
		clienteEntity.setDataCadastro(LocalDate.now());
		clienteEntity = clienteRepository.save(clienteEntity);

		var contaEntity = contaService.createContaInicial(clienteEntity, createCliente.getAgencia());
		movimentacaoService.createMovimentacaoInicial(contaEntity);

		return clienteMapper.toModel(clienteEntity);

	}

	@Override
	public Cliente read(UUID uuid) {
		var clienteEntity = clienteRepository.findByUuid(uuid)
				.orElseThrow(() -> new ModelException(ClienteService.NOT_FOUND));

		if (clienteEntity instanceof ClientePessoaFisicaDomainEntity pf) {
			return clienteMapper.toModel(pf);
		} else if (clienteEntity instanceof ClientePessoaJuridicaDomainEntity pj) {
			return clienteMapper.toModel(pj);
		} else {
			throw new IllegalArgumentException("Tipo desconhecido de cliente: " + clienteEntity.getClass());
		}
	}

	@Override
	public Cliente update(UUID uuid, UpdateCliente updateCliente) {
		var clienteEntity = clienteRepository.findByUuid(uuid)
				.orElseThrow(() -> new ModelException(ClienteService.NOT_FOUND));

		clienteMapper.intoEntity(updateCliente, clienteEntity);
		clienteRepository.flush();

		if (clienteEntity instanceof ClientePessoaFisicaDomainEntity pf) {
			return clienteMapper.toModel(pf);
		} else if (clienteEntity instanceof ClientePessoaJuridicaDomainEntity pj) {
			return clienteMapper.toModel(pj);
		} else {
			throw new IllegalArgumentException("Tipo desconhecido de cliente: " + clienteEntity.getClass());
		}
	}

	@Override
	public void delete(UUID uuid) {
		var clienteEntity = clienteRepository.findByUuid(uuid)
				.orElseThrow(() -> new ModelException(ClienteService.NOT_FOUND));

		clienteEntity.excluir();

		clienteRepository.save(clienteEntity);

	}

	@Override
	public PageCliente list(Integer page, Integer size, List<String> sortParams) {
		if (page == null)
			page = 0;
		if (size == null)
			size = 10;

		Sort sort = Sort.unsorted();

		if (sortParams != null && !sortParams.isEmpty()) {
			List<Sort.Order> orders = sortParams.stream().map(param -> {
				if (param.startsWith("-")) {
					return new Sort.Order(Sort.Direction.DESC, param.substring(1));
				} else {
					return new Sort.Order(Sort.Direction.ASC, param);
				}
			}).toList();

			sort = Sort.by(orders);
		}

		Pageable pageable = PageRequest.of(page, size, sort);
		Page<ClienteDomainEntity> pageResult = clienteRepository.findAll(pageable);

		Page<Cliente> clientesPage = pageResult.map(clienteMapper::toModel);

		return clienteMapper.toPageModel(clientesPage);
	}

}
