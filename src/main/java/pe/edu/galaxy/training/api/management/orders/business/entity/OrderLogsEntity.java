package pe.edu.galaxy.training.api.management.orders.business.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "OrderLogsEntity")
@Table(name = "order_logs")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderLogsEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "El nombre del estado es obligatorio")
    @Column(name = "status_name", nullable = false, length = 50)
    private String statusName;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @NotNull(message = "La fecha de creación es obligatoria")
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @NotNull(message = "El ID de la orden es obligatorio")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderHeaderEntity orderHeader;

    @PrePersist
    void setCreationDate() {
        this.creationDate = LocalDateTime.now();
    }
}
