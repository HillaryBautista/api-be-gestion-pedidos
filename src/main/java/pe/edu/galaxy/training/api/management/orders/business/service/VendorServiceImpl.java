package pe.edu.galaxy.training.api.management.orders.business.service;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.api.management.orders.business.dto.VendorDTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.VendorEntity;
import pe.edu.galaxy.training.api.management.orders.business.mapper.VendorMapper;
import pe.edu.galaxy.training.api.management.orders.business.repository.VendorRepository;

@Slf4j
@Service
public class VendorServiceImpl implements VendorService {

	// incluimos el VendorRepository por inyeccion
	public final VendorRepository vendorRepository;
	public final VendorMapper vendorMapper;

	// constructor
	public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
		this.vendorRepository = vendorRepository;
		this.vendorMapper = vendorMapper;
	}

	@Override
	public List<VendorDTO> findAll() throws ServiceException {
		try {
			// return VendorRepository.findAll(); //No se puede por que regresa un
			// repositoru se usa mapper
			// return VendorMapper.toDTO(VendorRepository.findAll());
			return vendorMapper.toDTO(vendorRepository.findLikeName("%"));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<VendorDTO> findById(Long id) throws ServiceException {
		try {
			VendorEntity VendorEntity = vendorRepository.findById(id)
					.orElseThrow(() -> new ServiceException(String.format("No existe empleado con el id %s", id)));
			return Optional.ofNullable(vendorMapper.toDTO(VendorEntity));
	} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<VendorDTO> findLikeObject(VendorDTO vendorDTO) throws ServiceException {
		try {
			return vendorMapper.toDTO(vendorRepository.findLikeName('%' + vendorDTO.getFirstName() + '%'));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public VendorDTO save(VendorDTO vendorDTO) throws ServiceException {
		try {
			return vendorMapper.toDTO(vendorRepository.save(vendorMapper.toEntity(vendorDTO)));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Boolean update(VendorDTO vendorDTO) throws ServiceException {
		log.info("VendoroDTO => {}", vendorDTO);
		try {
			Optional<VendorEntity> optVendorEntity = vendorRepository.findById(vendorDTO.getId());
			log.info("optVendorEntity => {}", optVendorEntity);
			if (optVendorEntity.isEmpty()) {
				return false;
				/*
				 * VendoroDTO retVendoroDTO = this.save(VendoroDTO); return
				 * isNull(retVendoroDTO);
				 */
			}
	        // Copiar propiedades de VendorDTO a VendorEntity
	        VendorEntity prmVendorEntity = optVendorEntity.get();
	        BeanUtils.copyProperties(vendorDTO, prmVendorEntity); 
	        
	        prmVendorEntity.setStatus("1");
	        log.info("prmVendorEntity => {}", prmVendorEntity);
	        
	        // Guardar los cambios en la base de datos
	        VendorEntity retVendorEntity = vendorRepository.save(prmVendorEntity);
	        return isNull(retVendorEntity);
	        
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public void delete(VendorDTO VendorDTO) throws ServiceException {
		log.info("VendoroDTO => {}", VendorDTO);
		try {
			/*
			 * Optional<VendoroEntity> optVendoroEntity =
			 * VendoroRespository.findById(VendoroDTO.getId()); if
			 * (!optVendoroEntity.isEmpty()) { VendoroEntity prmVendoroEntity =
			 * optVendoroEntity.get(); prmVendoroEntity.setEstado("0");
			 * VendoroRespository.save(prmVendoroEntity); }
			 */
			vendorRepository.delete(VendorDTO.getId());

		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public Optional<VendorDTO> findByDocumentNumber(String documentNumber) throws ServiceException {
		try {
			return Optional.ofNullable(vendorMapper.toDTO(vendorRepository.findByDocumentNumber(documentNumber)
					.orElseThrow(()-> new ServiceException(String.format("El empleado no existe, documentNumber %s",documentNumber)))));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
