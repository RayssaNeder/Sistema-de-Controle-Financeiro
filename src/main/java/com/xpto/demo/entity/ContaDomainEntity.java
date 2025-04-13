package com.xpto.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "conta")
public class ContaDomainEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, updatable = false)
	private UUID uuid;

	private String agencia;

	private String numero;

	private Boolean ativa = true;

	@Column(name = "data_exclusao")
	private LocalDate dataExclusao;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private ClienteDomainEntity cliente;

	@OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MovimentacaoDomainEntity> movimentacoes = new ArrayList<>();

	public void excluir() {
		this.ativa = false;
		this.dataExclusao = LocalDate.now();
	}

	@PrePersist
	private void initializeUUID() {
		if (uuid == null) {
			uuid = UUID.randomUUID();
		}
	}

}
