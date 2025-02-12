package pe.edu.galaxy.training.api.management.orders.business.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderLogsDTO {

	private Long id;

    @NotBlank(message = "El nombre del estado es obligatorio")
    private String statusName;

    private String comment;

    @NotNull(message = "La fecha de creación es obligatoria")
    private LocalDateTime creationDate;

    @JsonIgnore
    @NotNull(message = "El ID de la orden es obligatorio")
    private OrderHeaderDTO orderHeader;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public OrderHeaderDTO getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeaderDTO orderHeader) {
        this.orderHeader = orderHeader;
    }
}
