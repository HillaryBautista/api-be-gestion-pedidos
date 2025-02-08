package pe.edu.galaxy.training.api.management.orders.business.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.api.management.orders.business.dto.ClientDTO;
import pe.edu.galaxy.training.api.management.orders.business.service.ClientService;
import pe.edu.galaxy.training.api.management.orders.business.service.ServiceException;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping
	ResponseEntity<?> findAll() {

		Map<String, Object> res = new HashMap<>();

		try {
			List<ClientDTO> lstClientDTO = clientService.findAll();
			if (lstClientDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstClientDTO);

		} catch (ServiceException e) {
			//res.put("error", "Error interno");
			res.put("error", e.getMessage());
			return ResponseEntity.internalServerError().body(res);
		}
	}
	
	@GetMapping("/{id}")
	ResponseEntity<?> findById(@PathVariable(value =  "id",required = true) Long id) {

		Map<String, Object> res = new HashMap<>();

		try {
			
			if (isNull(id) || id<=0) {
				res.put("error", String.format("El id=%s ingresado no es válido",id));
				return ResponseEntity.badRequest().body(res);
			}
			
			Optional<ClientDTO> optClientoDTO = clientService.findById(id);
			if (optClientoDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optClientoDTO);

		} catch (ServiceException e) {
			e.printStackTrace();
			//res.put("error", "Error interno");
			res.put("error", e.getMessage());
			return ResponseEntity.internalServerError().body(res);
		}
	}
	
	@GetMapping("/find-by-ruc")
	ResponseEntity<?> findByRUC(@RequestParam(value =  "ruc", required = false, defaultValue = "") String ruc) {

		Map<String, Object> res = new HashMap<>();

		try {
			Optional<ClientDTO> optClienteDTO = clientService.findByRuc(ruc);
			if (optClienteDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optClienteDTO.get());

		} catch (ServiceException e) {
			//res.put("error", "Error interno");
			res.put("error", e.getMessage());
			return ResponseEntity.internalServerError().body(res);
		}
	}

	@GetMapping("/find-by-businessname")
	ResponseEntity<?> findLikeObject(@RequestParam(value =  "name", required = false, defaultValue = "") String name) {

		Map<String, Object> res = new HashMap<>();

		try {
			ClientDTO ClientDTO= new ClientDTO();
			ClientDTO.setBusinessName(name);
			List<ClientDTO> lstClientDTO = clientService.findLikeObject(ClientDTO);
			if (lstClientDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstClientDTO);

		} catch (ServiceException e) {
			//res.put("error", "Error interno");
			res.put("error", e.getMessage());
			return ResponseEntity.internalServerError().body(res);
		}
	}
	

	@PostMapping
	ResponseEntity<?> save(@RequestBody  ClientDTO ClientDTO) { //@Valid

		Map<String, Object> res = new HashMap<>();

		try {
			ClientDTO retClientoDTO = clientService.save(ClientDTO);
			if (isNull(retClientoDTO)) {
				res.put("error", "Error al registrar el cliente");
				return ResponseEntity.badRequest().build();
			}
			return new ResponseEntity<>(retClientoDTO, HttpStatus.CREATED);

		} catch (ServiceException e) {
			//res.put("error", "Error interno");
			res.put("error", e.getMessage());
			return ResponseEntity.internalServerError().body(res);
		}
	}


	
	@PutMapping("/{id}")
	ResponseEntity<?> update(@PathVariable(value =  "id",required = true) Long id, @RequestBody ClientDTO ClientDTO) {

		log.info("ClientoDTO => {}",ClientDTO);
		
		Map<String, Object> res = new HashMap<>();

		try {
			ClientDTO.setId(id);
			if (clientService.update(ClientDTO)) {
				res.put("error", "Error al registrar el cliente");
				return ResponseEntity.badRequest().build();
			}
			res.put("message", "Exito al actualizar el cliente");
			return new ResponseEntity<>(res, HttpStatus.OK);

		} catch (ServiceException e) {
			//res.put("error", "Error interno");
			res.put("error", e.getMessage());
			log.error("Error interno: ", e);
			return ResponseEntity.internalServerError().body(res);
		}
	}

	@DeleteMapping("/{id}")
	ResponseEntity<?> delete(@PathVariable(value =  "id",required = true) Long id) {

		log.info("Id => {}",id);
		Map<String, Object> res = new HashMap<>();
		try {
			clientService.delete(ClientDTO.builder().id(id).build());
			res.put("message", "Exito al eliminar el cliente");
			return new ResponseEntity<>(res, HttpStatus.OK);

		} catch (ServiceException e) {
			res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}
}
