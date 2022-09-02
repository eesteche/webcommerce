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


	public static class Buyer {
		String nombre;
		String apellido;
		String email;
		String phone;
		
		public Buyer() {
			
		}
		public Buyer(String nombre, String apellido, String email, String phone) {
			this.nombre = nombre;
			this.apellido = apellido;
			this.email = email;
			this.phone = phone;
		}
		
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getApellido() {
			return apellido;
		}
		public void setApellido(String apellido) {
			this.apellido = apellido;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		
		
	}
	
	public static class Items {
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
	
}
