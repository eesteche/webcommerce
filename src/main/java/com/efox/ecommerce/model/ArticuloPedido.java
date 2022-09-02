package com.efox.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class ArticuloPedido {

	@Id
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_articulopedido;
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_articulo")
	private Articulo articulo;
	@ManyToOne
	@JoinColumn(name="id_pedido")
	private Pedido pedido;
	
	private java.util.Date alta_fecha;
	private java.util.Date baja_fecha;
	private java.util.Date modi_fecha;
	
	private int cantidad;
	public ArticuloPedido(Articulo articulo, Pedido pedido, Integer quantity) {
		this.articulo = articulo;
		this.pedido = pedido;
		this.cantidad = quantity;
	}
	
	public ArticuloPedido() {
		
	}

	public Articulo getArticuloEnPedido() {
		return articulo;
	}

	public void setArticulosEnPedido(Articulo articuloEnPedido) {
		this.articulo = articuloEnPedido;
	}
	
	public Integer getId_articulopedido() {
		return id_articulopedido;
	}

	public void setId_articulopedido(Integer id_articulopedido) {
		this.id_articulopedido = id_articulopedido;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	

	public java.util.Date getAlta_fecha() {
		return alta_fecha;
	}

	public void setAlta_fecha(java.util.Date alta_fecha) {
		this.alta_fecha = alta_fecha;
	}

	public java.util.Date getBaja_fecha() {
		return baja_fecha;
	}

	public void setBaja_fecha(java.util.Date baja_fecha) {
		this.baja_fecha = baja_fecha;
	}

	public java.util.Date getModi_fecha() {
		return modi_fecha;
	}

	public void setModi_fecha(java.util.Date modi_fecha) {
		this.modi_fecha = modi_fecha;
	}

	@Override
	public String toString() {
		return "ArticuloPedido [id_articulopedido=" + id_articulopedido == null ? "" : id_articulopedido + ", articulo=" + articulo.getId_articulo() + ", pedido="
				+ pedido.getId_pedido() + ", cantidad=" + cantidad + "]";
	}
	
	
}
