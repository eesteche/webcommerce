package com.efox.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efox.ecommerce.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Integer>  {

}
