package pe.edu.galaxy.training.api.management.orders.business.mapper;

import java.util.List;

import pe.edu.galaxy.training.api.management.orders.business.dto.OrderDetailDTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.OrderDetailEntity;

public interface OrderDetailMapper {
	OrderDetailDTO toDTO(OrderDetailEntity e);
	
	OrderDetailEntity toEntity(OrderDetailDTO d);

	List<OrderDetailDTO> toDTO(List<OrderDetailEntity> lstE);

	List<OrderDetailEntity> toEntity(List<OrderDetailDTO> lstD);
}
