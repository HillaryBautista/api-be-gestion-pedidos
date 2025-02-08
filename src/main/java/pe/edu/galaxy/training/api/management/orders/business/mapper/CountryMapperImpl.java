package pe.edu.galaxy.training.api.management.orders.business.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import pe.edu.galaxy.training.api.management.orders.business.dto.CountryDTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.CountryEntity;

@Component
public class CountryMapperImpl implements CountryMapper {
	private final ModelMapper modelMapper;
	
	public CountryMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public CountryDTO toDTO(CountryEntity e) {
		return modelMapper.map(e, CountryDTO.class);
	}

	@Override
	public CountryEntity toEntity(CountryDTO d) {
		return modelMapper.map(d, CountryEntity.class);
	}

	@Override
	public List<CountryDTO> toDTO(List<CountryEntity> lstE) {
		return lstE.stream().map(e -> toDTO(e)).toList();
	}
	@Override
	public List<CountryEntity> toEntity(List<CountryDTO> lstD) {
		return lstD.stream().map(e -> toEntity(e)).toList();
	}

}