package com.efox.ecommerce.dto;

public class Buyer {

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
