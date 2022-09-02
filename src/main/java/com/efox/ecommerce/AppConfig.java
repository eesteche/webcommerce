package com.efox.ecommerce;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.efox.ecommerce.service.IProveedor;
import com.efox.ecommerce.service.ProveedorServiceImpl;


@Configuration
public class AppConfig {

	@Bean("ProveedorServiceImpl")
	@Primary
	public IProveedor servicioProveedor() {
		return new ProveedorServiceImpl();
	}

}
