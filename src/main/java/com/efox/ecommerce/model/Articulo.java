package com.efox.ecommerce.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Component
@Entity
@Table(name="articulo")
public class Articulo {

	@Id
	@OnDelete(action = OnDeleteAction.CASCADE)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_articulo;
	
	
	@NotEmpty
	@Column(nullable=false, length=225)
	private String codigo;
	
	@NotEmpty
	@Column(nullable=false, length=250)
	private String descripcion;
		
	@Column(length=250)
	private String categoria;
	
	@Column(length=150)
	private String marca;
	
	@Column(length=150)
	private String observaciones;
	
	@Column(length=25)
	private String moneda;
	
	@NotNull
	private Double precio;
	
	@Column(length=250)
	private String img_url;
	
	@Column(length=250)
	private String sku;
	
	private java.util.Date alta_fecha;
	private java.util.Date baja_fecha;
	private java.util.Date modi_fecha;
	
	@ManyToOne(cascade= {CascadeType.MERGE, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	@JoinColumn(name="id_proveedor", referencedColumnName="id_proveedor")
	private Proveedor proveedor;
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
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

	
	
	@JsonBackReference
	public Proveedor getProveedor() {
		return proveedor;
	}
	


	public Integer getId_articulo() {
		return id_articulo;
	}

	public void setId_articulo(Integer id_articulo) {
		this.id_articulo = id_articulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	

	@Override
	public String toString() {
		return "{id_articulo=" + id_articulo + ", codigo=" + codigo + ", descripcion=" + descripcion + "}";
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, descripcion, codigo, img_url, marca, precio, sku);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Articulo))
			return false;
		Articulo other = (Articulo) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(codigo, other.codigo) && Objects.equals(img_url, other.img_url)
				&& Objects.equals(marca, other.marca) && Objects.equals(precio, other.precio)
				&& Objects.equals(sku, other.sku);
	}
	
	public boolean IsArticleValid() {
		
		if(this.getDescripcion() == null || this.getPrecio() == null || this.getProveedor() == null || this.getCodigo() == null) {
			return false;
		}
		
		if(this.getPrecio() < 0) {
			return false;
		}
		
		return true;
	}
	
	
	
}