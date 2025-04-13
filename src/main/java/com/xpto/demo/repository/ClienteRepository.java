package com.xpto.demo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xpto.demo.entity.ClienteDomainEntity;
import com.xpto.demo.service.exception.UnimplementedMethodException;


@Repository
public interface ClienteRepository extends JpaRepository<ClienteDomainEntity, UUID> {

  
  default Optional<ClienteDomainEntity> findById(Long id) {
    throw new UnimplementedMethodException("Método não implementado.");
  }

  void deleteByUuid(UUID uuid);

  Optional<ClienteDomainEntity> findByUuid(UUID uuid);
}
