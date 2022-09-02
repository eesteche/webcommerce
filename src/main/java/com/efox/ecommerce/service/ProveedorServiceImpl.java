package com.efox.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.efox.ecommerce.dto.ProveedorDto;
import com.efox.ecommerce.model.Proveedor;
import com.efox.ecommerce.repository.ArticuloRepository;
import com.efox.ecommerce.repository.ProveedorRepository;

@Primary
@Service
public class ProveedorServiceImpl implements IProveedor {

	@Resource
	private ArticuloRepository ArticuloRepo;
	@Resource
	private ProveedorRepository ProvRepo;
	
	@Override
	public ProveedorDto addProveedor(ProveedorDto proveedorDto) {
		Proveedor proveedor = new Proveedor();
		mapDtoToEntity(proveedorDto, proveedor);
		Proveedor savedProveedor = ProvRepo.save(proveedor);
		return mapEntityToDto(savedProveedor);
	}
	
	@Override
	public List<ProveedorDto> getAllProveedores() {
		List<ProveedorDto> proveedorDtos = new ArrayList<>();
		List<Proveedor> proveedores = ProvRepo.findAll();
		proveedores.stream().forEach(prov -> {
			ProveedorDto proveedorDto = mapEntityToDto(prov);
			proveedorDtos.add(proveedorDto);
		});
		return proveedorDtos;
	}

	private void mapDtoToEntity(ProveedorDto proveedorDto, Proveedor proveedor) {
		proveedor.setNombre(proveedorDto.getNombre());				
	}

	private ProveedorDto mapEntityToDto(Proveedor proveedor) {
		ProveedorDto responseDto = new ProveedorDto();
		responseDto.setNombre(proveedor.getNombre());
		responseDto.setId_proveedor(proveedor.getId_proveedor());
		return responseDto;
	}

	@Override
	public ProveedorDto updateProveedor(ProveedorDto proveedor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteProveedor(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
