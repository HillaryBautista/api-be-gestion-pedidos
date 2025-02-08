package pe.edu.galaxy.training.api.management.orders.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.galaxy.training.api.management.orders.security.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	UserEntity findByUserName(String user);

}
