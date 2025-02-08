package pe.edu.galaxy.training.api.management.orders.business.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import pe.edu.galaxy.training.api.management.orders.business.dto.ClientDTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.ClientEntity;

@Component
public class ClientMapperImpl implements ClientMapper {
	private final ModelMapper modelMapper;
	
	public ClientMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public ClientDTO toDTO(ClientEntity e) {
		return modelMapper.map(e, ClientDTO.class);
	}

	@Override
	public ClientEntity toEntity(ClientDTO d) {
		return modelMapper.map(d, ClientEntity.class);
	}

	@Override
	public List<ClientDTO> toDTO(List<ClientEntity> lstE) {
		return lstE.stream().map(e -> toDTO(e)).toList();
	}
	@Override
	public List<ClientEntity> toEntity(List<ClientDTO> lstD) {
		return lstD.stream().map(e -> toEntity(e)).toList();
	}

}