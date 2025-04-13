package com.xpto.demo.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class EnderecoDTO {

	private UUID uuid;

	private String rua;

	private String bairro;

	private String cep;

	private String numero;

	private String complemento;

	private String cidade;

	private String uf;

	public EnderecoDTO() {
	}

	public EnderecoDTO(UUID uuid, String rua, String bairro, String cep, String numero, String complemento,
			String cidade, String uf, UUID uuidCliente) {
		super();
		this.uuid = uuid;
		this.rua = rua;
		this.bairro = bairro;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
		this.cidade = cidade;
		this.uf = uf;
	}

}
