package com.xpto.demo.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ContaDTO {

	private UUID uuid;
	private String agencia;
	private String numero;
	private Boolean ativa;
	private UUID clienteUuid;
	private List<MovimentacaoDTO> movimentacoes;

}
