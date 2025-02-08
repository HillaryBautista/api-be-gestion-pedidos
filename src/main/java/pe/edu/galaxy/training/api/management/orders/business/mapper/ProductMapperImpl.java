package pe.edu.galaxy.training.api.management.orders.business.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import pe.edu.galaxy.training.api.management.orders.business.dto.ProductDTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.ProductEntity;

@Component
public class ProductMapperImpl implements ProductMapper {
	private final ModelMapper modelMapper;

	public ProductMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public ProductDTO toDTO(ProductEntity e) {
		return modelMapper.map(e, ProductDTO.class);
	}

	@Override
	public List<ProductDTO> toDTO(List<ProductEntity> lstE) {
		return lstE.stream().map(e -> toDTO(e)).toList();
	}

	@Override
	public ProductEntity toEntity(ProductDTO d) {
		return modelMapper.map(d, ProductEntity.class);
	}

	@Override
	public List<ProductEntity> toEntity(List<ProductDTO> lstD) {
		return lstD.stream().map(e -> toEntity(e)).toList();
	}
}
