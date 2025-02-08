package pe.edu.galaxy.training.api.management.orders.business.service;

import java.util.Optional;

import pe.edu.galaxy.training.api.management.orders.business.dto.VendorDTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.VendorEntity;

public interface VendorService extends GenericService<VendorDTO> {
	Optional<VendorDTO> findByDocumentNumber(String ruc) throws ServiceException;
}
