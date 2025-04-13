package com.xpto.demo.mapper;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.xpto.demo.dto.MovimentacaoDTO;
import com.xpto.demo.dto.MovivemtosByClienteDTO;
import com.xpto.demo.dto.CreateMovimentacaoDTO;
import com.xpto.demo.dto.PageMovimentacaoDTO;
import com.xpto.demo.dto.UpdateMovimentacao;
import com.xpto.demo.entity.MovimentacaoDomainEntity;



@Component
public class MovimentacaoMapper {

	  private final ModelMapper modelMapper;

	  public MovimentacaoMapper(ModelMapper modelMapper) {
		    this.modelMapper = modelMapper;
		  }
	  
  public MovimentacaoDTO toModel(MovimentacaoDomainEntity entity) {
    return modelMapper.map(entity, MovimentacaoDTO.class);
  }

  public MovimentacaoDomainEntity toEntity(CreateMovimentacaoDTO createMovimentacao) {
    ModelMapper modelMapper = new ModelMapper();

    PropertyMap<CreateMovimentacaoDTO, MovimentacaoDomainEntity> contaEntityMap =
        new PropertyMap<CreateMovimentacaoDTO, MovimentacaoDomainEntity>() {
          protected void configure() {
            skip(destination.getId());
            skip(destination.getUuid());
            skip(destination.getConta());
          }
        };
    modelMapper.addMappings(contaEntityMap);

    return modelMapper.map(createMovimentacao, MovimentacaoDomainEntity.class);
  }

  public void intoEntity(UpdateMovimentacao updateMovimentacao, MovimentacaoDomainEntity contaEntity) {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

    PropertyMap<UpdateMovimentacao, MovimentacaoDomainEntity> contaEntityMap =
        new PropertyMap<UpdateMovimentacao, MovimentacaoDomainEntity>() {
          protected void configure() {
            skip(destination.getId());
          }
        };
    modelMapper.addMappings(contaEntityMap);

    modelMapper.map(updateMovimentacao, contaEntity);
  }

  public PageMovimentacaoDTO toPageModel(Page<MovimentacaoDTO> page) {
	  PageMovimentacaoDTO pageMovimentacao = new PageMovimentacaoDTO();
    pageMovimentacao.setPage(page.getNumber());
    pageMovimentacao.setSize(page.getSize());
    pageMovimentacao.setTotalElements(page.getTotalElements());
    pageMovimentacao.setTotalPages(page.getTotalPages());
    pageMovimentacao.setResults(page.getContent());
    return pageMovimentacao;
  }
  

}
