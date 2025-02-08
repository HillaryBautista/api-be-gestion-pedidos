package pe.edu.galaxy.training.api.management.orders.business.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.galaxy.training.api.management.orders.business.commons.DocumentType;

@Entity(name = "VendorEntity")
@Table(name = "vendor")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VendorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(max = 150, message = "El apellido paterno no debe exceder los 150 caracteres")
    @Column(name = "paternal_surname", nullable = false, length = 150)
    private String paternalSurname;

    @Size(max = 150, message = "El apellido materno no debe exceder los 150 caracteres")
    @Column(name = "maternal_surname", length = 150)
    private String maternalSurname;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 250, message = "El nombre no debe exceder los 250 caracteres")
    @Column(name = "first_name", nullable = false, length = 250)
    private String firstName;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "Debe proporcionar un correo electrónico válido")
    @Size(max = 150, message = "El correo electrónico no debe exceder los 150 caracteres")
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;
    
    @NotBlank(message = "El tipo de documento es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false, length = 10)
    private DocumentType documentType;

    @NotBlank(message = "El número de documento es obligatorio")
    @Size(max = 15, message = "El número de documento no debe exceder los 15 caracteres")
    @Pattern(regexp = "\\d+", message = "El número de documento debe contener solo números")
    @Column(name = "document_number", nullable = false, unique = true, length = 15)
    private String documentNumber;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "[01]", message = "El estado debe ser '1' (activo) o '0' (inactivo)")
    @Column(name = "status", nullable = false, length = 1)
    private String status = "1"; // Valor por defecto: '1' (activo)

    @PrePersist
    void setStatus() {
        if (this.status == null || this.status.isEmpty()) {
            this.status = "1"; // Asigna '1' si no se ha especificado un estado
        }
    }
}
