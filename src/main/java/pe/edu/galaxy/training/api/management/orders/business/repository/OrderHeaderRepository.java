package pe.edu.galaxy.training.api.management.orders.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.training.api.management.orders.business.entity.OrderHeaderEntity;

@Repository
public interface OrderHeaderRepository extends JpaRepository<OrderHeaderEntity, Long> {
	
	// SQL (Table & View)
	@Modifying
	@Query(value = "update order_header set status='0' where id=:id", nativeQuery = true)
	void delete(@Param("id") Long id);
		
}
