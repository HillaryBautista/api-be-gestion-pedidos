package pe.edu.galaxy.training.api.management.orders.business.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FindByLikeVoPageOrderHeaderRequestDTO {

	private String gloss;
	
	private String statusOrder;
	
	private String client;
	
	private String vendor;

	public String getGloss() {
        return gloss;
    }

    public void setGloss(String gloss) {
        this.gloss = gloss;
    }
    
    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }
    
	public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
    
    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}
