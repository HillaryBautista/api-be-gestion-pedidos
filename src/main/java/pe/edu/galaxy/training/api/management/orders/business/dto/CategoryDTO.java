package pe.edu.galaxy.training.api.management.orders.business.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO {

    private Long id;

    @NotBlank(message = "El nombre corto es obligatorio")
    @Size(max = 60, message = "El nombre corto no debe exceder los 60 caracteres")
    private String shortName;

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(max = 260, message = "El nombre completo no debe exceder los 260 caracteres")
    private String fullName;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "[01]", message = "El estado debe ser '1' (activo) o '0' (inactivo)")
    private String status = "1"; // Valor por defecto: '1' (activo)

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
