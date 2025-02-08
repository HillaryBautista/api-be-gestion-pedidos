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
import pe.edu.galaxy.training.api.management.orders.business.dto.VendorDTO;
import pe.edu.galaxy.training.api.management.orders.business.service.ServiceException;
import pe.edu.galaxy.training.api.management.orders.business.service.VendorService;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {

	private final VendorService vendorService;

	public VendorController(VendorService vendorService) {
		this.vendorService = vendorService;
	}

	@GetMapping
	ResponseEntity<?> findAll() {

		Map<String, Object> res = new HashMap<>();

		try {
			List<VendorDTO> lstVendorDTO = vendorService.findAll();
			if (lstVendorDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstVendorDTO);

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
			
			Optional<VendorDTO> optVendoroDTO = vendorService.findById(id);
			if (optVendoroDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optVendoroDTO);

		} catch (ServiceException e) {
			e.printStackTrace();
			//res.put("error", "Error interno");
			res.put("error", e.getMessage());
			return ResponseEntity.internalServerError().body(res);
		}
	}
	
	@GetMapping("/find-by-documentnumber")
	ResponseEntity<?> findByDocumentNumber(@RequestParam(value =  "documentNumber", required = false, defaultValue = "") String documentNumber) {

		Map<String, Object> res = new HashMap<>();

		try {
			Optional<VendorDTO> optVendorDTO = vendorService.findByDocumentNumber(documentNumber);
			if (optVendorDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optVendorDTO.get());

		} catch (ServiceException e) {
			//res.put("error", "Error interno");
			res.put("error", e.getMessage());
			return ResponseEntity.internalServerError().body(res);
		}
	}

	@GetMapping("/find-by-name")
	ResponseEntity<?> findLikeObject(@RequestParam(value =  "name", required = false, defaultValue = "") String name) {

		Map<String, Object> res = new HashMap<>();

		try {
			VendorDTO vendorDTO= new VendorDTO();
			vendorDTO.setFirstName(name);
			List<VendorDTO> lstVendorDTO = vendorService.findLikeObject(vendorDTO);
			if (lstVendorDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstVendorDTO);

		} catch (ServiceException e) {
			//res.put("error", "Error interno");
			res.put("error", e.getMessage());
			return ResponseEntity.internalServerError().body(res);
		}
	}
	

	@PostMapping
	ResponseEntity<?> save(@RequestBody  VendorDTO VendorDTO) { //@Valid

		Map<String, Object> res = new HashMap<>();

		try {
			VendorDTO retVendoroDTO = vendorService.save(VendorDTO);
			if (isNull(retVendoroDTO)) {
				res.put("error", "Error al registrar el vendedor");
				return ResponseEntity.badRequest().build();
			}
			return new ResponseEntity<>(retVendoroDTO, HttpStatus.CREATED);

		} catch (ServiceException e) {
			//res.put("error", "Error interno");
			res.put("error", e.getMessage());
			return ResponseEntity.internalServerError().body(res);
		}
	}


	
	@PutMapping("/{id}")
	ResponseEntity<?> update(@PathVariable(value =  "id",required = true) Long id, @RequestBody VendorDTO VendorDTO) {

		log.info("VendoroDTO => {}",VendorDTO);
		
		Map<String, Object> res = new HashMap<>();

		try {
			VendorDTO.setId(id);
			if (vendorService.update(VendorDTO)) {
				res.put("error", "Error al registrar el vendedor");
				return ResponseEntity.badRequest().build();
			}
			res.put("message", "Exito al actualizar el vendedor");
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
			vendorService.delete(VendorDTO.builder().id(id).build());
			res.put("message", "Exito al eliminar el vendedor");
			return new ResponseEntity<>(res, HttpStatus.OK);

		} catch (ServiceException e) {
			res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}
}
