package com.efox.ecommerce.service;

import java.util.List;

import com.efox.ecommerce.dto.ArticuloDto;

public interface IArticulo {

	public ArticuloDto updateArticulo(ArticuloDto Articulo);

	public String deleteArticulo(Integer id);

	public ArticuloDto addArticulo(ArticuloDto ArticuloDto);

	public List<ArticuloDto> getAllArticulos();
	
	public List<ArticuloDto> getArticulosByProveedor(Integer id);
	
	public List<String> getArticulosCategories();
	
	public List<ArticuloDto> getArticulosByCategories(String category);

}
