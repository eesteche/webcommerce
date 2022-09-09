package com.efox.ecommerce.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efox.ecommerce.dto.Items;
import com.efox.ecommerce.dto.PedidoDto;
import com.efox.ecommerce.service.IPedido;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/public/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {
	
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
	
	@GetMapping("/reports/{id}")
	public ResponseEntity<byte[]> pdf(@PathVariable(name = "id") String id) throws FileNotFoundException, JRException {
		Integer intId = NumberUtils.toInt(id, -1);		
		if(intId > 0) {
			PedidoDto pedido = PedidoService.getPedidoById(intId);
			List<PedidoDto> listapedidos = new ArrayList<PedidoDto>();
			PedidoDto[] pedidos = new PedidoDto[1];
			listapedidos.add(pedido);
			pedidos[0] = pedido;
			List<Items> items = pedido.getItems();
	 		Map<String, Object> empParams = new HashMap<String, Object>();
			empParams.put("business_name", "Efox");
			empParams.put("pedido", new JRBeanCollectionDataSource(listapedidos));
			empParams.put("items", new JRBeanCollectionDataSource(items));
			JasperPrint empReport =
					JasperFillManager.fillReport
				   (
							JasperCompileManager.compileReport(
							ResourceUtils.getFile("src/main/resources/pdfs/Pedido.jrxml")
									.getAbsolutePath()) // path of the jasper report
							, empParams // dynamic parameters
							, new JRBeanArrayDataSource(pedidos)
					);

			HttpHeaders headers = new HttpHeaders();
			//set the PDF format
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("filename", "Pedido.pdf");
			//create the report in PDF format
			return new ResponseEntity<byte[]>
					(JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);
		}else {
			return new ResponseEntity<byte[]>
			(null,null, HttpStatus.NOT_FOUND);
		}
		
						
	}
	
}
