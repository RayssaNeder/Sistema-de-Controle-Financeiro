package com.xpto.demo.dto;

import java.math.BigDecimal;

import com.xpto.demo.service.utils.RelatorioUtils.RelatorioComFormato;

public class RelatorioReceitaDTO implements RelatorioComFormato { 
    private String nomeCliente;
    private Long quantidadeMovimentacoes;
    private BigDecimal valorMovimentacoes;

    public RelatorioReceitaDTO(String nomeCliente, Long quantidadeMovimentacoes, BigDecimal valorMovimentacoes) {
        this.nomeCliente = nomeCliente;
        this.quantidadeMovimentacoes = quantidadeMovimentacoes;
        this.valorMovimentacoes = valorMovimentacoes != null ? valorMovimentacoes.setScale(2) : BigDecimal.ZERO.setScale(2);
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public Long getQuantidadeMovimentacoes() {
        return quantidadeMovimentacoes;
    }

    public BigDecimal getValorMovimentacoes() {
        return valorMovimentacoes;
    }

    public String formatarLinhaRelatorio() {
    	return String.format("Cliente: %s - Quantidade de movimentações: %d - Valor das movimentações: R$ %,.2f",
    		    nomeCliente, quantidadeMovimentacoes, valorMovimentacoes.doubleValue());
    }
}
