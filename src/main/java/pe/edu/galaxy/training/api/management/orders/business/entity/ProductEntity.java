package pe.edu.galaxy.training.api.management.orders.business.entity;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table; //java17 en adelante
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "ProductEntity")
@Table(name = "product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@NotBlank(message = "El nombre es obligatorio")
	@Size(max = 360, message = "El nombre no debe exceder los 360 caracteres")
	@Column(name = "name", nullable = false, length = 360)
	private String name;
	
	@NotNull(message = "El precio es obligatorio")
	@Positive(message = "El precio debe ser un valor positivo")
	@Column(name = "price", nullable = false, precision = 10, scale = 2)
	private BigDecimal price;
	
	@NotNull(message = "La cantidad es obligatoria")
	@Positive(message = "La cantidad debe ser un valor positivo")
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@Size(max = 20, message = "El garantía no debe exceder los 20 caracteres")
	@Column(name = "warranty", length = 20)
	private String warranty;
	
	@NotNull(message = "La categoría es obligatoria")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", nullable = false)
	private CategoryEntity category;
	
	@NotBlank(message = "El estado es obligatorio")
	@Pattern(regexp = "[01]", message = "El estado debe ser '1' (activo) o '0' (inactivo)")
	@Column(name = "status", nullable = false, length = 1)
	private String status = "1"; // Valor por defecto: '1' (activo)
	
	@PrePersist
	void setStatus() {
		this.status="1";
	}
	
	public void increaseStock(int quantity) {
	    this.quantity += quantity;
	}

	public void decreaseStock(int quantity) {
	    if (this.quantity >= quantity) {
	        this.quantity -= quantity;
	    } else {
	        throw new RuntimeException("Stock insuficiente para reducir " + quantity);
	    }
	}
}
