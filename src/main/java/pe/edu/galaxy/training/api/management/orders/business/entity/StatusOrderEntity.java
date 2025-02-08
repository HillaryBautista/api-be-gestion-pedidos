package pe.edu.galaxy.training.api.management.orders.business.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "StatusOrderEntity")
@Table(name = "status_order")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatusOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "El nombre corto es obligatorio")
    @Size(max = 60, message = "El nombre corto no debe exceder los 60 caracteres")
    @Column(name = "short_name", nullable = false)
    private String shortName;

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(max = 260, message = "El nombre completo no debe exceder los 260 caracteres")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "[01]", message = "El estado debe ser '1' (activo) o '0' (inactivo)")
    @Column(name = "status", columnDefinition = "CHAR(1) DEFAULT '1'")
    private String status = "1"; // Valor por defecto: '1' (activo)

    @PrePersist
    private void prePersist() {
        this.status = "1";
    }
}
