package pe.edu.galaxy.training.api.management.orders.business.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Pattern;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountryDTO {

    private Long id;

    @NotBlank(message = "El nombre oficial es obligatorio")
    @Size(max = 250, message = "El nombre oficial no debe exceder los 250 caracteres")
    private String officialName;

    @NotBlank(message = "El nombre común es obligatorio")
    @Size(max = 250, message = "El nombre común no debe exceder los 250 caracteres")
    private String commonName;

    @NotBlank(message = "El código ISO es obligatorio")
    @Pattern(regexp = "[A-Z]{3}", message = "El código ISO debe tener 3 caracteres en mayúsculas")
    private String isoCode;

    @Size(max = 250, message = "Las notas no deben exceder los 250 caracteres")
    private String notes;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "[01]", message = "El estado debe ser '1' (activo) o '0' (inactivo)")
    private String status = "1"; // Valor por defecto: '1' (activo)

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
