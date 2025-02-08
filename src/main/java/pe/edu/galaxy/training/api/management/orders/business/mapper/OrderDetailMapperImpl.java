package pe.edu.galaxy.training.api.management.orders.business.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import pe.edu.galaxy.training.api.management.orders.business.dto.OrderDetailDTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.OrderDetailEntity;

@Component
public class OrderDetailMapperImpl  implements OrderDetailMapper {
	private final ModelMapper modelMapper;

	public OrderDetailMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
    public OrderDetailDTO toDTO(OrderDetailEntity e) {
        return modelMapper.map(e, OrderDetailDTO.class);
    }

    @Override
    public OrderDetailEntity toEntity(OrderDetailDTO d) {
        return modelMapper.map(d, OrderDetailEntity.class);
    }

    @Override
    public List<OrderDetailDTO> toDTO(List<OrderDetailEntity> lstE) {
        return lstE.stream().map(e -> toDTO(e)).toList();
    }

    @Override
    public List<OrderDetailEntity> toEntity(List<OrderDetailDTO> lstD) {
        return lstD.stream().map(e -> toEntity(e)).toList();
    }

}
