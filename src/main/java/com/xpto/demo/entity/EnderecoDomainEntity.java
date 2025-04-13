package com.xpto.demo.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "endereco")
public class EnderecoDomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    
    @Column(nullable = false, updatable = false, unique = true)
    UUID uuid;

    private String rua;

    private String bairro;

    private String cep;

    private String numero;

    private String complemento;

    private String cidade;

    private String uf;
    
    @OneToOne
    @JoinColumn(name = "cliente_id", unique = true)
    private ClienteDomainEntity cliente;

     
    @PrePersist
    @SuppressWarnings("unused")
    private void initializeUUID() {
      if (uuid == null) {
        uuid = UUID.randomUUID();
      }
    }
}
