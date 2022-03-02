package co.estrategias.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.estrategias.entities.BsProceso;
import co.estrategias.services.BsProcesoService;
import co.estrategias.utilities.GlobalConst;

@RestController
@RequestMapping("${app.path_rest_service}")
@CrossOrigin(GlobalConst.CROSS_ORIGIN)
public class BsProcesoController {
	
	@Autowired
	private BsProcesoService procesoService;
	
	@GetMapping("/prueba")
	private List<BsProceso> procesos(
			@RequestHeader(value = GlobalConst.AUTHORIZATION, required=true) String credentials){
		return procesoService.procesos();
	}

	
}
