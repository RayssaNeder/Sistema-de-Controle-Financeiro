package com.xpto.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xpto.demo.service.IntegracaoService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private final IntegracaoService integracaoService;

	public DemoApplication(IntegracaoService integracaoService) {
		this.integracaoService = integracaoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		integracaoService.executarIntegracao();
	}

}
