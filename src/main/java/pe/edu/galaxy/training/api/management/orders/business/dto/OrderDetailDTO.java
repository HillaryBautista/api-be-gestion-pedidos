package pe.edu.galaxy.training.api.management.orders.business.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailDTO {

    private Long id;

    @JsonIgnore
    @NotNull(message = "El ID de la orden es obligatorio")
    private OrderHeaderDTO orderHeader;

    @NotNull(message = "El ID del producto es obligatorio")
    private ProductDTO product;

    @NotNull(message = "La cantidad es obligatoria")
    @DecimalMin(value = "0.01", inclusive = false, message = "La cantidad debe ser mayor a 0")
    private Integer quantity;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio no puede ser negativo")
    private BigDecimal price;

    @NotNull(message = "El subtotal es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El subtotal no puede ser negativo")
    private BigDecimal subtotal;

    @NotNull(message = "El igv es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El igv no puede ser negativo") //IGV
    private BigDecimal igv;

    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El total no puede ser negativo")
    private BigDecimal total;
    
    private String status = "1"; // Valor por defecto: '1'

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderHeaderDTO getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeaderDTO orderHeader) {
        this.orderHeader = orderHeader;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIgv() {
        return igv;
    }

    public void setIgv(BigDecimal igv) {
        this.igv = igv;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
