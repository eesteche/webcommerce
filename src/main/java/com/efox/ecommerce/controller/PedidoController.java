package com.efox.ecommerce.controller;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efox.ecommerce.dto.PedidoDto;
import com.efox.ecommerce.service.IPedido;

@RestController
@RequestMapping("/public/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

	@Qualifier("PedidoServiceImpl")
	@Autowired
	private IPedido PedidoService;

	@GetMapping()
	public ResponseEntity<List<PedidoDto>> getAllPedidos() {
		List<PedidoDto> Pedidoes = PedidoService.getAllPedidos();
		return new ResponseEntity<>(Pedidoes, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<PedidoDto> postPedido(@RequestBody PedidoDto PedidoDto) {
		PedidoDto post = PedidoService.addPedido(PedidoDto);
		return new ResponseEntity<>(post, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> getPedidosProvider(@PathVariable(name = "id") String id) {
		Integer intId = NumberUtils.toInt(id, -1);
		PedidoDto pedido = PedidoService.getPedidoById(intId);
		//List<PedidoDto> PedidosAux = Pedidos.stream().limit(10).collect(Collectors.toList());
		return new ResponseEntity<>(pedido, HttpStatus.OK);
	}
	
}
