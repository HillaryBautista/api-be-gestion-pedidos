package pe.edu.galaxy.training.api.management.orders.security.service.authentication;


import pe.edu.galaxy.training.api.management.orders.security.dto.request.LoginRequestDTO;
import pe.edu.galaxy.training.api.management.orders.security.dto.response.LoginResponseDTO;
import pe.edu.galaxy.training.api.management.orders.security.service.jwt.JwtService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	private final UserDetailsService userDetailsService;
	
	private final JwtService jwtService;
	
	private final AuthenticationManager authenticationManager;

	@Override
	public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
		
		log.info("loginRequestDTO {}", loginRequestDTO);

	    try {
	        authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(loginRequestDTO.userName(), loginRequestDTO.password())
	        );
	    } catch (BadCredentialsException e) {
	        throw new IllegalArgumentException("Usuario o contraseña incorrectos.");
	    } catch (DisabledException e) {
	        throw new IllegalArgumentException("La cuenta de usuario está deshabilitada.");
	    } catch (Exception e) {
	        throw new IllegalArgumentException("Error al autenticar. Intente nuevamente.");
	    }

	    UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDTO.userName());

	    log.info("userDetails {}", userDetails);

	    if (isNull(userDetails)) {
	        throw new IllegalArgumentException("Usuario no encontrado.");
	    }

	    String token = jwtService.generateToken(userDetails);

	    return new LoginResponseDTO(token);
	}
}