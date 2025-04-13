package com.xpto.demo.mapper;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.xpto.demo.dto.ContaDTO;
import com.xpto.demo.dto.CreateContaDTO;
import com.xpto.demo.dto.PageConta;
import com.xpto.demo.dto.UpdateConta;
import com.xpto.demo.entity.ContaDomainEntity;



@Component
public class ContaMapper {

	  private final ModelMapper modelMapper;

	  public ContaMapper(ModelMapper modelMapper) {
		    this.modelMapper = modelMapper;
		  }
	  
  public ContaDTO toModel(ContaDomainEntity entity) {
    return modelMapper.map(entity, ContaDTO.class);
  }

  public ContaDomainEntity toEntity(CreateContaDTO createConta) {
    ModelMapper modelMapper = new ModelMapper();

    PropertyMap<CreateContaDTO, ContaDomainEntity> contaEntityMap =
        new PropertyMap<CreateContaDTO, ContaDomainEntity>() {
          protected void configure() {
            skip(destination.getId());
            skip(destination.getUuid());
            skip(destination.getCliente());
          }
        };
    modelMapper.addMappings(contaEntityMap);

    return modelMapper.map(createConta, ContaDomainEntity.class);
  }

  public void intoEntity(UpdateConta updateConta, ContaDomainEntity contaEntity) {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

    PropertyMap<UpdateConta, ContaDomainEntity> contaEntityMap =
        new PropertyMap<UpdateConta, ContaDomainEntity>() {
          protected void configure() {
            skip(destination.getId());
          }
        };
    modelMapper.addMappings(contaEntityMap);

    modelMapper.map(updateConta, contaEntity);
  }

  public PageConta toPageModel(Page<ContaDTO> page) {
    PageConta pageConta = new PageConta();
    pageConta.setPage(page.getNumber());
    pageConta.setSize(page.getSize());
    pageConta.setTotalElements(page.getTotalElements());
    pageConta.setTotalPages(page.getTotalPages());
    pageConta.setResults(page.getContent());
    return pageConta;
  }
}
