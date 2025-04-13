package com.xpto.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.xpto.demo.dto.CreateMovimentacaoDTO;
import com.xpto.demo.dto.MovimentacaoDTO;
import com.xpto.demo.dto.MovivemtosByClienteDTO;
import com.xpto.demo.dto.PageMovimentacaoDTO;
import com.xpto.demo.dto.UpdateMovimentacao;
import com.xpto.demo.entity.ContaDomainEntity;
import com.xpto.demo.service.error.Error;

public interface MovimentacaoService {
	
	final Error NOT_FOUND =
		      new Error().code(HttpStatus.NOT_FOUND.value()).message("Movimentacao não encontrada");
	
	  public  MovimentacaoDTO create(CreateMovimentacaoDTO createMovimentacao);

	  public MovimentacaoDTO read(UUID id);

	  public MovimentacaoDTO update(UUID id, UpdateMovimentacao updateMovimentacao);

	  public void delete(UUID id);

	  public PageMovimentacaoDTO list(Integer page, Integer size, List<String> sort);
	  
	 public MovimentacaoDTO createMovimentacaoInicial(ContaDomainEntity conta);

	public List<MovivemtosByClienteDTO> getAllMovimentacoesByCliente(UUID uuidCliente);

}
