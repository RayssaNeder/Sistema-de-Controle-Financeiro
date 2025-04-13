package com.xpto.demo.dto;

import java.math.BigDecimal;

import com.xpto.demo.service.utils.RelatorioUtils.RelatorioComFormato;

import lombok.Data;


@Data
public class RelatorioReceitaDTO implements RelatorioComFormato {
	private String nomeCliente;
	private Long quantidadeMovimentacoes;
	private BigDecimal valorMovimentacoes;
	 private BigDecimal totalReceita;

	public RelatorioReceitaDTO(String nomeCliente, Long quantidadeMovimentacoes, BigDecimal valorMovimentacoes,  BigDecimal totalReceita) {
		this.nomeCliente = nomeCliente;
		this.quantidadeMovimentacoes = quantidadeMovimentacoes;
		this.valorMovimentacoes = valorMovimentacoes != null ? valorMovimentacoes.setScale(2)
				: BigDecimal.ZERO.setScale(2);
        this.totalReceita = totalReceita != null ? totalReceita.setScale(2) : BigDecimal.ZERO.setScale(2);
	}



	public String formatarLinhaRelatorio() {
	    return String.format(
	        "Cliente: %s - Quantidade de movimentações: %d - Valor das movimentações: R$ %,.2f - Total de Receitas: R$ %,.2f",
	        nomeCliente, quantidadeMovimentacoes, valorMovimentacoes.doubleValue(), totalReceita.doubleValue());
	}

}
