package com.xpto.demo.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class UpdateConta {
	
	private String agencia;
    private String numero;
    private Boolean ativa;
    private UUID uuidCliente; 

}
