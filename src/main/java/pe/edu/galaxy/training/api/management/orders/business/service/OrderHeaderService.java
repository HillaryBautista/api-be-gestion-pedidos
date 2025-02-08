package pe.edu.galaxy.training.api.management.orders.business.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.edu.galaxy.training.api.management.orders.business.dto.OrderDetailDTO;
import pe.edu.galaxy.training.api.management.orders.business.dto.OrderHeaderDTO;
import pe.edu.galaxy.training.api.management.orders.business.dto.request.FindByLikeVoPageOrderHeaderRequestDTO;
import pe.edu.galaxy.training.api.management.orders.business.dto.vo.OrderHeaderVODTO;

public interface OrderHeaderService {

	Optional<OrderHeaderDTO> findById(Long id) throws ServiceException;

	Long save(OrderHeaderDTO orderHeaderDTO) throws ServiceException;

	Boolean update(OrderHeaderDTO orderHeaderDTO) throws ServiceException;

	Boolean cancel(Long id, String motive) throws ServiceException; // anular pedido

	List<OrderHeaderDTO> findAll() throws ServiceException;
	
	List<OrderHeaderVODTO> findByLikeVO(FindByLikeVoPageOrderHeaderRequestDTO reqDto) throws ServiceException;
	
	Page<OrderHeaderVODTO> findByLikeVOPage(Pageable pageable, FindByLikeVoPageOrderHeaderRequestDTO reqDto) throws ServiceException;
	
	Boolean addItem(OrderDetailDTO orderDetailDTO) throws ServiceException;
}
