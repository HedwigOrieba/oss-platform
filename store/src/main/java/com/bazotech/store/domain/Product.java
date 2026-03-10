package com.bazotech.store.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString(exclude= {"orders"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="products")
@Entity
public class Product {
	
	/* Extend this to include brand, model */

	/* Unique Identifier for the product item. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	@EqualsAndHashCode.Include
	private Long productId;
	
	@Column(name="product_brand")
	private String brand;
	
	@Column(name="product_model")
	private String model;
	
	@Column(name="product_specification")
	private String specification;

//	@NotNull
//	@ManyToOne
//	@JoinColumn(name = "item_id")
//	private InventoryItem inventoryItem;

	@OneToOne
	@JoinColumn(name = "staging_id")
	private StagedItem stagedItem;

	/* Product Date-of-Creation */
	@NotNull
	private LocalDateTime publishedOn;
	@Column(name="published_on")
	
	@PrePersist 
	public void prePersist() { 
		publishedOn = LocalDateTime.now(); 
	}
	
	/* Product -> Order Relationship ManagedBy: ProductsOrders entity */
	@Builder.Default
	@ManyToMany(mappedBy = "products")
	private List<Order> orders = new ArrayList<>();
	
	/* Helper method for orders */
	public void addOrder(Order order) {
	    if (!orders.contains(order)) {
	        orders.add(order);
	        order.getProducts().add(this);
	    }
	}

	public void removeOrder(Order order) {
	    orders.remove(order);
	    order.getProducts().remove(this);
	}

}
