package pe.edu.galaxy.training.api.management.orders.business.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "OrderDetailEntity")
@Table(name = "order_detail")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnore
    @NotNull(message = "El ID de la orden es obligatorio")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderHeaderEntity orderHeader; // Relación con OrderHeaderEntity

    @NotNull(message = "El ID del producto es obligatorio")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product; // Relación con ProductEntity
    
    @NotNull(message = "La cantidad es obligatoria")
    @DecimalMin(value = "0.01", inclusive = false, message = "La cantidad debe ser mayor a 0")
    @Column(name = "quantity", nullable = false, precision = 10, scale = 2)
    private Integer quantity;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio no puede ser negativo")
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @NotNull(message = "El subtotal es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El subtotal no puede ser negativo")
    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;
    
    @NotNull(message = "El igv es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El igv no puede ser negativo")
    @Column(name = "igv", nullable = false, precision = 10, scale = 2)
    private BigDecimal igv;
    
    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El total no puede ser negativo")
    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "status", columnDefinition = "CHAR(1) DEFAULT '1'")
    private String status = "1"; // Valor por defecto: '1'
    
    @PrePersist
	void setStatus() {
		this.status="1";
	}
}
