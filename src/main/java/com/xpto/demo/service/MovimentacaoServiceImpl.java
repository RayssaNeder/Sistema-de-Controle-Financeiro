package com.xpto.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xpto.demo.dto.CreateMovimentacaoDTO;
import com.xpto.demo.dto.MovimentacaoDTO;
import com.xpto.demo.dto.MovivemtosByClienteDTO;
import com.xpto.demo.dto.PageMovimentacaoDTO;
import com.xpto.demo.dto.UpdateMovimentacao;
import com.xpto.demo.entity.ContaDomainEntity;
import com.xpto.demo.entity.MovimentacaoDomainEntity;
import com.xpto.demo.enums.TipoMovimentacao;
import com.xpto.demo.mapper.MovimentacaoMapper;
import com.xpto.demo.repository.ContaRepository;
import com.xpto.demo.repository.MovimentacaoRepository;
import com.xpto.demo.service.exception.ModelException;

@Service
@Transactional
public class MovimentacaoServiceImpl implements MovimentacaoService {

	private MovimentacaoRepository movimentacaoRepository;
	private ContaRepository contaRepository;

	private MovimentacaoMapper movimentacaoMapper;

	public MovimentacaoServiceImpl(MovimentacaoRepository movimentacaoRepository, MovimentacaoMapper movimentacaoMapper,
			ContaRepository contaRepository) {
		this.movimentacaoRepository = movimentacaoRepository;
		this.movimentacaoMapper = movimentacaoMapper;
		this.contaRepository = contaRepository;
	}

	@Override
	public MovimentacaoDTO create(CreateMovimentacaoDTO createMovimentacao) {

		var conta = contaRepository.findByUuid(createMovimentacao.getUuidConta())
				.orElseThrow(() -> new ModelException(ContaService.NOT_FOUND));

		MovimentacaoDomainEntity movimentacaoEntity = null;

		movimentacaoEntity = movimentacaoMapper.toEntity(createMovimentacao);
		movimentacaoEntity.setConta(conta);
		movimentacaoEntity.setData(LocalDate.now());
		movimentacaoEntity = movimentacaoRepository.save(movimentacaoEntity);

		return movimentacaoMapper.toModel(movimentacaoEntity);

	}

	@Override
	public MovimentacaoDTO read(UUID uuid) {
		var movimentacaoEntity = movimentacaoRepository.findByUuid(uuid)
				.orElseThrow(() -> new ModelException(MovimentacaoService.NOT_FOUND));

		return movimentacaoMapper.toModel(movimentacaoEntity);

	}

	@Override
	public MovimentacaoDTO update(UUID uuid, UpdateMovimentacao updateMovimentacao) {
		var movimentacaoEntity = movimentacaoRepository.findByUuid(uuid)
				.orElseThrow(() -> new ModelException(MovimentacaoService.NOT_FOUND));

		movimentacaoMapper.intoEntity(updateMovimentacao, movimentacaoEntity);
		movimentacaoRepository.flush();

		return movimentacaoMapper.toModel(movimentacaoEntity);

	}

	@Override
	public void delete(UUID uuid) {
		var movimentacao = movimentacaoRepository.findByUuid(uuid)
				.orElseThrow(() -> new ModelException(MovimentacaoService.NOT_FOUND));

		movimentacao.excluir();

		movimentacaoRepository.save(movimentacao);

	}

	@Override
	public PageMovimentacaoDTO list(Integer page, Integer size, List<String> sortParams) {
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
		Page<MovimentacaoDomainEntity> pageResult = movimentacaoRepository.findAll(pageable);

		Page<MovimentacaoDTO> movimentacaosPage = pageResult.map(movimentacaoMapper::toModel);

		return movimentacaoMapper.toPageModel(movimentacaosPage);
	}

	@Override
	public MovimentacaoDTO createMovimentacaoInicial(ContaDomainEntity conta) {

		MovimentacaoDomainEntity movimentacaoInicial = new MovimentacaoDomainEntity();
		movimentacaoInicial.setConta(conta);
		movimentacaoInicial.setTipo(TipoMovimentacao.CREDITO);
		movimentacaoInicial.setValor(BigDecimal.ZERO);
		movimentacaoInicial.setDescricao("Movimentação inicial automática");
		movimentacaoInicial.setData(LocalDate.now());

		movimentacaoRepository.save(movimentacaoInicial);

		return movimentacaoMapper.toModel(movimentacaoInicial);

	}

	@Override
	public List<MovivemtosByClienteDTO> getAllMovimentacoesByCliente(UUID uuidCliente) {

		var movimentacoes = movimentacaoRepository.findAllMovimentacaoByCliente(uuidCliente);

		return movimentacoes;
	}

}
