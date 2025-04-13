package com.xpto.demo.mapper;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.xpto.demo.dto.Cliente;
import com.xpto.demo.dto.CreateClientePessoaFisicaDTO;
import com.xpto.demo.dto.CreateClientePessoaJuridicaDTO;
import com.xpto.demo.dto.PageCliente;
import com.xpto.demo.dto.UpdateCliente;
import com.xpto.demo.entity.ClienteDomainEntity;
import com.xpto.demo.entity.ClientePessoaFisicaDomainEntity;
import com.xpto.demo.entity.ClientePessoaJuridicaDomainEntity;

@Component
public class ClienteMapper {

	private final ModelMapper modelMapper;

	public ClienteMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;

	}

	public Cliente toModel(ClientePessoaFisicaDomainEntity entity) {
		return modelMapper.map(entity, Cliente.class);
	}

	public Cliente toModel(ClientePessoaJuridicaDomainEntity entity) {
		return modelMapper.map(entity, Cliente.class);
	}

	public Cliente toModel(ClienteDomainEntity entity) {
		if (entity instanceof ClientePessoaFisicaDomainEntity fisica) {
			return toModel(fisica);
		} else if (entity instanceof ClientePessoaJuridicaDomainEntity juridica) {
			return toModel(juridica);
		} else {
			throw new IllegalArgumentException("Tipo de cliente desconhecido: " + entity.getClass());
		}
	}

	public ClientePessoaFisicaDomainEntity toEntity(CreateClientePessoaFisicaDTO createCliente) {
		ModelMapper modelMapper = new ModelMapper();

		PropertyMap<CreateClientePessoaFisicaDTO, ClientePessoaFisicaDomainEntity> clienteEntityMap = new PropertyMap<CreateClientePessoaFisicaDTO, ClientePessoaFisicaDomainEntity>() {
			protected void configure() {
				skip(destination.getId());
				skip(destination.getUuid());
			}
		};
		modelMapper.addMappings(clienteEntityMap);

		return modelMapper.map(createCliente, ClientePessoaFisicaDomainEntity.class);
	}

	public ClientePessoaJuridicaDomainEntity toEntity(CreateClientePessoaJuridicaDTO createCliente) {
		ModelMapper modelMapper = new ModelMapper();

		PropertyMap<CreateClientePessoaJuridicaDTO, ClientePessoaJuridicaDomainEntity> clienteEntityMap = new PropertyMap<CreateClientePessoaJuridicaDTO, ClientePessoaJuridicaDomainEntity>() {
			protected void configure() {
				skip(destination.getId());
				skip(destination.getUuid());
			}
		};
		modelMapper.addMappings(clienteEntityMap);

		return modelMapper.map(createCliente, ClientePessoaJuridicaDomainEntity.class);
	}

	public void intoEntity(UpdateCliente updateCliente, ClienteDomainEntity clienteEntity) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

		PropertyMap<UpdateCliente, ClientePessoaFisicaDomainEntity> clienteEntityMap = new PropertyMap<UpdateCliente, ClientePessoaFisicaDomainEntity>() {
			protected void configure() {
				skip(destination.getId());
			}
		};
		modelMapper.addMappings(clienteEntityMap);

		modelMapper.map(updateCliente, clienteEntity);
	}

	public PageCliente toPageModel(Page<Cliente> page) {
		PageCliente pageCliente = new PageCliente();
		pageCliente.setPage(page.getNumber());
		pageCliente.setSize(page.getSize());
		pageCliente.setTotalElements(page.getTotalElements());
		pageCliente.setTotalPages(page.getTotalPages());
		pageCliente.setResults(page.getContent());
		return pageCliente;
	}
}
