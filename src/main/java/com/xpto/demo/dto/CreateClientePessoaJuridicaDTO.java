package com.xpto.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CreateClientePessoaJuridicaDTO extends CreateCliente {
	 private String cnpj;
	    private String razaoSocial;
}
