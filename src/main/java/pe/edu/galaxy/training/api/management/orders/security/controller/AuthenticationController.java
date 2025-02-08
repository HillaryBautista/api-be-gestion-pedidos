package pe.edu.galaxy.training.api.management.orders.security.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.api.management.orders.business.service.ServiceException;
import pe.edu.galaxy.training.api.management.orders.security.dto.request.LoginRequestDTO;
import pe.edu.galaxy.training.api.management.orders.security.dto.response.ErrorResponseDTO;
import pe.edu.galaxy.training.api.management.orders.security.dto.response.LoginResponseDTO;
import pe.edu.galaxy.training.api.management.orders.security.service.authentication.AuthenticationService;


@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authenticationService;


	// Recomendable
	@PostMapping("/login")
	public ResponseEntity<?> signinHeader(HttpServletResponse response, @RequestBody LoginRequestDTO request) {
		try {
			log.info("login ...");
					String token=authenticationService.login(request).token();
			log.info("token {}",token);
	
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("Authorization", "Bearer " + token);
	
			return ResponseEntity.ok().headers(responseHeaders).build();
		} catch (IllegalArgumentException e) {
	        log.error("Error de autenticación: {}", e.getMessage());
	        return ResponseEntity.status(HttpStatus.FORBIDDEN)
	                .body(new ErrorResponseDTO(e.getMessage(), HttpStatus.FORBIDDEN.value()));
	    } catch (Exception e) {
	        log.error("Error inesperado: ", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ErrorResponseDTO("Ocurrió un error inesperado. Intente nuevamente.", HttpStatus.INTERNAL_SERVER_ERROR.value()));
	    }
	}
	
	/*
	@PostMapping("/login-body")
	public ResponseEntity<Map<String, String>> signinBody(HttpServletResponse response, @RequestBody LoginRequestDTO request) {
		
		log.info("login ...");
		
		String token=authenticationService.login(request).token();
		log.info("token {}",token);

		Map<String, String> resp = new HashMap<>();
		resp.put("token", token);

		return ResponseEntity.ok().body(resp);
	}*/
	
}