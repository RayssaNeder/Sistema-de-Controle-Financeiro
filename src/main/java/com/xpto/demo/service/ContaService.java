package com.xpto.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.xpto.demo.dto.ContaDTO;
import com.xpto.demo.dto.CreateContaDTO;
import com.xpto.demo.dto.PageConta;
import com.xpto.demo.dto.UpdateConta;
import com.xpto.demo.entity.ClienteDomainEntity;
import com.xpto.demo.entity.ContaDomainEntity;
import com.xpto.demo.service.error.Error;

public interface ContaService {

	final Error NOT_FOUND = new Error().code(HttpStatus.NOT_FOUND.value()).message("Conta não encontrada");

	public ContaDTO create(CreateContaDTO createConta);

	public ContaDTO read(UUID uuid);

	public ContaDTO update(UUID uuid, UpdateConta updateConta);

	public void delete(UUID uuid);

	public PageConta list(Integer page, Integer size, List<String> sortParams);

	public ContaDomainEntity createContaInicial(ClienteDomainEntity clienteEntity, String agencia);

}
