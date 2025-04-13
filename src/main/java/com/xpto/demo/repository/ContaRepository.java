package com.xpto.demo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xpto.demo.entity.ContaDomainEntity;
import com.xpto.demo.service.exception.UnimplementedMethodException;


@Repository
public interface ContaRepository extends JpaRepository<ContaDomainEntity, UUID> {

  
  default Optional<ContaDomainEntity> findById(Long id) {
    throw new UnimplementedMethodException("Método não implementado.");
  }

  void deleteByUuid(UUID uuid);

  Optional<ContaDomainEntity> findByUuid(UUID uuid);
}
