package com.efox.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.efox.ecommerce.model.Articulo;
import com.efox.ecommerce.model.Proveedor;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo,Integer>{
	
	public List<Articulo> findByProveedor(Proveedor proveedor);
	
	@Query("SELECT DISTINCT a.categoria FROM Articulo a")
	List<String> getDistinctCategories();
	
	public List<Articulo> findByCategoria(String categoria);

}