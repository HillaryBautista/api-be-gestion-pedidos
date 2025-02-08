package pe.edu.galaxy.training.api.management.orders.business.repository.vo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.training.api.management.orders.business.entity.OrderHeaderVOEntity;

@Repository
public interface OrderHeaderVORepository extends JpaRepository<OrderHeaderVOEntity, Long>{

	//@Query(value = "select * from view_orders", nativeQuery = true)
	//@Query(value = "select * from view_orders where gloss like :gloss and client like :client", nativeQuery = true)
	@Query(value = "SELECT \r\n"
	        + "    ohea.id, \r\n"
	        + "    ohea.gloss, \r\n"
	        + "    ohea.order_date, \r\n"
	        + "    ohea.subtotal, \r\n"
	        + "    ohea.igv, \r\n"
	        + "    ohea.total, \r\n"
	        + "    CONCAT(sord.full_name) AS status_order, \r\n"
	        + "    CONCAT(CONCAT(clie.ruc, ' - '), clie.business_name) AS client, \r\n"
	        + "    CONCAT(CONCAT(vend.first_name, ' '), CONCAT(vend.paternal_surname, ' '), vend.maternal_surname) AS vendor, \r\n"
	        + "    COUNT(odet.id) AS items \r\n"
	        + "FROM \r\n"
	        + "    order_header ohea \r\n"
	        + "    INNER JOIN client clie ON ohea.client_id = clie.id \r\n"
	        + "    INNER JOIN vendor vend ON ohea.vendor_id = vend.id \r\n"
	        + "    INNER JOIN order_detail odet ON ohea.id = odet.order_id \r\n"
	        + "    INNER JOIN status_order sord ON ohea.status_id = sord.id \r\n"
	        + "WHERE \r\n"
	        + "    ohea.gloss LIKE :gloss \r\n"
	        + "    AND CONCAT(sord.full_name) LIKE :statusOrder \r\n"
	        + "    AND CONCAT(CONCAT(clie.ruc, ' - '), clie.business_name) LIKE :client \r\n"
	        + "    AND CONCAT(CONCAT(vend.first_name, ' '), CONCAT(vend.paternal_surname, ' '), vend.maternal_surname) LIKE :vendor \r\n"
	        + "    AND ohea.status = '1' \r\n"
	        + "    AND odet.status = '1' \r\n"
	        + "GROUP BY \r\n"
	        + "    ohea.id, \r\n"
	        + "    ohea.gloss, \r\n"
	        + "    ohea.order_date, \r\n"
	        + "    ohea.subtotal, \r\n"
	        + "    ohea.igv, \r\n"
	        + "    ohea.total, \r\n"
	        + "    CONCAT(sord.full_name), \r\n"
	        + "    CONCAT(CONCAT(clie.ruc, ' - '), clie.business_name), \r\n"
	        + "    CONCAT(CONCAT(vend.first_name, ' '), CONCAT(vend.paternal_surname, ' '), vend.maternal_surname) \r\n"
	        + "ORDER BY \r\n"
	        + "    ohea.id DESC", 
	        nativeQuery = true)
		List<OrderHeaderVOEntity> findByLikeVO(@Param("gloss") String gloss, @Param("statusOrder") String statusOrder, @Param("client") String client, @Param("vendor") String vendor);
		
		@Query(value = "select * from view_orders where gloss like :gloss and client like :client", nativeQuery = true)
		Page<OrderHeaderVOEntity> findByLikeVOPage(Pageable pageable, @Param("gloss") String gloss, @Param("client") String client);

}
