package com.xpto.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xpto.demo.dto.CreateEndereco;
import com.xpto.demo.dto.EnderecoDTO;
import com.xpto.demo.dto.PageEnderecoDTO;
import com.xpto.demo.entity.EnderecoDomainEntity;
import com.xpto.demo.mapper.EnderecoMapper;
import com.xpto.demo.repository.ClienteRepository;
import com.xpto.demo.repository.EnderecoRepository;
import com.xpto.demo.service.exception.ModelException;

@Service
@Transactional
public class EnderecoServiceImpl implements EnderecoService {

	private EnderecoRepository enderecoRepository;
	private ClienteRepository clienteRepository;

	private EnderecoMapper enderecoMapper;

	public EnderecoServiceImpl(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository,
			EnderecoMapper enderecoMapper) {
		this.clienteRepository = clienteRepository;
		this.enderecoRepository = enderecoRepository;
		this.enderecoMapper = enderecoMapper;
	}

	@Transactional
	@Override
	public EnderecoDTO create(CreateEndereco createEndereco) {
		var cliente = clienteRepository.findByUuid(createEndereco.getUuidCliente())
				.orElseThrow(() -> new ModelException(ClienteService.NOT_FOUND));

		if (cliente.getEndereco() != null) {
			var enderecoAntigo = cliente.getEndereco();

			cliente.setEndereco(null);
			clienteRepository.save(cliente);

			enderecoRepository.delete(enderecoAntigo);
			enderecoRepository.flush();
		}

		var enderecoEntity = enderecoMapper.toEntity(createEndereco);
		enderecoEntity.setCliente(cliente);

		enderecoEntity = enderecoRepository.save(enderecoEntity);

		cliente.setEndereco(enderecoEntity);
		clienteRepository.save(cliente);

		return enderecoMapper.toModel(enderecoEntity);
	}

	@Override
	public EnderecoDTO read(UUID uuid) {
		var enderecoEntity = enderecoRepository.findByUuid(uuid)
				.orElseThrow(() -> new ModelException(EnderecoService.NOT_FOUND));

		return enderecoMapper.toModel(enderecoEntity);
	}

	@Override
	public void delete(UUID uuid) {
		enderecoRepository.findByUuid(uuid).orElseThrow(() -> new ModelException(NOT_FOUND));

		enderecoRepository.deleteByUuid(uuid);

	}

	@Override
	public PageEnderecoDTO list(Integer page, Integer size, List<String> sortParams) {
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
		Page<EnderecoDomainEntity> pageResult = enderecoRepository.findAll(pageable);

		Page<EnderecoDTO> clientesPage = pageResult.map(enderecoMapper::toModel);

		return enderecoMapper.toPageModel(clientesPage);
	}

	@Override
	public List<EnderecoDTO> getAllEnderecosByCliente(UUID uuidCliente) {
		var enderecos = enderecoRepository.findAllEnderecoByCliente(uuidCliente);

		return enderecos;
	}

}
