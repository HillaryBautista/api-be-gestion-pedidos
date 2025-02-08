package pe.edu.galaxy.training.api.management.orders.business.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.api.management.orders.business.dto.ProductDTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.CategoryEntity;
import pe.edu.galaxy.training.api.management.orders.business.entity.ProductEntity;
import pe.edu.galaxy.training.api.management.orders.business.mapper.ProductMapper;
import pe.edu.galaxy.training.api.management.orders.business.repository.CategoryRepository;
import pe.edu.galaxy.training.api.management.orders.business.repository.ProductRepository;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	// incluimos el ProductRepository por inyeccion
	public final ProductRepository productRepository;
	public final ProductMapper productMapper;
	
	public final CategoryRepository categoryRepository;

	// constructor
	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<ProductDTO> findAll() throws ServiceException {
		try {
			// return productRepository.findAll(); //No se puede por que regresa un
			// repositoru se usa mapper
			// return productMapper.toDTO(productRepository.findAll());
			return productMapper.toDTO(productRepository.findLikeName("%"));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<ProductDTO> findById(Long id) throws ServiceException {
		try {
			ProductEntity productEntity = productRepository.findById(id)
					.orElseThrow(() -> new ServiceException(String.format("No existe producto con el id %s", id)));
			return Optional.ofNullable(productMapper.toDTO(productEntity));
	} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ProductDTO> findLikeObject(ProductDTO productDTO) throws ServiceException {
		try {
			return productMapper.toDTO(productRepository.findLikeName('%' + productDTO.getName() + '%'));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ProductDTO save(ProductDTO productDTO) throws ServiceException {
		try {
			return productMapper.toDTO(productRepository.save(productMapper.toEntity(productDTO)));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Boolean update(ProductDTO productDTO) throws ServiceException {
		log.info("ProductoDTO => {}", productDTO);
		try {
			Optional<ProductEntity> optProductEntity = productRepository.findById(productDTO.getId());
			log.info("optProductEntity => {}", optProductEntity);
			if (optProductEntity.isEmpty()) {
				return false;
				/*
				 * ProductoDTO retProductoDTO = this.save(productoDTO); return
				 * isNull(retProductoDTO);
				 */
			}

			// Obtener la categoría desde la base de datos usando el ID
	        CategoryEntity categoryEntity = null;
	        if (productDTO.getCategory() != null && productDTO.getCategory().getId() != null) {
	            categoryEntity = categoryRepository.findById(productDTO.getCategory().getId())
	                                .orElseThrow(() -> new ServiceException("Categoría no encontrada con ID: " + productDTO.getCategory().getId()));
	        }

	        // Copiar propiedades de ProductDTO a ProductEntity
	        ProductEntity prmProductEntity = optProductEntity.get();
	        BeanUtils.copyProperties(productDTO, prmProductEntity, "id", "category"); // Excluir 'id' y 'category'
	        
	        // Asignar la categoría encontrada
	        prmProductEntity.setCategory(categoryEntity);
	        prmProductEntity.setStatus("1");
	        log.info("prmProductEntity => {}", prmProductEntity);
	        
	        // Guardar los cambios en la base de datos
	        ProductEntity retProductEntity = productRepository.save(prmProductEntity);
	        return isNull(retProductEntity);
	        
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public void delete(ProductDTO productDTO) throws ServiceException {
		log.info("ProductoDTO => {}", productDTO);
		try {
			/*
			 * Optional<ProductoEntity> optProductoEntity =
			 * productoRespository.findById(productoDTO.getId()); if
			 * (!optProductoEntity.isEmpty()) { ProductoEntity prmProductoEntity =
			 * optProductoEntity.get(); prmProductoEntity.setEstado("0");
			 * productoRespository.save(prmProductoEntity); }
			 */
			productRepository.delete(productDTO.getId());

		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public Boolean updateQuantity(Long id, Integer cantidad) throws ServiceException {
		try {
			productRepository.updateQuantity(id, cantidad);
			return true;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
