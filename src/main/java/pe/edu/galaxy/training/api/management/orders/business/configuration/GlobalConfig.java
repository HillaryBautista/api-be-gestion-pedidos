package pe.edu.galaxy.training.api.management.orders.business.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfig {

	/*
	 * Esto permite que otros componentes puedan inyectar y reutilizar la instancia
	 * del objeto (en este caso, un ModelMapper) en lugar de crearla manualmente.
	 */
	@Bean
	ModelMapper modelMapper() {
		//return new ModelMapper();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setFieldMatchingEnabled(true)
				.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
		return modelMapper;
	}
}
