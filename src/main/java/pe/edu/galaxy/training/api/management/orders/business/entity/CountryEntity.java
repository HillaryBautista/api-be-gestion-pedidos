package pe.edu.galaxy.training.api.management.orders.business.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "CountryEntity")
@Table(name = "country")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "El nombre oficial es obligatorio")
    @Size(max = 250, message = "El nombre oficial no debe exceder los 250 caracteres")
    @Column(name = "official_name")
    private String officialName;

    @NotBlank(message = "El nombre común es obligatorio")
    @Size(max = 250, message = "El nombre común no debe exceder los 250 caracteres")
    @Column(name = "common_name")
    private String commonName;

    @NotBlank(message = "El código ISO es obligatorio")
    @Pattern(regexp = "[A-Z]{3}", message = "El código ISO debe tener 3 caracteres en mayúsculas")
    @Column(name = "iso_code")
    private String isoCode;

    @Size(max = 250, message = "Las notas no deben exceder los 250 caracteres")
    @Column(name = "notes")
    private String notes;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "[01]", message = "El estado debe ser '1' (activo) o '0' (inactivo)")
    @Column(name = "status", columnDefinition = "CHAR(1) DEFAULT '1'")
    private String status = "1"; // Valor por defecto: '1' (activo)

    @PrePersist
    void setStatus() {
        if (this.status == null || this.status.isEmpty()) {
            this.status = "1"; // Asigna '1' si no se ha especificado un estado
        }
    }
}