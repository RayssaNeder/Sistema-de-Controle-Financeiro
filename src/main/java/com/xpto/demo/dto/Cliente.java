package com.xpto.demo.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class Cliente {
	private UUID uuid;
	private String nome;
	private String email;
	private String telefone;

	private String cpf;
	private String cnpj;
	private String razaoSocial;
	private EnderecoDTO endereco;
}
