package com.xpto.demo.dto;

import lombok.Data;

@Data
public class UpdateCliente {
	private String nome;
	private String email;
	private String telefone;
	private String cpf;
	private String cnpj;
	private String razaoSocial;
}
