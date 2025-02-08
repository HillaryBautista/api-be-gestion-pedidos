package pe.edu.galaxy.training.api.management.orders.business.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import pe.edu.galaxy.training.api.management.orders.business.dto.CategoryDTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.CategoryEntity;

@Component
public class CategoryMapperImpl implements CategoryMapper{
	private final ModelMapper modelMapper;
	
	public CategoryMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public CategoryDTO toDTO(CategoryEntity e) {
		return modelMapper.map(e, CategoryDTO.class);
	}

	@Override
	public CategoryEntity toEntity(CategoryDTO d) {
		return modelMapper.map(d, CategoryEntity.class);
	}

	@Override
	public List<CategoryDTO> toDTO(List<CategoryEntity> lstE) {
		return lstE.stream().map(e -> toDTO(e)).toList();
	}
	@Override
	public List<CategoryEntity> toEntity(List<CategoryDTO> lstD) {
		return lstD.stream().map(e -> toEntity(e)).toList();
	}

}
