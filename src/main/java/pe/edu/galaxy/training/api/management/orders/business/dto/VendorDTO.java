package pe.edu.galaxy.training.api.management.orders.business.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.galaxy.training.api.management.orders.business.commons.DocumentType;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VendorDTO {

    private Long id;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(max = 150, message = "El apellido paterno no debe exceder los 150 caracteres")
    private String paternalSurname;

    @Size(max = 150, message = "El apellido materno no debe exceder los 150 caracteres")
    private String maternalSurname;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 250, message = "El nombre no debe exceder los 250 caracteres")
    private String firstName;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe ser válido")
    @Size(max = 150, message = "El correo electrónico no debe exceder los 150 caracteres")
    private String email;
    
    @NotBlank(message = "El tipo de documento es obligatorio")
    private DocumentType documentType;

    @NotBlank(message = "El número de documento es obligatorio")
    @Size(max = 15, message = "El número de documento no debe exceder los 15 caracteres")
    @Pattern(regexp = "\\d+", message = "El número de documento debe contener solo números")
    private String documentNumber;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "[01]", message = "El estado debe ser '1' (activo) o '0' (inactivo)")
    private String status = "1"; // Valor por defecto: '1' (activo)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaternalSurname() {
        return paternalSurname;
    }

    public void setPaternalSurname(String paternalSurname) {
        this.paternalSurname = paternalSurname;
    }

    public String getMaternalSurname() {
        return maternalSurname;
    }

    public void setMaternalSurname(String maternalSurname) {
        this.maternalSurname = maternalSurname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
