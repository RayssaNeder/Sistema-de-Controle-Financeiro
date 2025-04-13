package com.xpto.demo.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class CreateEndereco {

	private String agencia;
	private String rua;
	private String bairro;
	private String cep;
	private String numero;
	private String complemento;
	private String cidade;
	private String uf;
	private UUID uuidCliente;

}
