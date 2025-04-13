package com.xpto.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CreateClientePessoaFisicaDTO extends CreateCliente {
    private String cpf;

    
    
}
