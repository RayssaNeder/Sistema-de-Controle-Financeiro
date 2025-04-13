package com.xpto.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xpto.demo.dto.RelatorioReceitaDTO;
import com.xpto.demo.dto.RelatorioSaldoClienteDTO;
import com.xpto.demo.dto.RelatorioSaldoGeralDTO;
import com.xpto.demo.repository.RelatorioRepository;



@Service
@Transactional
public class RelatorioServiceImpl implements RelatorioService {
	
	  private RelatorioRepository relatorioRepository;

	  
	  public RelatorioServiceImpl(RelatorioRepository relatorioRepository) {
		    this.relatorioRepository = relatorioRepository;
		  }

	@Override
	public RelatorioSaldoClienteDTO gerarRelatorioSaldoCliente(UUID uuidCliente) {
		RelatorioSaldoClienteDTO relatoriio = relatorioRepository.gerarRelatorioSaldoCliente(uuidCliente);
		return relatoriio;
	}
	

	@Override
	public RelatorioSaldoClienteDTO gerarRelatorioSaldoClienteByPeriodo(UUID uuidCliente, LocalDate dataInicio,
			LocalDate dataFim) {
		RelatorioSaldoClienteDTO relatoriio = relatorioRepository.gerarRelatorioSaldoClienteByPeriodo(uuidCliente, dataInicio, dataFim );
		return relatoriio;
	}

	@Override
	public List<RelatorioSaldoGeralDTO> gerarRelatorioSaldoGeral() {
		return relatorioRepository.gerarRelatorioSaldoGeral();
	}

	@Override
	public List<RelatorioReceitaDTO> gerarRelatorioReceita(LocalDate dataInicio, LocalDate dataFim) {
		return relatorioRepository.gerarRelatorioReceita(dataInicio,dataFim);
	}

	
	}


