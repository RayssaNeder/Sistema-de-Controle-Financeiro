package com.xpto.demo.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "cliente_pessoa_juridica")
public class ClientePessoaJuridicaDomainEntity extends ClienteDomainEntity {
	@Column(nullable = false, unique = true)
	private String cnpj;
	private String razaoSocial;

}
