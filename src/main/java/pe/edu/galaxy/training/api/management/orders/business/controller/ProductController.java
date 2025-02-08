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
import pe.edu.galaxy.training.api.management.orders.business.dto.ProductDTO;
import pe.edu.galaxy.training.api.management.orders.business.service.ProductService;
import pe.edu.galaxy.training.api.management.orders.business.service.ServiceException;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	ResponseEntity<?> findAll() {

		Map<String, Object> res = new HashMap<>();

		try {
			List<ProductDTO> lstProductDTO = productService.findAll();
			if (lstProductDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstProductDTO);

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
			
			Optional<ProductDTO> optProductoDTO = productService.findById(id);
			if (optProductoDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optProductoDTO);

		} catch (ServiceException e) {
			e.printStackTrace();
			//res.put("error", "Error interno");
			res.put("error", e.getMessage());
			return ResponseEntity.internalServerError().body(res);
		}
	}

	@GetMapping("/find-by-name")
	ResponseEntity<?> findLikeObject(@RequestParam(value =  "name", required = false, defaultValue = "") String name) {

		Map<String, Object> res = new HashMap<>();

		try {
			ProductDTO productDTO= new ProductDTO();
			productDTO.setName(name);
			List<ProductDTO> lstProductDTO = productService.findLikeObject(productDTO/*productDTO.builder().nombre(nombre).build()*/);
			if (lstProductDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstProductDTO);

		} catch (ServiceException e) {
			//res.put("error", "Error interno");
			res.put("error", e.getMessage());
			return ResponseEntity.internalServerError().body(res);
		}
	}
	

	@PostMapping
	ResponseEntity<?> save(@RequestBody  ProductDTO productDTO) { //@Valid

		Map<String, Object> res = new HashMap<>();

		try {
			ProductDTO retProductoDTO = productService.save(productDTO);
			if (isNull(retProductoDTO)) {
				res.put("error", "Error al registrar el producto");
				return ResponseEntity.badRequest().build();
			}
			return new ResponseEntity<>(retProductoDTO, HttpStatus.CREATED);

		} catch (ServiceException e) {
			//res.put("error", "Error interno");
			res.put("error", e.getMessage());
			return ResponseEntity.internalServerError().body(res);
		}
	}


	
	@PutMapping("/{id}")
	ResponseEntity<?> update(@PathVariable(value =  "id",required = true) Long id, @RequestBody ProductDTO productDTO) {

		log.info("ProductoDTO => {}",productDTO);
		
		Map<String, Object> res = new HashMap<>();

		try {
			productDTO.setId(id);
			if (productService.update(productDTO)) {
				res.put("error", "Error al registrar el producto");
				return ResponseEntity.badRequest().build();
			}
			res.put("message", "Exito al actualizar el producto");
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
			productService.delete(ProductDTO.builder().id(id).build());
			res.put("message", "Exito al eliminar el producto");
			return new ResponseEntity<>(res, HttpStatus.OK);

		} catch (ServiceException e) {
			res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}
}
