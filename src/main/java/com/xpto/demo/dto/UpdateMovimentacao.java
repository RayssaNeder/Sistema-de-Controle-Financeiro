package com.xpto.demo.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.xpto.demo.enums.TipoMovimentacao;

import lombok.Data;

@Data
public class UpdateMovimentacao {
	
	  private UUID nomeCliente;
	    private UUID conta;
	    private BigDecimal valor;
	    private TipoMovimentacao tipo; 

}
