package pe.edu.galaxy.training.api.management.orders.business.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.training.api.management.orders.business.entity.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
	// Nativo (Default)

	// JPQL (Entity)
	@Query("select p from ClientEntity p where upper(p.businessName) like upper(:businessName) and p.status='1'")
	List<ClientEntity> findLikeName(@Param("businessName") String businessName);

	@Query("select p from ClientEntity p where p.ruc =:ruc and p.status='1'")
	Optional<ClientEntity> findByRUC(@Param("ruc") String ruc);
	
	// SQL (Table & View)
	@Modifying
	@Query(value = "update client set status='0' where id=:id", nativeQuery = true)
	void delete(@Param("id") Long id);
}
