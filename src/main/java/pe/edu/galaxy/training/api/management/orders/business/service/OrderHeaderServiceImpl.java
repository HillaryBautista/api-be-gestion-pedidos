package pe.edu.galaxy.training.api.management.orders.business.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.api.management.orders.business.dto.ClientDTO;
import pe.edu.galaxy.training.api.management.orders.business.dto.OrderDetailDTO;
import pe.edu.galaxy.training.api.management.orders.business.dto.OrderHeaderDTO;
import pe.edu.galaxy.training.api.management.orders.business.dto.ProductDTO;
import pe.edu.galaxy.training.api.management.orders.business.dto.VendorDTO;
import pe.edu.galaxy.training.api.management.orders.business.dto.request.FindByLikeVoPageOrderHeaderRequestDTO;
import pe.edu.galaxy.training.api.management.orders.business.dto.vo.OrderHeaderVODTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.OrderHeaderEntity;
import pe.edu.galaxy.training.api.management.orders.business.entity.OrderHeaderVOEntity;
import pe.edu.galaxy.training.api.management.orders.business.mapper.OrderHeaderMapper;
import pe.edu.galaxy.training.api.management.orders.business.repository.OrderHeaderRepository;
import pe.edu.galaxy.training.api.management.orders.business.repository.vo.OrderHeaderVORepository;

import static java.util.Objects.isNull;

import java.math.BigDecimal;

@Slf4j
@Service
public class OrderHeaderServiceImpl implements OrderHeaderService {

	public final OrderHeaderRepository orderHeaderRepository;
	private final OrderHeaderVORepository orderHeaderVORepository;

	// public final ClienteRespository clienteRespository;

	public final ClientService clientService;
	
	public final VendorService vendorService;

	public final ProductService productService;

	public final OrderHeaderMapper orderHeaderMapper;
	
	@Value("${custom.parameters.ivg}")
	private Float igv;
	
	BigDecimal igvValue = BigDecimal.ZERO;
	BigDecimal subTotalOrder = BigDecimal.ZERO;
    BigDecimal igvOrder = BigDecimal.ZERO;
    BigDecimal totalOrder = BigDecimal.ZERO;

	public OrderHeaderServiceImpl(
			OrderHeaderRepository orderHeaderRepository, 
			OrderHeaderVORepository orderHeaderVORepository,
			ClientService clienteService,
			VendorService vendorService, 
			ProductService productoService, 
			OrderHeaderMapper orderHeaderMapper) {
		this.orderHeaderRepository = orderHeaderRepository;
		this.orderHeaderVORepository = orderHeaderVORepository;
		this.orderHeaderMapper = orderHeaderMapper;
		this.clientService = clienteService;
		this.vendorService = vendorService;
		this.productService = productoService;
	}
	
	@PostConstruct
	public void init() {
	    this.igvValue = BigDecimal.valueOf(igv);
	    log.info("IGV inyectado correctamente: {}", igvValue);
	}

	@Override
	public List<OrderHeaderDTO> findAll() throws ServiceException {
		try {
			List<OrderHeaderEntity> lstOrderHeaderEntity = orderHeaderRepository.findAll();
			if (lstOrderHeaderEntity.isEmpty()) {
				return new ArrayList<>();
			}
			return orderHeaderMapper.toDTO(lstOrderHeaderEntity);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
		// throw new ServiceSecurityException("No implementado por temas de rendimiento, usar
		// findByLikeVO(Visual Object)");
	}

	@Override
	public Optional<OrderHeaderDTO> findById(Long id) throws ServiceException {
		try {
			Optional<OrderHeaderEntity> optOrderHeaderEntity = orderHeaderRepository.findById(id);
			if (optOrderHeaderEntity.isEmpty()) {
				return Optional.empty();
			}
			return Optional.ofNullable(orderHeaderMapper.toDTO(optOrderHeaderEntity.get()));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/*
	 * @Override public List<OrderHeaderDTO> findLikeObject(OrderHeaderDTO
	 * OrderHeaderDTO) throws ServiceSecurityException { throw new
	 * ServiceSecurityException("No implementado por temas de rendimiento, usar findByLikeVO(Visual Object)"
	 * ); }
	 */

	@Transactional
	@Override
	public Long save(OrderHeaderDTO orderHeaderDTO) throws ServiceException {
		try {
			// Verificaciones
			this.validateOrder(orderHeaderDTO);
			this.validateClient(orderHeaderDTO);
			this.validateVendor(orderHeaderDTO);
			
			// Verificar si el producto existe & si existe suficiente stock del producto
			this.validateOrderDetail(orderHeaderDTO.getOrderDetails());
			// Cálculos
			orderHeaderDTO.getOrderDetails().forEach(System.out::println);
			
			// Cálculos
			this.calculateTotals(orderHeaderDTO);

			log.info("orderHeaderDTO =>{}", orderHeaderDTO);

			OrderHeaderEntity orderHeaderEntity = orderHeaderMapper.toEntity(orderHeaderDTO);
		    			
			orderHeaderEntity.getOrderDetails().forEach(item -> item.setOrderHeader(orderHeaderEntity));

			log.info("orderHeaderEntity B =>{}", orderHeaderEntity);

			// Registro
			OrderHeaderEntity retOrderHeaderEntity = orderHeaderRepository.save(orderHeaderEntity);

			if (isNull(retOrderHeaderEntity)) {
				throw new ServiceException("Error al regitrar el pedido");
			}

			// Actualizar stock
			this.updateStock(orderHeaderDTO.getOrderDetails());
			return retOrderHeaderEntity.getId();

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Boolean update(OrderHeaderDTO OrderHeaderDTO) throws ServiceException {

		// TDO

		return false;
	}

	/*
	 * @Transactional
	 * 
	 * @Override public void delete(OrderHeaderDTO OrderHeaderDTO) throws
	 * ServiceSecurityException { throw new ServiceSecurityException("No implementado, anular");
	 * 
	 * }
	 */

	@Override
	public Boolean cancel(Long id, String motivo) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderHeaderVODTO> findByLikeVO(FindByLikeVoPageOrderHeaderRequestDTO reqDto) throws ServiceException {
		try {
			log.info("getClient: "+reqDto.getClient());
			List<OrderHeaderVOEntity> lstPedidoVOEntity = orderHeaderVORepository.findByLikeVO("%" + reqDto.getGloss() + "%", "%" + reqDto.getStatusOrder() + "%","%" + reqDto.getClient() + "%","%" + reqDto.getVendor() + "%");
			if (lstPedidoVOEntity.isEmpty()) {
				return new ArrayList<>();
			}
			return orderHeaderMapper.toVODTO(lstPedidoVOEntity);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Boolean addItem(OrderDetailDTO orderDetailDTO) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Page<OrderHeaderVODTO> findByLikeVOPage(Pageable pageable, FindByLikeVoPageOrderHeaderRequestDTO reqDto)
			throws ServiceException {

		Page<OrderHeaderVOEntity> lstPedidoVOEntity = orderHeaderVORepository.findByLikeVOPage(pageable,
				"%" + reqDto.getGloss() + "%", "%" + reqDto.getClient() + "%");
		

		List<OrderHeaderVODTO> lstPedidoListadoVODTO = orderHeaderMapper.toVODTO(lstPedidoVOEntity.getContent());

		return new PageImpl<>(lstPedidoListadoVODTO, pageable, lstPedidoListadoVODTO.size());
	}

	// Validaciones
	private void validateOrder(OrderHeaderDTO orderHeaderDTO) throws ServiceException {

		if (isNull(orderHeaderDTO) || isNull(orderHeaderDTO.getOrderDetails())) {
			throw new ServiceException("El pedido no es válido, es nulo no cuenta con su detalle respectivo");
		}
		/*
		 * if (isNull(orderHeaderDTO.getOrderDetails())) { throw new
		 * ServiceSecurityException("El pedido no válido, no cuenta con su detalle respectivo");
		 * }
		 */
	}

	private void validateClient(OrderHeaderDTO orderHeaderDTO) throws ServiceException {
		// Verficar si el cliente existe, verificar su linea de credido,...

		if (isNull(orderHeaderDTO.getClient()) || isNull(orderHeaderDTO.getClient().getId())) {
			throw new ServiceException("El cliente del pedido requerido");
		}

		Long idClient = orderHeaderDTO.getClient().getId();

		Optional<ClientDTO> optClientDTO = this.clientService.findById(idClient);

		if (optClientDTO.isEmpty()) {
			throw new ServiceException(String.format("El cliente con el id =%s no es valido", idClient));
		}
		
		orderHeaderDTO.setClient(optClientDTO.get());
	}

	private void validateVendor(OrderHeaderDTO orderHeaderDTO) throws ServiceException {
		// Verficar si el cliente existe, verificar su linea de credido,...

		if (isNull(orderHeaderDTO.getVendor()) || isNull(orderHeaderDTO.getVendor().getId())) {
			throw new ServiceException("El vendedor del pedido requerido");
		}

		Long idVendor = orderHeaderDTO.getVendor().getId();

		Optional<VendorDTO> optVendorDTO = this.vendorService.findById(idVendor);

		if (optVendorDTO.isEmpty()) {
			throw new ServiceException(String.format("El vendedor con el id =%s no es valido", idVendor));
		}
		
		orderHeaderDTO.setVendor(optVendorDTO.get());
	}

	private ProductDTO validateProduct(ProductDTO productDTO, Integer quantity) throws ServiceException {
		// Verficar si el producto existe, si la cantidad es suficiente,...

		if (isNull(productDTO) || isNull(productDTO.getId())) {
			throw new ServiceException("El producto del pedido es requerido");
		}

		Long idProduct = productDTO.getId();

		Optional<ProductDTO> optProductDTO = this.productService.findById(idProduct);

		if (optProductDTO.isEmpty()) {
			throw new ServiceException(String.format("El producto con el id =%s no es valido", idProduct));
		}

		ProductDTO dbProductDTO = optProductDTO.get();
		
		if (dbProductDTO.getQuantity().compareTo(quantity) < 0) {
		    throw new ServiceException(
		        String.format("La cantidad solicitada del producto id =%s no es suficiente, solo existe %s unidades", 
		                      idProduct, dbProductDTO.getQuantity()));
		}
		
		//productDTO = dbProductDTO;
		//log.info("productDTO =>{}", productDTO);
		return dbProductDTO;
		/**
		 * Nota: El método compareTo() devuelve: 0 si los dos valores de tipo BigDecimal
		 * son iguales. Un valor negativo si el primer BigDecimal es menor que el
		 * segundo. Un valor positivo si el primer BigDecimal es mayor que el segundo.
		 * Entonces, compareTo(quantity) < 0 verifica si la cantidad del producto es
		 * menor que la cantidad solicitada.
		 */

	}

	private void validateOrderDetail(List<OrderDetailDTO> items) throws ServiceException {

		for (OrderDetailDTO orderDetail : items) {

			if (isNull(orderDetail.getQuantity()) || orderDetail.getQuantity() <= 0) {
				throw new ServiceException("La cantidad solicitada no es válido");
			}

			// Producto
			ProductDTO productDTO = orderDetail.getProduct();
	        
			//log.info("productDTO =>{}", productDTO);

			if (isNull(productDTO)) {
				throw new ServiceException("El producto del item no válido");
			}
			productDTO = this.validateProduct(productDTO, orderDetail.getQuantity());

			log.info("productDTO =>{}", productDTO);
			//log.info("productDTO.getPrice() =>{}", productDTO.getPrice());
			orderDetail.setProduct(productDTO);
			orderDetail.setPrice(productDTO.getPrice());
		}
	}
	
	private void calculateTotals(OrderHeaderDTO orderHeaderDTO) throws ServiceException {
	    // Inicializar los valores totales a cero
	    subTotalOrder = BigDecimal.ZERO;
	    igvOrder = BigDecimal.ZERO;
	    totalOrder = BigDecimal.ZERO;

	    for (OrderDetailDTO orderDetail : orderHeaderDTO.getOrderDetails()) {
	        // Convertir cantidad a BigDecimal para hacer operaciones
	        BigDecimal quantity = BigDecimal.valueOf(orderDetail.getQuantity());
	        BigDecimal price = orderDetail.getPrice();

	        if (isNull(price) || price.compareTo(BigDecimal.ZERO) <= 0) {
	            throw new ServiceException("El precio del producto no es válido");
	        }

	        // Calcular los totales del detalle
	        BigDecimal subTotalItem = price.multiply(quantity); // subTotal = cantidad * precio
	        BigDecimal igvItem = subTotalItem.multiply(igvValue); // IGV = subtotal * igv
	        BigDecimal totalItem = subTotalItem.add(igvItem); // Total = subTotal + IGV

	        // Asignar valores al detalle
	        orderDetail.setSubtotal(subTotalItem);
	        orderDetail.setIgv(igvItem);
	        orderDetail.setTotal(totalItem);

	        // Sumar los valores al total general
	        subTotalOrder = subTotalOrder.add(subTotalItem);
	        igvOrder = igvOrder.add(igvItem);
	        totalOrder = totalOrder.add(totalItem);
	    }

	    // Asignar valores al encabezado del pedido
	    orderHeaderDTO.setSubtotal(subTotalOrder);
	    orderHeaderDTO.setIgv(igvOrder);
	    orderHeaderDTO.setTotal(totalOrder);

	    log.info("Cálculos completados: SubTotal = {}, IGV = {}, Total = {}", subTotalOrder, igvOrder, totalOrder);
	}

	private void updateStock(List<OrderDetailDTO> items) throws ServiceException {
		for (OrderDetailDTO orderDetail : items) {
			// Actualizar el stock
			if (!productService.updateQuantity(orderDetail.getProduct().getId(), orderDetail.getQuantity())) {
				throw new ServiceException("Error al actualizar la cantidad solicitada del producto");
			}

		}
	}

}
