package pe.edu.galaxy.training.api.management.orders.business.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "ClientEntity")
@Table(name = "client")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "La razón social es obligatoria")
    @Size(max = 260, message = "La razón social no debe exceder los 260 caracteres")
    @Column(name = "business_name")
    private String businessName;

    @NotBlank(message = "El ruc es obligatorio")
    @Size(min = 11, max = 11, message = "El ruc debe tener 11 caracteres")
    @Pattern(regexp = "^[0-9]{11}$", message = "El ruc debe ser numérico y de 11 dígitos")
    @Column(name = "ruc", unique = true)
    private String ruc;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 400, message = "La dirección no debe exceder los 400 caracteres")
    @Column(name = "address")
    private String address;

    @Size(max = 15, message = "El número de teléfono no debe exceder los 15 caracteres")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull(message = "El ID del país es obligatorio")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "country_id", nullable = false)
	private CountryEntity country;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "[01]", message = "El estado debe ser '1' (activo) o '0' (inactivo)")
    @Column(name = "status", columnDefinition = "CHAR(1) DEFAULT '1'")
    private String status = "1"; // Valor por defecto: '1' (activo)
    
	@PrePersist
	void setStatus() {
		this.status="1";
	}
}