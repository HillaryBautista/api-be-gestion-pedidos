package pe.edu.galaxy.training.api.management.orders.business.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {

	private Long id;
	
	@NotBlank(message = "El nombre es obligatorio")
	@Size(max = 360, message = "El nombre no debe exceder los 360 caracteres")
	private String name;
	
	@NotNull(message = "El precio es obligatorio")
	@Positive(message = "El precio debe ser un valor positivo")
	private BigDecimal price;
	
	@NotNull(message = "La cantidad es obligatoria")
	@Positive(message = "La cantidad debe ser un valor positivo")
	private Integer quantity;
	
	@Size(max = 20, message = "La garantía no debe exceder los 20 caracteres")
	private String warranty;
	
	@NotNull(message = "El ID de la categoría es obligatorio")
	private CategoryDTO category;
	
	@NotBlank(message = "El estado es obligatorio")
	@Pattern(regexp = "[01]", message = "El estado debe ser '1' (activo) o '0' (inactivo)")
	private String status = "1"; // Valor por defecto: '1' (activo)

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
