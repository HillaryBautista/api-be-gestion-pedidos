package pe.edu.galaxy.training.api.management.orders.business.mapper;

import java.util.List;

import pe.edu.galaxy.training.api.management.orders.business.dto.ClientDTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.ClientEntity;

public interface ClientMapper {
	ClientDTO toDTO(ClientEntity e);

	ClientEntity toEntity(ClientDTO d);

	List<ClientDTO> toDTO(List<ClientEntity> lstE);

	List<ClientEntity> toEntity(List<ClientDTO> lstD);
}
