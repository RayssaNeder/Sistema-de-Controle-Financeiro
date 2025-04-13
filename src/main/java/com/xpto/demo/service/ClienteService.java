package com.xpto.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.xpto.demo.dto.Cliente;
import com.xpto.demo.dto.CreateCliente;
import com.xpto.demo.dto.CreateClientePessoaFisicaDTO;
import com.xpto.demo.dto.PageCliente;
import com.xpto.demo.dto.UpdateCliente;
import com.xpto.demo.service.error.Error;

public interface ClienteService {
	
	final Error NOT_FOUND =
		      new Error().code(HttpStatus.NOT_FOUND.value()).message("Cliente não encontrado");
	
	  public Cliente create(CreateCliente createcliente);

	  public Cliente read(UUID id);

	  public Cliente update(UUID id, UpdateCliente updateCliente);

	  public void delete(UUID id);

	  public PageCliente list(Integer page, Integer size, List<String> sort);

}
