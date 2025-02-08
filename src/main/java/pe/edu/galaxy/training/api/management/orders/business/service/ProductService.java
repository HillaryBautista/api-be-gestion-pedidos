package pe.edu.galaxy.training.api.management.orders.business.service;

import pe.edu.galaxy.training.api.management.orders.business.dto.ProductDTO;

public interface ProductService extends GenericService<ProductDTO> {
	Boolean updateQuantity(Long id, Integer quantity) throws ServiceException;
}
