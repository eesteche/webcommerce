package com.efox.ecommerce.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class Pedido {

	@Id
	@OnDelete(action = OnDeleteAction.CASCADE)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_pedido;
	@NotEmpty
	@Column(nullable=false, length=125)
	private String nombre;
	@NotEmpty
	@Column(nullable=false, length=125)
	private String apellido;
	@NotEmpty
	@Column(nullable=false, length=150)
	private String email;
	@Column(length=150)
	private String phone;
	
	@Column(precision=0)
	private java.util.Date alta_fecha;
	private java.util.Date baja_fecha;
	private java.util.Date modi_fecha;
	
	@OneToMany(mappedBy="pedido", cascade = {CascadeType.ALL})
	List<ArticuloPedido>  articuloPedido;
	
	public Pedido() {
		
	}

	public java.util.Date getAlta_fecha() {
		return alta_fecha;
	}

	public void setAlta_fecha(Timestamp alta_fecha) {
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

	public Integer getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Integer id_pedido) {
		this.id_pedido = id_pedido;
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

	public List<ArticuloPedido> getArticuloPedido() {
		return articuloPedido;
	}

	public void setArticuloPedido(List<ArticuloPedido> articuloPedido) {
		this.articuloPedido = articuloPedido;
	}
	

	@Override
	public String toString() {
		return "Pedido [id_pedido=" + id_pedido + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
				+ ", phone=" + phone + ", alta_fecha=" + alta_fecha + ", baja_fecha=" + baja_fecha + ", modi_fecha="
				+ modi_fecha + ", articuloPedido=" + articuloPedido == null ? "null" : articuloPedido + "]";
	}

	public Pedido(Integer id_pedido, @NotEmpty String nombre , @NotEmpty String apellido,  List<Articulo> articulos) {
		super();
		this.id_pedido = id_pedido;
		this.nombre = nombre;
		this.apellido = apellido;
	}
}
