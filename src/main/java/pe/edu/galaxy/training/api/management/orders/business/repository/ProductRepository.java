package pe.edu.galaxy.training.api.management.orders.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.training.api.management.orders.business.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	// Nativo (Default)

	// JPQL (Entity)
	@Query("select p from ProductEntity p where upper(p.name) like upper(:name) and p.status='1'")
	List<ProductEntity> findLikeName(@Param("name") String name);

	// SQL (Table & View)
	@Modifying
	@Query(value = "update product set status='0' where id=:id", nativeQuery = true)
	void delete(@Param("id") Long id);
	
	@Modifying
	@Query(value = "update product set quantity=:quantity where id=:id", nativeQuery = true)
	void updateQuantity(@Param("id") Long id, @Param("quantity") Integer quantity);

	/**
	 * Nota: el query nativo es más veloz que JPQL
	 */
}
