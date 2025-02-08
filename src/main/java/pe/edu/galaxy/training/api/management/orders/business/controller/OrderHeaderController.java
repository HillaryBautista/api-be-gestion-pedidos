package pe.edu.galaxy.training.api.management.orders.business.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.api.management.orders.business.dto.OrderHeaderDTO;
import pe.edu.galaxy.training.api.management.orders.business.dto.request.FindByLikeVoPageOrderHeaderRequestDTO;
import pe.edu.galaxy.training.api.management.orders.business.dto.vo.OrderHeaderVODTO;
import pe.edu.galaxy.training.api.management.orders.business.service.OrderHeaderService;
import pe.edu.galaxy.training.api.management.orders.business.service.ServiceException;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
public class OrderHeaderController {

	private final OrderHeaderService orderHeadService;

	public OrderHeaderController(OrderHeaderService orderHeadService) {
		this.orderHeadService = orderHeadService;
	}

	@GetMapping
	ResponseEntity<?> findAll() {

		Map<String, Object> res = new HashMap<>();

		try {
			List<OrderHeaderDTO> lstOrderHeaderDTO = orderHeadService.findAll();
			if (lstOrderHeaderDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstOrderHeaderDTO);

		} catch (ServiceException e) {
			res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}
	
	@PostMapping("list-vo")
	ResponseEntity<?> findAllVO(@RequestBody FindByLikeVoPageOrderHeaderRequestDTO reqDto) {

		Map<String, Object> res = new HashMap<>();

		try {
			//PedidoRequestDTO pedidoRequestDTO= new PedidoRequestDTO();
			List<OrderHeaderVODTO> lstPedidoListadoVODTO = orderHeadService.findByLikeVO(reqDto);
			if (lstPedidoListadoVODTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstPedidoListadoVODTO);

		} catch (ServiceException e) {
			res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}
	
	@PostMapping("list-vo-page")
	ResponseEntity<?> findAllVO(
			@RequestParam(value =  "page", defaultValue = "0") Integer page,
			@RequestParam(value =  "size", defaultValue = "2") Integer size,
			@RequestParam(value =  "field", defaultValue = "gloss") String field,
			@RequestParam(value =  "order", defaultValue = "ASC") String order,
			@RequestBody FindByLikeVoPageOrderHeaderRequestDTO reqDto) {

		Map<String, Object> res = new HashMap<>();

		try {
			//PedidoRequestDTO pedidoRequestDTO= new PedidoRequestDTO();
			Pageable pageable=PageRequest.of(page, size, Sort.by(Direction.valueOf(order.toUpperCase()), field) );
			Page<OrderHeaderVODTO> pagePedidoListadoVODTO = orderHeadService.findByLikeVOPage(pageable,reqDto);
			if (pagePedidoListadoVODTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(pagePedidoListadoVODTO);

		} catch (ServiceException e) {
			res.put("error", "Error interno");
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
			
			Optional<OrderHeaderDTO> optOrderHeadDTO = orderHeadService.findById(id);
			if (optOrderHeadDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optOrderHeadDTO);

		} catch (ServiceException e) {
			e.printStackTrace();
			//res.put("error", "Error interno");
			res.put("error", e.getMessage());
			return ResponseEntity.internalServerError().body(res);
		}
	}

	@PostMapping
	ResponseEntity<?> save(@RequestBody  OrderHeaderDTO orderHeadDTO) { //@Valid

		Map<String, Object> res = new HashMap<>();

		try {
			Long idOrder = orderHeadService.save(orderHeadDTO);
			if (isNull(idOrder)) {
				res.put("error", "Error al registrar el pedido");
				return ResponseEntity.badRequest().build();
			}
			
			res.put("mensaje", String.format("El pedido fue registrado con exito, su codigo es %s",idOrder));
			
			return new ResponseEntity<>(idOrder, HttpStatus.CREATED);

		} catch (ServiceException e) {
			e.printStackTrace();
			res.put("error", e.getMessage());
			//res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}
}
