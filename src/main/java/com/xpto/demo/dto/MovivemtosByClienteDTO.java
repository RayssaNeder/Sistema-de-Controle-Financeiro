package com.xpto.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.xpto.demo.enums.TipoMovimentacao;

import lombok.Data;

@Data
public class MovivemtosByClienteDTO {

	public MovivemtosByClienteDTO(LocalDate data, UUID conta, TipoMovimentacao tipo, BigDecimal valor, String descricao,
			UUID cliente, String nome, String email, String telefone, String agencia, String numero, Boolean ativa) {
		super();
		this.data = data;
		this.tipo = tipo;
		this.valor = valor;
		this.descricao = descricao;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.agencia = agencia;
		this.numero = numero;
		this.ativa = ativa;
		this.conta = conta;
		this.cliente = cliente;
	}

	private LocalDate data;
	private UUID conta;
	private TipoMovimentacao tipo;
	private BigDecimal valor;
	private String descricao;
	private UUID cliente;
	private String nome;
	private String email;
	private String telefone;
	private String agencia;
	private String numero;
	private Boolean ativa;
}
