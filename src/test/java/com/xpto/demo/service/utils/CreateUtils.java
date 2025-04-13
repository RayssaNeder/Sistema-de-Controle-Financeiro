package com.xpto.demo.service.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;

import com.xpto.demo.dto.Cliente;
import com.xpto.demo.dto.CreateCliente;
import com.xpto.demo.dto.CreateClientePessoaFisicaDTO;
import com.xpto.demo.dto.CreateClientePessoaJuridicaDTO;
import com.xpto.demo.dto.EnderecoDTO;
import com.xpto.demo.dto.MovimentacaoDTO;
import com.xpto.demo.dto.UpdateCliente;
import com.xpto.demo.entity.ClientePessoaFisicaDomainEntity;
import com.xpto.demo.entity.ClientePessoaJuridicaDomainEntity;
import com.xpto.demo.entity.ContaDomainEntity;
import com.xpto.demo.entity.EnderecoDomainEntity;
import com.xpto.demo.entity.MovimentacaoDomainEntity;
import com.xpto.demo.enums.TipoMovimentacao;



public class CreateUtils {

	@AfterAll
	public static void afterAll() {
		System.gc();
	}

	public static EnderecoDomainEntity createEnderecoEntity() {
		EnderecoDomainEntity endereco = new EnderecoDomainEntity();
		endereco.setId(1L);
		endereco.setRua("Rua das Flores");
		endereco.setNumero("123");
		endereco.setComplemento("Apto 101");
		endereco.setBairro("Centro");
		endereco.setCidade("São Paulo");
		endereco.setUf("SP");
		endereco.setCep("01001-000");

		return endereco;
	}

	public static ClientePessoaFisicaDomainEntity createClientePessoaFisicaEntity() {
		ClientePessoaFisicaDomainEntity entity = new ClientePessoaFisicaDomainEntity();
		entity.setId(1L);
		entity.setUuid(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
		entity.setNome("João da Silva");
		entity.setDataCadastro(LocalDate.now());
		entity.setDataCadastro(LocalDate.now());
		entity.setEmail("joao.silva@gmail.com");
		entity.setTelefone("(11) 98765-4321");
		entity.setCpf("123.456.789-09");

		return entity;
	}

	public static ClientePessoaJuridicaDomainEntity createClientePessoaJuridicaEntity() {
		ClientePessoaJuridicaDomainEntity entity = new ClientePessoaJuridicaDomainEntity();
		entity.setId(1L);
		entity.setUuid(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
		entity.setNome("João da Silva");
		entity.setDataCadastro(LocalDate.now());
		entity.setDataCadastro(LocalDate.now());
		entity.setEmail("joao.silva@gmail.com");
		entity.setTelefone("(11) 98765-4321");
		entity.setCnpj("123.456.789-09");
		entity.setRazaoSocial("Flores LTDA");

		return entity;
	}

	public static CreateCliente createCreateCliente() {
		CreateCliente createCliente = new CreateCliente();
		createCliente.setNome("João da Silva");
		createCliente.setEmail("joao.silva@example.com");
		createCliente.setTelefone("(11) 98765-4321");
		createCliente.setAgencia("0001");

		return createCliente;
		
	}
	public static CreateClientePessoaFisicaDTO createCreateClientePessoaFIsicaDTO() {
		CreateClientePessoaFisicaDTO createCliente = new CreateClientePessoaFisicaDTO();
		createCliente.setNome("João da Silva");
		createCliente.setEmail("joao.silva@example.com");
		createCliente.setTelefone("(11) 98765-4321");
		createCliente.setAgencia("0001");
		createCliente.setCpf("123.456.789-09");

		return createCliente;
		
	}
	
	public static CreateClientePessoaJuridicaDTO createCreateClientePessoaJuridicaDTO() {
		CreateClientePessoaJuridicaDTO createCliente = new CreateClientePessoaJuridicaDTO();
		createCliente.setNome("João da Silva");
		createCliente.setEmail("joao.silva@example.com");
		createCliente.setTelefone("(11) 98765-4321");
		createCliente.setAgencia("0001");
		createCliente.setCnpj("123.456.789-09");
		createCliente.setRazaoSocial("JoaoSilvaLTDA");

		return createCliente;
		
	}
	
    public static Cliente criarClientePessoaFisica() {
        Cliente cliente = new Cliente();
        cliente.setUuid(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        cliente.setNome("João da Silva");
        cliente.setEmail("joao.silva@email.com");
        cliente.setTelefone("(11) 98765-4321");
        cliente.setCpf("123.456.789-09");
        cliente.setCnpj(null);
        cliente.setRazaoSocial(null);
        cliente.setEndereco(criarEndereco());
        return cliente;
    }

    public static Cliente criarClientePessoaJuridica() {
        Cliente cliente = new Cliente();
        cliente.setUuid(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        cliente.setNome("Tech Solutions Ltda");
        cliente.setEmail("contato@techsolutions.com.br");
        cliente.setTelefone("(11) 3456-7890");
        cliente.setCnpj("12.345.678/0001-90");
        cliente.setRazaoSocial("Tech Solutions Tecnologia Ltda");
        cliente.setCpf(null);
        cliente.setEndereco(criarEndereco());
        return cliente;
    }
	
	
    private static EnderecoDTO criarEndereco() {
        EnderecoDTO endereco = new EnderecoDTO();
        endereco.setRua("Rua das Flores");
        endereco.setNumero("123");
        endereco.setComplemento("Apto 101");
        endereco.setBairro("Centro");
        endereco.setCidade("São Paulo");
        endereco.setUf("SP");
        endereco.setCep("01001-000");
        return endereco;
    }

    public static ContaDomainEntity criarContaDomainEntity() {
        ContaDomainEntity conta = new ContaDomainEntity();
        conta.setId(1L);
        conta.setUuid(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        conta.setAgencia("0001");
        conta.setNumero("123456-7");
        conta.setAtiva(true);
        conta.setDataExclusao(null);
        conta.setCliente(createClientePessoaFisicaEntity());
        
        return conta;
	    
    }
    
    public static MovimentacaoDomainEntity criarMovimentacaoDomainEntity() {

    MovimentacaoDomainEntity movimentacao = new MovimentacaoDomainEntity();
    movimentacao.setId(1L);
    movimentacao.setUuid(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
    movimentacao.setData(LocalDate.of(2023, 11, 15));
    movimentacao.setValor(new BigDecimal("1500.75"));
    movimentacao.setDescricao("Depósito inicial");
    movimentacao.setTipo(TipoMovimentacao.CREDITO);
    movimentacao.setDataExclusao(null);
    movimentacao.setConta(criarContaDomainEntity());
    
    return movimentacao;
    }
    
    public static MovimentacaoDTO criarMovimentacaoDTO() {
    MovimentacaoDTO movimentacao = new MovimentacaoDTO();
    movimentacao.setUuid(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
    movimentacao.setData(LocalDate.of(2023, 11, 20));
    movimentacao.setValor(new BigDecimal("1500.75"));
    movimentacao.setDescricao("Depósito inicial");
    movimentacao.setTipo(TipoMovimentacao.CREDITO);
    movimentacao.setUuidConta(UUID.fromString("110e8400-e29b-41d4-a716-446655441111"));
    
    return movimentacao;
}

	public static UpdateCliente createUpdateCliente() {
		UpdateCliente updatePF = new UpdateCliente();
		updatePF.setNome("João Silva Updated");
		updatePF.setEmail("joao.updated@email.com");
		updatePF.setTelefone("(11) 98765-0001");
		updatePF.setCpf("123.456.789-09"); 
		updatePF.setCnpj(null);
		updatePF.setRazaoSocial(null);
		
		return updatePF;
	}
}
