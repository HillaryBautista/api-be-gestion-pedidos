package pe.edu.galaxy.training.api.management.orders.business.mapper;

import java.util.List;

import pe.edu.galaxy.training.api.management.orders.business.dto.VendorDTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.VendorEntity;

public interface VendorMapper {
	VendorDTO toDTO(VendorEntity e);
	
	VendorEntity toEntity(VendorDTO d);

	List<VendorDTO> toDTO(List<VendorEntity> lstE);

	List<VendorEntity> toEntity(List<VendorDTO> lstD);
}
