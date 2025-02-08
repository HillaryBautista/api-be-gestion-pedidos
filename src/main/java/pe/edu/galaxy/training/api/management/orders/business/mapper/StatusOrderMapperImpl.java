package pe.edu.galaxy.training.api.management.orders.business.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import pe.edu.galaxy.training.api.management.orders.business.dto.StatusOrderDTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.StatusOrderEntity;

@Component
public class StatusOrderMapperImpl implements StatusOrderMapper {
	private final ModelMapper modelMapper;

	public StatusOrderMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public StatusOrderDTO toDTO(StatusOrderEntity e) {
		return modelMapper.map(e, StatusOrderDTO.class);
	}

	@Override
	public List<StatusOrderDTO> toDTO(List<StatusOrderEntity> lstE) {
		return lstE.stream().map(e -> toDTO(e)).toList();
	}

	@Override
	public StatusOrderEntity toEntity(StatusOrderDTO d) {
		return modelMapper.map(d, StatusOrderEntity.class);
	}

	@Override
	public List<StatusOrderEntity> toEntity(List<StatusOrderDTO> lstD) {
		return lstD.stream().map(e -> toEntity(e)).toList();
	}
}
