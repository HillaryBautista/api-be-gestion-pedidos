package pe.edu.galaxy.training.api.management.orders.business.mapper;

import java.util.List;

import pe.edu.galaxy.training.api.management.orders.business.dto.ProductDTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.ProductEntity;

public interface ProductMapper {
	ProductDTO toDTO(ProductEntity e);
		
	ProductEntity toEntity(ProductDTO d);

	List<ProductDTO> toDTO(List<ProductEntity> lstE);

	List<ProductEntity> toEntity(List<ProductDTO> lstD);
}
