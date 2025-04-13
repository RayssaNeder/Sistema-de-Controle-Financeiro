package com.xpto.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xpto.demo.dto.RelatorioReceitaDTO;
import com.xpto.demo.dto.RelatorioSaldoClienteDTO;
import com.xpto.demo.dto.RelatorioSaldoGeralDTO;
import com.xpto.demo.entity.MovimentacaoDomainEntity;



@Repository
public interface RelatorioRepository extends JpaRepository<MovimentacaoDomainEntity, Long> {

	
	@Query("""
		    SELECT new com.xpto.demo.dto.RelatorioSaldoClienteDTO(
		        cl.nome,
		        cl.dataCadastro,
		        e.rua,
		        e.numero,
		        e.complemento,
		        e.bairro,
		        e.cidade,
		        e.uf,
		        e.cep,
		        SUM(CASE WHEN m.tipo = 'CREDITO' THEN 1 ELSE 0 END),
		        SUM(CASE WHEN m.tipo = 'DEBITO' THEN 1 ELSE 0 END),
		        COUNT(m),
		        CAST(
		            CASE
		                WHEN COUNT(m.id) <= 10 THEN COUNT(m.id) * 1.00
		                WHEN COUNT(m.id) <= 20 THEN COUNT(m.id) * 0.75
		                ELSE COUNT(m.id) * 0.50
		            END AS BigDecimal),
		        COALESCE((
		            SELECT m2.valor
		            FROM MovimentacaoDomainEntity m2
		            WHERE m2.conta.cliente.uuid = :uuidCliente
		            AND m2.data = (
		                SELECT MIN(m3.data)
		                FROM MovimentacaoDomainEntity m3
		                WHERE m3.conta.cliente.uuid = :uuidCliente
		            )
		            ORDER BY m2.id
		            FETCH FIRST 1 ROWS ONLY
		        ), CAST(0 AS BigDecimal)),
		        SUM(CASE WHEN m.tipo = 'CREDITO' THEN m.valor ELSE -m.valor END)
		    )
		    FROM MovimentacaoDomainEntity m
		    JOIN m.conta c
		    JOIN c.cliente cl
		    JOIN cl.endereco e
		    WHERE cl.uuid = :uuidCliente
		    GROUP BY cl.nome, cl.dataCadastro,
		             e.rua, e.numero, e.complemento, e.bairro, e.cidade, e.uf, e.cep
		""")
		RelatorioSaldoClienteDTO gerarRelatorioSaldoCliente(@Param("uuidCliente") UUID uuidCliente);

	@Query("""
		    SELECT new com.xpto.demo.dto.RelatorioSaldoClienteDTO(
		        cl.nome,
		        cl.dataCadastro,
		        e.rua,
		        e.numero,
		        e.complemento,
		        e.bairro,
		        e.cidade,
		        e.uf,
		        e.cep,
		        SUM(CASE WHEN m.tipo = 'CREDITO' THEN 1 ELSE 0 END),
		        SUM(CASE WHEN m.tipo = 'DEBITO' THEN 1 ELSE 0 END),
		        COUNT(m),
		        CAST(
		            CASE
		                WHEN COUNT(m.id) <= 10 THEN COUNT(m.id) * 1.00
		                WHEN COUNT(m.id) <= 20 THEN COUNT(m.id) * 0.75
		                ELSE COUNT(m.id) * 0.50
		            END AS BigDecimal),
		        COALESCE((
		            SELECT m2.valor
		            FROM MovimentacaoDomainEntity m2
		            WHERE m2.conta.cliente.uuid = :uuidCliente
		              AND m2.data = (
		                  SELECT MIN(m3.data)
		                  FROM MovimentacaoDomainEntity m3
		                  WHERE m3.conta.cliente.uuid = :uuidCliente
		              )
		              ORDER BY m2.id
		              FETCH FIRST 1 ROWS ONLY
		        ), CAST(0 AS BigDecimal)),
		        SUM(CASE WHEN m.tipo = 'CREDITO' THEN m.valor ELSE -m.valor END)
		    )
		    FROM MovimentacaoDomainEntity m
		    JOIN m.conta c
		    JOIN c.cliente cl
		    JOIN cl.endereco e
		    WHERE cl.uuid = :uuidCliente
		      AND m.data BETWEEN :dataInicio AND :dataFim
		    GROUP BY cl.nome, cl.dataCadastro,
		             e.rua, e.numero, e.complemento, e.bairro, e.cidade, e.uf, e.cep
		""")
		RelatorioSaldoClienteDTO gerarRelatorioSaldoClienteByPeriodo(
		    @Param("uuidCliente") UUID uuidCliente,
		    @Param("dataInicio") LocalDate dataInicio,
		    @Param("dataFim") LocalDate dataFim
		);

	

	@Query("""
		    SELECT new com.xpto.demo.dto.RelatorioSaldoGeralDTO(
		        cl.nome,
		        cl.dataCadastro,
		        CAST(CURRENT_DATE AS LocalDate),
		        SUM(CASE 
		                WHEN m.tipo = 'CREDITO' THEN m.valor 
		                ELSE -m.valor 
		            END)
		    )
		    FROM ClienteDomainEntity cl
		    LEFT JOIN cl.contas c
		    LEFT JOIN c.movimentacoes m
		    GROUP BY cl.nome, cl.dataCadastro
		""")
		List<RelatorioSaldoGeralDTO> gerarRelatorioSaldoGeral();



	@Query("""
    SELECT new com.xpto.demo.dto.RelatorioReceitaDTO(
        cl.nome,
        COUNT(m),
        SUM(CASE 
                WHEN m.tipo = 'CREDITO' THEN m.valor
                ELSE -m.valor
            END)
    )
    FROM MovimentacaoDomainEntity m
    JOIN m.conta c
    JOIN c.cliente cl
    WHERE m.data BETWEEN :dataInicio AND :dataFim
    GROUP BY cl.nome
""")
List<RelatorioReceitaDTO> gerarRelatorioReceita(
    @Param("dataInicio") LocalDate dataInicio,
    @Param("dataFim") LocalDate dataFim
);


}
