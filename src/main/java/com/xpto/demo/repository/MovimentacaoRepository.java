package com.xpto.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xpto.demo.dto.MovivemtosByClienteDTO;
import com.xpto.demo.dto.RelatorioSaldoClienteDTO;
import com.xpto.demo.entity.MovimentacaoDomainEntity;
import com.xpto.demo.service.exception.UnimplementedMethodException;


@Repository
public interface MovimentacaoRepository extends JpaRepository<MovimentacaoDomainEntity, UUID> {

	default Optional<MovimentacaoDomainEntity> findById(Long id) {
		throw new UnimplementedMethodException("Método não implementado.");
	}

	void deleteByUuid(UUID uuid);

	Optional<MovimentacaoDomainEntity> findByUuid(UUID uuid);

	@Query("SELECT new com.xpto.demo.dto.MovivemtosByClienteDTO(" 
			
			+ " m.data, " 
			+ "c.uuid, "
			+ "	 m.tipo, " 
			+ "	 m.valor, "
			+ "	 m.descricao, " 
			+ "cl.uuid, "
			+ "	cl.nome , " 
			+ "	cl.email , " 
			+ "	cl.telefone , " 
			+ "	 c.agencia , "
			+ "	 c.numero , " 
			+ "	 c.ativa) " 
			+ "FROM MovimentacaoDomainEntity m "
			+ "JOIN ContaDomainEntity c ON c.id = m.conta.id " 
			+ "JOIN ClienteDomainEntity cl ON cl.id = c.cliente.id "
			+ "WHERE  cl.uuid = :uuidCliente")
	List<MovivemtosByClienteDTO> findAllMovimentacaoByCliente(@Param("uuidCliente") UUID uuidCliente);

	
}
