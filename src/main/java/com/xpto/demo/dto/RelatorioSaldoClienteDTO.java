package com.xpto.demo.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import lombok.Data;

@Data
public class RelatorioSaldoClienteDTO {
	private String nome;
	private LocalDate dataCadastro;

	private String rua;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;

	private Long qtdCredito;
	private Long qtdDebito;
	private Long totalMovimentacoes;
	private BigDecimal valorPago;
	private BigDecimal saldoInicial;
	private BigDecimal saldoAtual;

	public RelatorioSaldoClienteDTO(String nome, LocalDate dataCadastro, String rua, String numero, String complemento,
			String bairro, String cidade, String uf, String cep, Long qtdCredito, Long qtdDebito,
			Long totalMovimentacoes, BigDecimal valorPago, BigDecimal saldoInicial, BigDecimal saldoAtual) {
		this.nome = nome;
		this.dataCadastro = dataCadastro;

		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;

		this.qtdCredito = qtdCredito;
		this.qtdDebito = qtdDebito;
		this.totalMovimentacoes = totalMovimentacoes;
		this.valorPago = valorPago.setScale(2, RoundingMode.HALF_UP);
		this.saldoInicial = saldoInicial.setScale(2, RoundingMode.HALF_UP);
		this.saldoAtual = saldoAtual.setScale(2, RoundingMode.HALF_UP);
	}

	public String gerarTextoRelatorio() {
		return String.format("""
				    Relatório de saldo do cliente %s:
				    Cliente: %s - Cliente desde: %s;
				    Endereço: %s, %s, %s, %s, %s, %s, %s;
				    Movimentações de crédito: %d;
				    Movimentações de débito: %d;
				    Total de movimentações: %d;
				    Valor pago pelas movimentações: R$ %,.2f
				    Saldo inicial: R$ %,.2f
				    Saldo atual: R$ %,.2f
				""", nome, nome, dataCadastro, rua, numero, complemento != null ? complemento : "-", bairro, cidade, uf,
				cep, qtdCredito, qtdDebito, totalMovimentacoes, valorPago, saldoInicial, saldoAtual);
	}
}
