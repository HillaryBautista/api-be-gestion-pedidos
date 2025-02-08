package pe.edu.galaxy.training.api.management.orders.business.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

	List<T> findAll() throws ServiceException;

	// findById(T t) es generico pero poner long hace que el rendimiento cresca
	// suponiendo que va a recibir un uuid
	Optional<T> findById(Long t) throws ServiceException;

	List<T> findLikeObject(T t) throws ServiceException;

	// Save
	T save(T t) throws ServiceException;

	// Update
	Boolean update(T t) throws ServiceException;

	// Delete

	void delete(T t) throws ServiceException;
}
