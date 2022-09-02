package com.efox.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Service;

import com.efox.ecommerce.dto.ArticuloDto;
import com.efox.ecommerce.model.Articulo;
import com.efox.ecommerce.model.Proveedor;
import com.efox.ecommerce.repository.ArticuloRepository;
import com.efox.ecommerce.repository.ProveedorRepository;

@Service
public class ArticuloServiceImpl implements IArticulo {

	private static final Log LOG = LogFactory.getLog(ArticuloServiceImpl.class);
	
	@Resource
	private ArticuloRepository ArticuloRepo;
	@Resource
	private ProveedorRepository ProvRepo;

	@Transactional
	@Override
	public ArticuloDto addArticulo(ArticuloDto articuloDto) {
		
		String provString = articuloDto.getProveedor();
		if(provString.isBlank() || provString.isEmpty() || provString == null) {return null;}
		
		Proveedor provAux = ProvRepo.findByNombre(provString);
		if(provAux == null || provAux.getNombre().isBlank() || provAux.getNombre().isEmpty()) {
			provAux.setNombre(provString);
			ProvRepo.save(provAux);
		}
		if(provAux != null && !provAux.getNombre().isBlank() && !provAux.getNombre().isEmpty()) {
			
			Articulo articulo = new Articulo();
			mapDtoToEntity(articuloDto, articulo,provAux);
			Articulo savedArticulo = ArticuloRepo.save(articulo);
			return mapEntityToDto(savedArticulo, provAux);
		}else {
			return null;
		}
		
	}

	@Override
	public List<ArticuloDto> getAllArticulos() {

		List<ArticuloDto> articuloDtos = new ArrayList<>();
		List<Proveedor> provList = ProvRepo.findAll();
		provList.stream().forEach(p -> {			
			List<Articulo> articulos = ArticuloRepo.findByProveedor(p);
			articulos.stream().forEach(a -> {			
				ArticuloDto articuloDto = mapEntityToDto(a,p);
				articuloDtos.add(articuloDto);
			});
		});
		
		return articuloDtos;
	}
	
	@Override
	public List<ArticuloDto> getArticulosByProveedor(Integer id) {
		
		List<ArticuloDto> articuloDtos = new ArrayList<>();
		Proveedor prov = ProvRepo.findById(id).orElse(null);
		List<Articulo> articulos = ArticuloRepo.findByProveedor(prov);
		articulos.stream().forEach(a -> {			
			ArticuloDto articuloDto = mapEntityToDto(a,prov);
			articuloDtos.add(articuloDto);
		});
				
		return articuloDtos;
	}
	
	@Override
	public List<String> getArticulosCategories() {
		
		List<String> categories = ArticuloRepo.getDistinctCategories();		
		
		return categories;
	}
	
	@Override
	public List<ArticuloDto> getArticulosByCategories(String category) {
						
		List<ArticuloDto> articuloDtos = new ArrayList<>();			
		List<Articulo> articulos = ArticuloRepo.findByCategoria(category);
		Set<Proveedor> prov = articulos.stream()
	            .map(Articulo::getProveedor)
	            .distinct()
	            .collect(Collectors.toSet());
						
		articulos.stream().forEach(a -> {	
			Proveedor aux2 = prov.stream().filter(p -> 
				p.getId_proveedor().equals(a.getProveedor().getId_proveedor())).findFirst().orElse(null);
			
			ArticuloDto articuloDto = mapEntityToDto(a,aux2);
			articuloDtos.add(articuloDto);
		});
				
		return articuloDtos;
	}	

	@Transactional
	@Override
	public ArticuloDto updateArticulo(ArticuloDto articuloDto) {
		
		String provString = articuloDto.getProveedor();
		if(provString.isBlank() || provString.isEmpty() || provString == null) {return null;}
		
		Proveedor provAux = ProvRepo.findByNombre(provString);
		if(provAux == null || provAux.getNombre().isBlank() || provAux.getNombre().isEmpty()) {
			provAux.setNombre(provString);
			ProvRepo.save(provAux);
		}
		
		if(provAux != null && !provAux.getNombre().isBlank() && !provAux.getNombre().isEmpty()) {
			Articulo put = ArticuloRepo.getReferenceById(articuloDto.getId());
			mapDtoToEntity(articuloDto, put,provAux);
			Articulo articulo = ArticuloRepo.save(put);
			Proveedor p = ProvRepo.findByNombre(articuloDto.getProveedor());
			return mapEntityToDto(articulo,p);
		}else {
			return null;
		}
		
	}

	@Transactional
	@Override
	public String deleteArticulo(Integer id) {
		
		if(ArticuloRepo.existsById(id)) {
			Articulo articulo = ArticuloRepo.findById(id).orElse(null);
			//Articuloconfig.removeArticulo();<- ojo con arts config
			ArticuloRepo.delete(articulo);
			return "Articulo " + id + " borrado";
		}
		return null;
	}

	private void mapDtoToEntity(ArticuloDto articuloDto, Articulo articulo,Proveedor provAux) {
				
		articulo.setDescripcion(articuloDto.getDescripcion());
		articulo.setPrecio(articuloDto.getPrecio());
		articulo.setCodigo(articuloDto.getCodigo());
		articulo.setProveedor(provAux);
	}

	private ArticuloDto mapEntityToDto(Articulo articulo, Proveedor proveedor) {				
		ArticuloDto responseDto = new ArticuloDto();
		responseDto.setDescripcion(articulo.getDescripcion());
		responseDto.setId(articulo.getId_articulo());
		responseDto.setPrecio(articulo.getPrecio());
		responseDto.setCodigo(articulo.getCodigo());
		responseDto.setProveedor(proveedor.getNombre());
		return responseDto;
	}	
	
}
