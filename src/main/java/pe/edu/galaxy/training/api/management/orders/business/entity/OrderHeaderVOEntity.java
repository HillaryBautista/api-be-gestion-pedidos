package pe.edu.galaxy.training.api.management.orders.business.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity(name = "OrderHeaderVOEntity")
@Table(name = "view_orders")
public class OrderHeaderVOEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "client")
    private String client; // Relación con ClientEntity
 
    @Column(name = "vendor")
    private String vendor; // Relación con VendorEntity
    
	@Column(name ="gloss")
	private String gloss;
    
    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @Column(name = "igv")
    private BigDecimal igv;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "status_order")
    private String statusOrder; // Relación con StatusOrderEntity
    
    @Column(name = "items")
    private Integer items;
}
