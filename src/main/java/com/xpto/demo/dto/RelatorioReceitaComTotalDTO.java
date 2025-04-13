package com.xpto.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.xpto.demo.service.utils.RelatorioUtils.RelatorioComFormato;

import lombok.Data;


@Data
public class RelatorioReceitaComTotalDTO implements RelatorioComFormato {
    private List<RelatorioReceitaDTO> receitas;
    private BigDecimal totalReceita;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public RelatorioReceitaComTotalDTO(List<RelatorioReceitaDTO> receitas, 
                                     BigDecimal totalReceita,
                                     LocalDate dataInicio,
                                     LocalDate dataFim) {
        this.receitas = receitas;
        this.totalReceita = totalReceita != null ? totalReceita.setScale(2) : BigDecimal.ZERO.setScale(2);
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String formatarLinhaRelatorio() {

        StringBuilder sb = new StringBuilder();
        
        sb.append("Relatório de receita da empresa (XPTO) por período:\n");
        sb.append(String.format("Período: %s a %s\n\n", 
                              dataInicio, 
                              dataFim));
        
        for (RelatorioReceitaDTO receita : receitas) {
            sb.append(String.format(
                "Cliente %s - Quantidade de movimentações: %d - Valor das movimentações: R$ %,.2f\n",
                receita.getNomeCliente(),
                receita.getQuantidadeMovimentacoes(),
                receita.getValorMovimentacoes().doubleValue()
            ));
        }
        
        // Rodapé com total
        sb.append(String.format("\nTotal de receitas: R$ %,.2f", 
                              totalReceita.doubleValue()));
        
        return sb.toString();
    }
}
