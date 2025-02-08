package pe.edu.galaxy.training.api.management.orders.business.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import pe.edu.galaxy.training.api.management.orders.business.dto.OrderHeaderDTO;
import pe.edu.galaxy.training.api.management.orders.business.dto.vo.OrderHeaderVODTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.OrderDetailEntity;
import pe.edu.galaxy.training.api.management.orders.business.entity.OrderHeaderEntity;
import pe.edu.galaxy.training.api.management.orders.business.entity.OrderHeaderVOEntity;

@Component
public class OrderHeaderMapperImpl implements OrderHeaderMapper {
	private final ModelMapper modelMapper;

    public OrderHeaderMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

	@Override
	public OrderHeaderDTO toDTO(OrderHeaderEntity e) {
		return modelMapper.map(e, OrderHeaderDTO.class);
	}

	@Override
	public OrderHeaderEntity toEntity(OrderHeaderDTO d) {
		return modelMapper.map(d, OrderHeaderEntity.class);
	}


	@Override
	public List<OrderHeaderDTO> toDTO(List<OrderHeaderEntity> lstE) {
		return lstE.stream().map(e -> toDTO(e)).toList();
	}

	@Override
	public List<OrderHeaderEntity> toEntity(List<OrderHeaderDTO> lstD) {
		return lstD.stream().map(e -> toEntity(e)).toList();
	}

	@Override
	public List<OrderHeaderVODTO> toVODTO(List<OrderHeaderVOEntity> lstD) {
		return lstD.stream().map(e-> {
			 return modelMapper.map(e,OrderHeaderVODTO.class);
		}).toList();	
	}
}
