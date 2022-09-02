package com.efox.ecommerce.service;

import java.util.List;

import com.efox.ecommerce.dto.ProveedorDto;

public interface IProveedor {

	public ProveedorDto updateProveedor(ProveedorDto proveedor);

	public String deleteProveedor(Integer id);

	public ProveedorDto addProveedor(ProveedorDto proveedorDto);

	public List<ProveedorDto> getAllProveedores();

}
