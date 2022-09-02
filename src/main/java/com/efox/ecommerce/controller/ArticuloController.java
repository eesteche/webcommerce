package com.efox.ecommerce.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efox.ecommerce.dto.ArticuloDto;
import com.efox.ecommerce.service.IArticulo;

@RestController
@RequestMapping("/public/articulos")
@CrossOrigin(origins = "*")
public class ArticuloController {

	@Autowired
	private IArticulo ArticuloService;

	@GetMapping()
	public ResponseEntity<List<ArticuloDto>> getAllArticulos() {
		List<ArticuloDto> articulos = ArticuloService.getAllArticulos();
		List<ArticuloDto> articulosAux = articulos.stream().limit(10).collect(Collectors.toList());
		return new ResponseEntity<>(articulosAux, HttpStatus.OK);
	}
	
	@GetMapping("/provider/{id}")
	public ResponseEntity<List<ArticuloDto>> getArticulosProvider(@PathVariable(name = "id") String id) {
		Integer intId = NumberUtils.toInt(id, -1);
		List<ArticuloDto> articulos = ArticuloService.getArticulosByProveedor(intId);
		//List<ArticuloDto> articulosAux = articulos.stream().limit(10).collect(Collectors.toList());
		return new ResponseEntity<>(articulos, HttpStatus.OK);
	}
	
	@GetMapping("/categories") 
	public ResponseEntity<List<String>> getArticulosCategories() {
		
		List<String> categories = ArticuloService.getArticulosCategories();
		
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	@GetMapping("/categories/{category}")
	public ResponseEntity<List<ArticuloDto>> getArticulosByCategories(@PathVariable(name = "category") String category) {
		
		List<ArticuloDto> articlesByCategory = ArticuloService.getArticulosByCategories(category);
		
		return new ResponseEntity<>(articlesByCategory, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<ArticuloDto> postArticulo(@RequestBody ArticuloDto ArticuloDto) {
		ArticuloDto post = ArticuloService.addArticulo(ArticuloDto);
		return new ResponseEntity<>(post, HttpStatus.CREATED);
	}

	@PutMapping()
	public ResponseEntity<ArticuloDto> updateArticulo(@RequestBody ArticuloDto articulo) {
				
			ArticuloDto put = ArticuloService.updateArticulo(articulo);
			return new ResponseEntity<>(put, HttpStatus.CREATED);				
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteArticulo(@PathVariable(name = "id") String id) {
		
		Integer intId = NumberUtils.toInt(id, -1);
		
		if (intId < 1) {
			System.out.println("Error en el ID.");
			return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
		}else{
			String message = ArticuloService.deleteArticulo(intId);
			return new ResponseEntity<>(message, HttpStatus.OK);
		}
		
	}
}
