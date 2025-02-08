package pe.edu.galaxy.training.api.management.orders.business.mapper;

import java.util.List;

import pe.edu.galaxy.training.api.management.orders.business.dto.CountryDTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.CountryEntity;

public interface CountryMapper {
	CountryDTO toDTO(CountryEntity e);

	CountryEntity toEntity(CountryDTO d);

	List<CountryDTO> toDTO(List<CountryEntity> lstE);

	List<CountryEntity> toEntity(List<CountryDTO> lstD);
}
