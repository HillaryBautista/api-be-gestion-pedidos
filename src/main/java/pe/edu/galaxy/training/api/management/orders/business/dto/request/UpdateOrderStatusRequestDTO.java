package pe.edu.galaxy.training.api.management.orders.business.dto.request;

import jakarta.validation.constraints.NotNull;

public class UpdateOrderStatusRequestDTO {

	@NotNull
    private Long newStatus; // Nuevo estado del pedido

	private String comment;
	
    public Long getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Long newStatus) {
        this.newStatus = newStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
