package pe.edu.galaxy.training.api.management.orders.business.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientDTO {

    private Long id;

    @NotBlank(message = "La razón social es obligatoria")
    @Size(max = 260, message = "La razón social no debe exceder los 260 caracteres")
    private String businessName;
    
    @NotBlank(message = "El ruc es obligatorio")
    @Size(min = 11, max = 11, message = "El ruc debe tener 11 caracteres")
    @Pattern(regexp = "^[0-9]{11}$", message = "El ruc debe ser numérico y de 11 dígitos")
    private String ruc;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 400, message = "La dirección no debe exceder los 400 caracteres")
    private String address;

    @Size(max = 15, message = "El número de teléfono no debe exceder los 15 caracteres")
    private String phoneNumber;

    @NotNull(message = "El ID del país es obligatorio")
    private CountryDTO country;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "[01]", message = "El estado debe ser '1' (activo) o '0' (inactivo)")
    private String status = "1"; // Valor por defecto: '1' (activo)

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public CountryDTO getCountry() {
		return country;
	}

	public void setCountry(CountryDTO country) {
		this.country = country;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
