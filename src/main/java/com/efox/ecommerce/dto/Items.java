package com.efox.ecommerce.dto;

import com.efox.ecommerce.model.ArticuloPedido;

public class Items {
	private Integer id;
	private Integer quantity;
	private Double price;
	private String description;
	private String code;
	
	public Items() {
		
	}
	public Items(ArticuloPedido artPedido) {			
		this.id = artPedido.getId_articulopedido();
		this.quantity = artPedido.getCantidad();
		this.price = artPedido.getArticulo().getPrecio();
		this.description = artPedido.getArticulo().getDescripcion();
		this.code = artPedido.getArticulo().getCodigo();			
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
