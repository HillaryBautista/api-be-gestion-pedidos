package pe.edu.galaxy.training.api.management.orders.business.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FindByLikeVoOrderHeaderRequestDTO {

	private String gloss;
	
	private String client;

	public String getGloss() {
        return gloss;
    }

    public void setGloss(String gloss) {
        this.gloss = gloss;
    }
    
	public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
