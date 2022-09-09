package com.efox.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.efox.ecommerce.model.Articulo;
import com.efox.ecommerce.model.ArticuloPedido;
import com.google.gson.Gson;

public class PedidoDto {

	private Buyer buyer;
	private List<Items> items = new ArrayList<Items>();
	private String state;
	private Integer order_id;
	private static Items item;
	public PedidoDto() {
		
	}
	
	public String toString() { return new Gson().toJson(this); }
	
	public Buyer getBuyer() {
		return buyer;
	}

	
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}
	
	public void setItemsFromArticulos(List<Articulo> articulos) {
		
		articulos.stream().forEach(art -> {
			item.setCode(art.getCodigo());
			item.setDescription(art.getDescripcion());
			item.setId(art.getId_articulo());
			
		});
		
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	
	public double getTotalValue() {
	    double results = 0;
	    for(Items item: this.items){
	        results += (item.getQuantity() * item.getPrice());
	    }
	    return results;
	}
	
}
