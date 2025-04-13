package com.xpto.demo.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.xpto.demo.enums.TipoMovimentacao;

import lombok.Data;

@Data
public class CreateMovimentacaoDTO {
	private UUID uuidConta;
	private BigDecimal valor;
	private String descricao;
	private TipoMovimentacao tipo;

}
