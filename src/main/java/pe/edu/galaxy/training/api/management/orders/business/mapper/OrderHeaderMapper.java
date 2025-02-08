package pe.edu.galaxy.training.api.management.orders.business.mapper;

import java.util.List;

import pe.edu.galaxy.training.api.management.orders.business.dto.OrderHeaderDTO;
import pe.edu.galaxy.training.api.management.orders.business.dto.vo.OrderHeaderVODTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.OrderHeaderEntity;
import pe.edu.galaxy.training.api.management.orders.business.entity.OrderHeaderVOEntity;

public interface OrderHeaderMapper {
	OrderHeaderDTO toDTO(OrderHeaderEntity e);
	
	OrderHeaderEntity toEntity(OrderHeaderDTO d);

	List<OrderHeaderDTO> toDTO(List<OrderHeaderEntity> lstE);

	List<OrderHeaderEntity> toEntity(List<OrderHeaderDTO> lstD);
	
	List<OrderHeaderVODTO> toVODTO(List<OrderHeaderVOEntity> lstD);
}
