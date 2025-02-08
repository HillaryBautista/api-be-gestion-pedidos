package pe.edu.galaxy.training.api.management.orders.security.service.authentication;

import pe.edu.galaxy.training.api.management.orders.security.dto.request.LoginRequestDTO;
import pe.edu.galaxy.training.api.management.orders.security.dto.response.LoginResponseDTO;

public interface AuthenticationService {

	LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws Exception;
}
