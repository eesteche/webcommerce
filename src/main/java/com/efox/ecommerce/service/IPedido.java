package com.efox.ecommerce.service;

import java.util.List;

import com.efox.ecommerce.dto.PedidoDto;

public interface IPedido {

	public PedidoDto updatePedido(PedidoDto pedidoDto);

	public String deletePedido(Integer id);

	public PedidoDto addPedido(PedidoDto pedidoDto);

	public List<PedidoDto> getAllPedidos();
	
	public PedidoDto getPedidoById(Integer id);

}
