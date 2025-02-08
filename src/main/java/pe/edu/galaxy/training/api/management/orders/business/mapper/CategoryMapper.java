package pe.edu.galaxy.training.api.management.orders.business.mapper;

import java.util.List;

import pe.edu.galaxy.training.api.management.orders.business.dto.CategoryDTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.CategoryEntity;

public interface CategoryMapper {
	CategoryDTO toDTO(CategoryEntity e);
	
	CategoryEntity toEntity(CategoryDTO d);

	List<CategoryDTO> toDTO(List<CategoryEntity> lstE);

	List<CategoryEntity> toEntity(List<CategoryDTO> lstD);
}
