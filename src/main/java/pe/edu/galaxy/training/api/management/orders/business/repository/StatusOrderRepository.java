package pe.edu.galaxy.training.api.management.orders.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.training.api.management.orders.business.entity.StatusOrderEntity;

@Repository
public interface StatusOrderRepository extends JpaRepository<StatusOrderEntity, Long> {

}
