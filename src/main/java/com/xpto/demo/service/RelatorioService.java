package com.xpto.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.xpto.demo.dto.RelatorioReceitaComTotalDTO;
import com.xpto.demo.dto.RelatorioSaldoClienteDTO;
import com.xpto.demo.dto.RelatorioSaldoGeralDTO;

public interface RelatorioService {

	public RelatorioSaldoClienteDTO gerarRelatorioSaldoCliente(UUID uuidCliente);

	public RelatorioSaldoClienteDTO gerarRelatorioSaldoClienteByPeriodo(UUID uuidCliente, LocalDate dataInicio,
			LocalDate dataFim);

	public List<RelatorioSaldoGeralDTO> gerarRelatorioSaldoGeral();

	public RelatorioReceitaComTotalDTO gerarRelatorioReceita(LocalDate dataInicio, LocalDate dataFim);

}
