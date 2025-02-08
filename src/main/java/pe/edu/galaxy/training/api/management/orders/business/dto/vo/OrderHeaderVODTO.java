package pe.edu.galaxy.training.api.management.orders.business.dto.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderHeaderVODTO {

    private Long id;

    private String client; // Relación con ClientEntity
 
    private String vendor; // Relación con VendorEntity
    
	private String gloss;
    
    private LocalDateTime orderDate;

    private BigDecimal subtotal;

    private BigDecimal igv;

    private BigDecimal total;

    private String statusOrder; // Relación con StatusOrderEntity
    
    private Integer items;
}
