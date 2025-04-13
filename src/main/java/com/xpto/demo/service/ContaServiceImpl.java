package com.xpto.demo.service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xpto.demo.dto.ContaDTO;
import com.xpto.demo.dto.CreateContaDTO;
import com.xpto.demo.dto.PageConta;
import com.xpto.demo.dto.UpdateConta;
import com.xpto.demo.entity.ClienteDomainEntity;
import com.xpto.demo.entity.ContaDomainEntity;
import com.xpto.demo.mapper.ContaMapper;
import com.xpto.demo.repository.ClienteRepository;
import com.xpto.demo.repository.ContaRepository;
import com.xpto.demo.service.exception.ModelException;

@Service
@Transactional
public class ContaServiceImpl implements ContaService {

	private ContaRepository contaRepository;
	private ClienteRepository clienteRepository;

	private ContaMapper contaMapper;

	public ContaServiceImpl(ClienteRepository clienteRepository, ContaRepository contaRepository,
			ContaMapper contaMapper) {
		this.clienteRepository = clienteRepository;
		this.contaRepository = contaRepository;
		this.contaMapper = contaMapper;
	}

	@Override
	public ContaDTO create(CreateContaDTO createConta) {

		var cliente = clienteRepository.findByUuid(createConta.getUuidCliente())
				.orElseThrow(() -> new ModelException(ClienteService.NOT_FOUND));

		var contaEntity = contaMapper.toEntity(createConta);
		contaEntity.setCliente(cliente);
		contaEntity = contaRepository.save(contaEntity);
		return contaMapper.toModel(contaEntity);
	}

	@Override
	public ContaDTO read(UUID uuid) {
		var contaEntity = contaRepository.findByUuid(uuid)
				.orElseThrow(() -> new ModelException(ContaService.NOT_FOUND));

		return contaMapper.toModel(contaEntity);
	}

	@Override
	public ContaDTO update(UUID uuid, UpdateConta updateConta) {
		var contaEntity = contaRepository.findByUuid(uuid)
				.orElseThrow(() -> new ModelException(ContaService.NOT_FOUND));

		contaMapper.intoEntity(updateConta, contaEntity);
		contaRepository.flush();

		return contaMapper.toModel(contaEntity);
	}

	@Override
	public void delete(UUID uuid) {
		var conta = contaRepository.findByUuid(uuid).orElseThrow(() -> new ModelException(ContaService.NOT_FOUND));

		conta.excluir();

		contaRepository.save(conta);

	}

	@Override
	public PageConta list(Integer page, Integer size, List<String> sortParams) {
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
		Page<ContaDomainEntity> pageResult = contaRepository.findAll(pageable);

		Page<ContaDTO> clientesPage = pageResult.map(contaMapper::toModel);

		return contaMapper.toPageModel(clientesPage);
	}

	@Override
	public ContaDomainEntity createContaInicial(ClienteDomainEntity clienteEntity, String agencia) {

		ContaDomainEntity conta = new ContaDomainEntity();
		conta.setCliente(clienteEntity);
		conta.setUuid(UUID.randomUUID());
		conta.setAgencia(agencia);
		conta.setNumero(generateNumeroConta());
		return contaRepository.save(conta);
	}

	private String generateNumeroConta() {
		Random random = new Random();

		int parte1 = random.nextInt(10000);
		int digito = random.nextInt(10);

		return String.format("%04d-%d", parte1, digito);
	}

}
