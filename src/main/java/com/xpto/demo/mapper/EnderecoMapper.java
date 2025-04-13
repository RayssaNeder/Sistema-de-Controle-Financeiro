package com.xpto.demo.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.xpto.demo.dto.CreateEndereco;
import com.xpto.demo.dto.EnderecoDTO;
import com.xpto.demo.dto.PageEnderecoDTO;
import com.xpto.demo.entity.EnderecoDomainEntity;

@Component
public class EnderecoMapper {

	private final ModelMapper modelMapper;

	public EnderecoMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public EnderecoDTO toModel(EnderecoDomainEntity entity) {
		return modelMapper.map(entity, EnderecoDTO.class);
	}

	public EnderecoDomainEntity toEntity(CreateEndereco createEndereco) {
		ModelMapper modelMapper = new ModelMapper();

		PropertyMap<CreateEndereco, EnderecoDomainEntity> enderecoEntityMap = new PropertyMap<CreateEndereco, EnderecoDomainEntity>() {
			protected void configure() {
				skip(destination.getId());
				skip(destination.getUuid());
				skip(destination.getCliente());
			}
		};
		modelMapper.addMappings(enderecoEntityMap);

		return modelMapper.map(createEndereco, EnderecoDomainEntity.class);
	}

	public PageEnderecoDTO toPageModel(Page<EnderecoDTO> page) {
		PageEnderecoDTO pageEndereco = new PageEnderecoDTO();
		pageEndereco.setPage(page.getNumber());
		pageEndereco.setSize(page.getSize());
		pageEndereco.setTotalElements(page.getTotalElements());
		pageEndereco.setTotalPages(page.getTotalPages());
		pageEndereco.setResults(page.getContent());
		return pageEndereco;
	}
}
