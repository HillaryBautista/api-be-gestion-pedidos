package pe.edu.galaxy.training.api.management.orders.business.mapper;

import java.util.List;

import pe.edu.galaxy.training.api.management.orders.business.dto.StatusOrderDTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.StatusOrderEntity;

public interface StatusOrderMapper {
	StatusOrderDTO toDTO(StatusOrderEntity e);
		
	StatusOrderEntity toEntity(StatusOrderDTO d);

	List<StatusOrderDTO> toDTO(List<StatusOrderEntity> lstE);

	List<StatusOrderEntity> toEntity(List<StatusOrderDTO> lstD);
}
