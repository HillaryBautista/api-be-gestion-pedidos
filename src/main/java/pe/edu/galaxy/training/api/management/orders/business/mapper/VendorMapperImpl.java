package pe.edu.galaxy.training.api.management.orders.business.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import pe.edu.galaxy.training.api.management.orders.business.dto.VendorDTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.VendorEntity;

@Component
public class VendorMapperImpl implements VendorMapper {
	private final ModelMapper modelMapper;

	public VendorMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public VendorDTO toDTO(VendorEntity e) {
		return modelMapper.map(e, VendorDTO.class);
	}

	@Override
	public List<VendorDTO> toDTO(List<VendorEntity> lstE) {
		return lstE.stream().map(e -> toDTO(e)).toList();
	}

	@Override
	public VendorEntity toEntity(VendorDTO d) {
		return modelMapper.map(d, VendorEntity.class);
	}

	@Override
	public List<VendorEntity> toEntity(List<VendorDTO> lstD) {
		return lstD.stream().map(e -> toEntity(e)).toList();
	}
}
