package pe.edu.galaxy.training.api.management.orders.business.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderHeaderDTO {

    private Long id;

    @NotNull(message = "El ID del cliente es obligatorio")
    private ClientDTO client;

    @NotNull(message = "El ID del vendedor es obligatorio")
    private VendorDTO vendor;
    
    @NotNull(message = "La glosa es requerida")
    @Size(max = 240, message = "El nombre oficial no debe exceder los 240 caracteres")
	private String gloss;
    
    @NotNull(message = "La fecha de la orden es obligatoria")
    private LocalDateTime orderDate;

    @NotNull(message = "El subtotal es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El subtotal no puede ser negativo")
    private BigDecimal subtotal;

    @NotNull(message = "El igv es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El igv no puede ser negativo") //IGV
    private BigDecimal igv;

    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El total no puede ser negativo")
    private BigDecimal total;

    @NotNull(message = "El ID del estado es obligatorio")
    private StatusOrderDTO statusOrder;

    private List<OrderDetailDTO> orderDetails;

    private String status = "1"; // Valor por defecto: '1'

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public VendorDTO getVendor() {
        return vendor;
    }

    public void setVendor(VendorDTO vendor) {
        this.vendor = vendor;
    }

    public String getGloss() {
        return gloss;
    }

    public void setGloss(String gloss) {
        this.gloss = gloss;
    }
    
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
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

    public StatusOrderDTO getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrderDTO statusOrder) {
        this.statusOrder = statusOrder;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
