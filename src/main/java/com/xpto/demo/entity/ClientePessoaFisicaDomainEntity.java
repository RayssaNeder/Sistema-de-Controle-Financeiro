package com.xpto.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cliente_pessoa_fisica")
public class ClientePessoaFisicaDomainEntity extends ClienteDomainEntity {
    private String cpf;
}

