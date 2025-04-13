package com.xpto.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.xpto.demo.enums.TipoMovimentacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "movimentacao")
public class MovimentacaoDomainEntity {
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
	  
	  @Column(nullable = false, updatable = false, unique = true)
	    UUID uuid;

    private LocalDate data;

    private BigDecimal valor;
    
    private String descricao;
    
    @Column(name = "data_exclusao")
    private LocalDate dataExclusao;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private ContaDomainEntity conta;
    
    
    public void excluir() {
        this.dataExclusao = LocalDate.now(); 
    }

    @PrePersist
    @SuppressWarnings("unused")
    private void initializeUUID() {
      if (uuid == null) {
        uuid = UUID.randomUUID();
      }
    }
}
