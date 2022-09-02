package com.efox.ecommerce.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.efox.ecommerce.dto.PedidoDto;
import com.efox.ecommerce.dto.PedidoDto.Buyer;
import com.efox.ecommerce.dto.PedidoDto.Items;
import com.efox.ecommerce.model.Articulo;
import com.efox.ecommerce.model.ArticuloPedido;
import com.efox.ecommerce.model.Pedido;
import com.efox.ecommerce.repository.ArticuloRepository;
import com.efox.ecommerce.repository.PedidoRepository;

@Service
public class IPedidoImpl implements IPedido {
	
	@Resource
	private PedidoRepository PedidosRepo;
	@Resource
	private ArticuloRepository ArtsRepo;

	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");	
	private final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public PedidoDto updatePedido(PedidoDto pedidoDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deletePedido(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PedidoDto addPedido(PedidoDto pedidoDto) {
		Pedido pedido = new Pedido();
		mapDtoToEntity(pedidoDto, pedido);		
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		pedido.setAlta_fecha(parseTimestamp(date));
		
		Pedido savedPedido = PedidosRepo.save(pedido);
		Map<Articulo,Integer> articulosEnPedido = new HashMap<Articulo,Integer>();
		Map<Integer, Integer> listIdArticulos = new HashMap<Integer,Integer>();
		pedidoDto.getItems().stream().forEach(dto -> listIdArticulos.put(dto.getId(), dto.getQuantity()));
		listIdArticulos.entrySet().stream().forEach(id -> {
			Articulo aux = ArtsRepo.findById(id.getKey()).orElse(null);
			articulosEnPedido.put(aux,id.getValue());
		});
		List<ArticuloPedido> articulosPorPedido = getArticulosPedidos(articulosEnPedido,savedPedido);						
		savedPedido.setArticuloPedido(articulosPorPedido);
		Pedido savedPedidoConId = PedidosRepo.save(savedPedido);		
		return mapEntityFinalToDto(savedPedidoConId);
	}

	@Override
	public List<PedidoDto> getAllPedidos() {
		List<PedidoDto> pedidoDto = new ArrayList<>();
		List<Pedido> pedidos = PedidosRepo.findAll();
		pedidos.stream().forEach(ped -> {
			PedidoDto proveedorDto = mapEntityFinalToDto(ped);
			pedidoDto.add(proveedorDto);
		});
		return pedidoDto;
	}
	
	@Override
	public PedidoDto getPedidoById(Integer id) {
		Pedido ped = PedidosRepo.findById(id).orElse(null);
		PedidoDto pedDto = mapEntityFinalToDto(ped);
		return pedDto;
	}
	
	private void mapDtoToEntity(PedidoDto pedidoDto, Pedido pedido) {
		pedido.setNombre(pedidoDto.getBuyer().getNombre());
		pedido.setApellido(pedidoDto.getBuyer().getApellido());
		pedido.setEmail(pedidoDto.getBuyer().getEmail());
		pedido.setPhone(pedidoDto.getBuyer().getPhone());
		
	}

	private PedidoDto mapEntityToDto(Pedido p) {
		PedidoDto responseDto = new PedidoDto();		
		responseDto.setBuyer(new Buyer(p.getNombre(),p.getApellido(),p.getEmail(),p.getPhone()));		
		return responseDto;
	}
	
	private PedidoDto mapEntityFinalToDto(Pedido pedido) {
		PedidoDto responseDto = new PedidoDto();			
		responseDto.setBuyer(new Buyer(pedido.getNombre(),pedido.getApellido(),pedido.getEmail(),pedido.getPhone()));
		pedido.getArticuloPedido().stream().forEach(x -> {
			responseDto.getItems().add(new Items(x));
		});
		responseDto.setOrder_id(pedido.getId_pedido());
		
		return responseDto;
	}

	private List<ArticuloPedido> getArticulosPedidos(Map<Articulo,Integer> articulosEnPedido, Pedido pedido){
		
		//articulosEnPedido.stream().forEach(x -> { System.out.println("getArticulosPedidos: "+x.toString()) ;});
		List<ArticuloPedido> articulosPorPedido = new ArrayList<ArticuloPedido>();
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		articulosEnPedido.entrySet().stream().forEach(articulo -> {
			ArticuloPedido aux = new ArticuloPedido(articulo.getKey(),pedido,articulo.getValue());
			aux.setAlta_fecha(parseTimestamp(date));
			articulosPorPedido.add(aux);
			
			
		});
		//articulosPorPedido.stream().forEach(x -> { System.out.println("getArticulosPedidos 3 : "+x.toString()) ;});
		return articulosPorPedido;
	}
	
	private java.sql.Date parseDate(String date) {				
		
	    try {
	        return (java.sql.Date) new Date(DATE_FORMAT.parse(date).getTime());
	    } catch (ParseException e) {
	        throw new IllegalArgumentException(e);
	    }
	}
	 
	private java.sql.Timestamp parseTimestamp(String timestamp) {
	    try {
	        return new Timestamp(DATE_TIME_FORMAT.parse(timestamp).getTime());
	    } catch (ParseException e) {
	        throw new IllegalArgumentException(e);
	    }
	}

}
