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
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "cliente")
public abstract class ClienteDomainEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private UUID uuid;
	private String nome;
	private String email;
	private String telefone;
	@Column(name = "data_cadastro")
    private LocalDate dataCadastro;
	 @Column(name = "data_exclusao")
	    private LocalDate dataExclusao;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	protected List<ContaDomainEntity> contas = new ArrayList<>();

	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private EnderecoDomainEntity endereco;


		
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
