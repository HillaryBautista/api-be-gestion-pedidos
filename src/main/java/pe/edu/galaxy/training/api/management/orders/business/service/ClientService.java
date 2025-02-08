package pe.edu.galaxy.training.api.management.orders.business.service;

import java.util.Optional;

import pe.edu.galaxy.training.api.management.orders.business.dto.ClientDTO;

public interface ClientService extends GenericService<ClientDTO> {
	Optional<ClientDTO> findByRuc(String ruc) throws ServiceException;
}
