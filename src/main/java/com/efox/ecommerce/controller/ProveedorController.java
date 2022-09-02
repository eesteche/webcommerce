package com.efox.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efox.ecommerce.dto.ProveedorDto;
import com.efox.ecommerce.service.IProveedor;

@RestController
@RequestMapping("/public/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {

	@Qualifier("ProveedorServiceImpl")
	@Autowired
	private IProveedor proveedorService;

	@GetMapping()
	public ResponseEntity<List<ProveedorDto>> getAllProveedores() {
		List<ProveedorDto> proveedores = proveedorService.getAllProveedores();
		return new ResponseEntity<>(proveedores, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<ProveedorDto> postProveedor(@RequestBody ProveedorDto proveedorDto) {
		ProveedorDto post = proveedorService.addProveedor(proveedorDto);
		return new ResponseEntity<>(post, HttpStatus.CREATED);
	}

	
}
