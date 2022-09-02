package com.efox.ecommerce.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Component
@Entity
public class Proveedor {

	@Id
	@OnDelete(action = OnDeleteAction.CASCADE)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_proveedor;
	@NotEmpty
	@Column(nullable=false, length=100, unique=true)
	private String nombre;	
	@Column(length=150)
	private String rubro;
	@Column(length = 400)
	private String main_url;
	
	private java.util.Date alta_fecha;
	private java.util.Date baja_fecha;
	private java.util.Date modi_fecha;
	
	
	public Proveedor(@NotEmpty String nombre) {		
		this.nombre = nombre;
	}

	@JsonManagedReference
	@OneToMany(mappedBy="proveedor",fetch=FetchType.EAGER,cascade = CascadeType.REMOVE)
	private Set<Articulo> articulos = new HashSet<Articulo>();	
	
	public String getMain_url() {
		return main_url;
	}

	public void setMain_url(String main_url) {
		this.main_url = main_url;
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

	public Integer getId_proveedor() {
		return id_proveedor;
	}

	public void setId_proveedor(Integer id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRubro() {
		return rubro;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	public Set<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(Set<Articulo> articulos) {
		this.articulos = articulos;
	}

	
	@Override
	public String toString() {
		return "Proveedor [id_proveedor=" + id_proveedor + ", nombre=" + nombre + ", rubro=" + rubro + ", listaUrl="
				+ ", articulos=" + articulos + "]";
	}

	public Proveedor(Integer id_proveedor, @NotEmpty String nombre, @NotEmpty String rubro, 
			Set<Articulo> articulos) {
		super();
		this.id_proveedor = id_proveedor;
		this.nombre = nombre;
		this.rubro = rubro;		
		this.articulos = articulos;
	}

	public Proveedor() {
		
	}
	
	
}