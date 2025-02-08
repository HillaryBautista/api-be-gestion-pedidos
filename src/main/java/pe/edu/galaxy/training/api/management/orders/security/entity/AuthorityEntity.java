package pe.edu.galaxy.training.api.management.orders.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "seg_authority")
@Data
public class AuthorityEntity{

	@Id
	@Column(name = "id") 
	private Long id = 0L;

	@Column(name = "name")
	private String name = "";

}
