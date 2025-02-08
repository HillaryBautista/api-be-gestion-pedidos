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

@Entity(name = "OrderHeaderEntity")
@Table(name = "order_header")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderHeaderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "El ID del cliente es obligatorio")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client; // Relación con ClientEntity
 
    @NotNull(message = "El ID del vendedor es obligatorio")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "vendor_id", nullable = false)
    private VendorEntity vendor; // Relación con VendorEntity
    
    @NotNull(message = "La glosa es requerida")
    @Size(max = 240, message = "El nombre oficial no debe exceder los 240 caracteres")
	@Column(name ="gloss", nullable = false)
	private String gloss;
    
    @NotNull(message = "La fecha de la orden es obligatoria")
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @NotNull(message = "El subtotal es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El subtotal no puede ser negativo")
    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @NotNull(message = "El igv es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El igv no puede ser negativo")
    @Column(name = "igv", nullable = false, precision = 5, scale = 2)
    private BigDecimal igv;

    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El total no puede ser negativo")
    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @NotNull(message = "El ID del estado es obligatorio")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "status_id", nullable = false)
    private StatusOrderEntity statusOrder; // Relación con StatusOrderEntity
    
    @OneToMany(mappedBy = "orderHeader", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetailEntity> orderDetails;

    @Column(name = "status", columnDefinition = "CHAR(1) DEFAULT '1'")
    private String status = "1"; // Valor por defecto: '1'
    
    @PrePersist
	void setStatus() {
		this.status="1";
		this.orderDate=LocalDateTime.now();
	}
}
