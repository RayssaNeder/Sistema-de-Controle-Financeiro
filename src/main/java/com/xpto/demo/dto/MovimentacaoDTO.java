package com.xpto.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.xpto.demo.enums.TipoMovimentacao;

import lombok.Data;

@Data
public class MovimentacaoDTO {
    private UUID uuid;
    private LocalDate data;
    private BigDecimal valor;
    private String descricao;
    private TipoMovimentacao tipo;
    private UUID uuidConta; 

}
