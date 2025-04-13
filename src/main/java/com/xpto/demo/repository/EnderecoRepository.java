package com.xpto.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xpto.demo.dto.EnderecoDTO;
import com.xpto.demo.entity.EnderecoDomainEntity;
import com.xpto.demo.service.exception.UnimplementedMethodException;


@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoDomainEntity, UUID> {

  
  default Optional<EnderecoDomainEntity> findById(Long id) {
    throw new UnimplementedMethodException("Método não implementado.");
  }

  void deleteByUuid(UUID uuid);

  Optional<EnderecoDomainEntity> findByUuid(UUID uuid);

  @Query("SELECT new com.xpto.demo.dto.EnderecoDTO(" 
		  + " e.uuid, " 
			+ " e.rua, " 
			+ "e.bairro, "
			+ "	 e.cep, " 
			+ "	 e.numero, "
			+ "	 e.complemento, " 
			+ "e.cidade, "
			+ "	e.uf , " 
			+ "	cl.uuid ) " 
			+ "FROM EnderecoDomainEntity e "
			+ "JOIN ClienteDomainEntity cl ON cl.id = e.cliente.id "
			+ "WHERE  cl.uuid = :uuidCliente")
  List<EnderecoDTO> findAllEnderecoByCliente(UUID uuidCliente);
  

}
