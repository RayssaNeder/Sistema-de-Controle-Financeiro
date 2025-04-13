package com.xpto.demo.service;

import java.sql.CallableStatement;
import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IntegracaoServiceImpl implements IntegracaoService {

	private final DataSource dataSource;

	public IntegracaoServiceImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void executarIntegracao() {
		try (Connection conn = dataSource.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{call APP.PRC_INTEGRACAO_DADOS()}");
			stmt.execute();
			System.out.println("Procedure executada com sucesso.");
		} catch (Exception e) {
			System.err.println("Erro ao executar procedure: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
