package pe.edu.galaxy.training.api.management.orders.business.service;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.api.management.orders.business.dto.ClientDTO;
import pe.edu.galaxy.training.api.management.orders.business.entity.ClientEntity;
import pe.edu.galaxy.training.api.management.orders.business.entity.CountryEntity;
import pe.edu.galaxy.training.api.management.orders.business.mapper.ClientMapper;
import pe.edu.galaxy.training.api.management.orders.business.repository.ClientRepository;
import pe.edu.galaxy.training.api.management.orders.business.repository.CountryRepository;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

	// incluimos el ClientRepository por inyeccion
	public final ClientRepository clientRepository;
	public final ClientMapper clientMapper;

	public final CountryRepository countryRepository;
	
	// constructor
	public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper, CountryRepository countryRepository) {
			this.clientRepository = clientRepository;
			this.clientMapper = clientMapper;
			this.countryRepository = countryRepository;
		}

	@Override
	public List<ClientDTO> findAll() throws ServiceException {
		try {
			// return ClientRepository.findAll(); //No se puede por que regresa un
			// repositoru se usa mapper
			// return ClientMapper.toDTO(ClientRepository.findAll());
			return clientMapper.toDTO(clientRepository.findLikeName("%"));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<ClientDTO> findById(Long id) throws ServiceException {
		try {
			ClientEntity ClientEntity = clientRepository.findById(id)
					.orElseThrow(() -> new ServiceException(String.format("No existe Cliento con el id %s", id)));
			return Optional.ofNullable(clientMapper.toDTO(ClientEntity));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ClientDTO> findLikeObject(ClientDTO ClientDTO) throws ServiceException {
		try {
			return clientMapper.toDTO(clientRepository.findLikeName('%' + ClientDTO.getBusinessName() + '%'));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ClientDTO save(ClientDTO ClientDTO) throws ServiceException {
		try {
			return clientMapper.toDTO(clientRepository.save(clientMapper.toEntity(ClientDTO)));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Boolean update(ClientDTO clientDTO) throws ServiceException {
		log.info("ClientoDTO => {}", clientDTO);
		try {
			Optional<ClientEntity> optClientEntity = clientRepository.findById(clientDTO.getId());
			log.info("optClientEntity => {}", optClientEntity);
			if (optClientEntity.isEmpty()) {
				return false;
				/*
				 * ClientoDTO retClientoDTO = this.save(ClientoDTO); return
				 * isNull(retClientoDTO);
				 */
			}
			
			// Obtener la categoría desde la base de datos usando el ID
	        CountryEntity countryEntity = null;
	        if (clientDTO.getCountry() != null && clientDTO.getCountry().getId() != null) {
	        	countryEntity = countryRepository.findById(clientDTO.getCountry().getId())
	                                .orElseThrow(() -> new ServiceException("País no encontrado con ID: " + clientDTO.getCountry().getId()));
	        }
	        
			// Copiar propiedades de ClientDTO a ClientEntity
			ClientEntity prmClientEntity = optClientEntity.get();
			BeanUtils.copyProperties(clientDTO, prmClientEntity, "id", "country"); // Excluir 'id' y 'country'

			// Asignar la categoría encontrada
			prmClientEntity.setCountry(countryEntity);
			prmClientEntity.setStatus("1");
			log.info("prmClientEntity => {}", prmClientEntity);

			// Guardar los cambios en la base de datos
			ClientEntity retClientEntity = clientRepository.save(prmClientEntity);
			return isNull(retClientEntity);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public void delete(ClientDTO ClientDTO) throws ServiceException {
		log.info("ClientoDTO => {}", ClientDTO);
		try {
			/*
			 * Optional<ClientoEntity> optClientoEntity =
			 * ClientoRespository.findById(ClientoDTO.getId()); if
			 * (!optClientoEntity.isEmpty()) { ClientoEntity prmClientoEntity =
			 * optClientoEntity.get(); prmClientoEntity.setEstado("0");
			 * ClientoRespository.save(prmClientoEntity); }
			 */
			clientRepository.delete(ClientDTO.getId());

		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public Optional<ClientDTO> findByRuc(String ruc) throws ServiceException {
		try {
			return Optional.ofNullable(clientMapper.toDTO(clientRepository.findByRUC(ruc)
					.orElseThrow(()-> new ServiceException(String.format("El cliente no existe, ruc %s",ruc)))));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
