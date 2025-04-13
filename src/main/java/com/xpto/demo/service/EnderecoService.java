package com.xpto.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.xpto.demo.dto.CreateEndereco;
import com.xpto.demo.dto.EnderecoDTO;
import com.xpto.demo.dto.PageEnderecoDTO;
import com.xpto.demo.service.error.Error;

public interface EnderecoService {

	final Error NOT_FOUND = new Error().code(HttpStatus.NOT_FOUND.value()).message("Endereco não encontrado");

	public EnderecoDTO create(CreateEndereco createEndereco);

	public EnderecoDTO read(UUID uuid);

	public void delete(UUID uuid);

	public PageEnderecoDTO list(Integer page, Integer size, List<String> sortParams);

	public List<EnderecoDTO> getAllEnderecosByCliente(UUID uuidCliente);

}
