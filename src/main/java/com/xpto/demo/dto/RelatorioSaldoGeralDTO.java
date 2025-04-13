package com.xpto.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.xpto.demo.service.utils.RelatorioUtils.RelatorioComFormato;

public class RelatorioSaldoGeralDTO implements RelatorioComFormato {

    private String nomeCliente;
    private LocalDate dataCadastro;
    private LocalDate dataReferencia;
    private BigDecimal saldo;

    public RelatorioSaldoGeralDTO(String nomeCliente, LocalDate dataCadastro, LocalDate dataReferencia, BigDecimal saldo) {
        this.nomeCliente = nomeCliente;
        this.dataCadastro = dataCadastro;
        this.dataReferencia = dataReferencia;
        this.saldo = saldo != null ? saldo.setScale(2) : BigDecimal.ZERO.setScale(2);
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public LocalDate getDataReferencia() {
        return dataReferencia;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
    
    public String gerarTextoRelatorio() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();

        sb.append("Relatório de saldo de todos os clientes:\n");
        sb.append(String.format("Cliente: %s - Cliente desde: %s - Saldo em %s: %s\n",
            nomeCliente,
            dataCadastro.format(formatter),
            dataReferencia.format(formatter),
            String.format("%,.2f", saldo)
        ));

        return sb.toString();
    }


    public String formatarLinhaRelatorio() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String cadastroFormatado = dataCadastro.format(formatter);
        String referenciaFormatada = dataReferencia.format(formatter);
        String saldoFormatado = String.format("%,.2f", saldo);
        
        return String.format("Cliente: %s - Cliente desde: %s - Saldo em %s: %s", 
            nomeCliente, cadastroFormatado, referenciaFormatada, saldoFormatado);
    }
}
