package pe.edu.galaxy.training.api.management.orders.business.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.training.api.management.orders.business.entity.VendorEntity;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity, Long> {
	// Nativo (Default)

	// JPQL (Entity)
	@Query("select p from VendorEntity p where upper(p.firstName) like upper(:firstName) and p.status='1'")
	List<VendorEntity> findLikeName(@Param("firstName") String firstName);

	@Query("select p from VendorEntity p where p.documentNumber =:documentNumber and p.status='1'")
	Optional<VendorEntity> findByDocumentNumber(@Param("documentNumber") String documentNumber);
	
	// SQL (Table & View)
	@Modifying
	@Query(value = "update vendor set status='0' where id=:id", nativeQuery = true)
	void delete(@Param("id") Long id);
}
