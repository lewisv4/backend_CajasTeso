package co.estrategias.controllers;

import co.estrategias.daos.BsAudGarantizadosDao;
import co.estrategias.daos.RpaCanalesEstadosFinancierosDao;
import co.estrategias.entities.BsAudGarantizados;
import co.estrategias.entities.RpaCanalesEstadosFinancieros;
import co.estrategias.services.*;
import co.estrategias.utilities.Constantes;
import co.estrategias.utilities.DatosGarantizados;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RestController
@RequestMapping("${app.path_rest_service}")
@CrossOrigin("*")
public class GarantizadosController {
	
	/* El path del excel en el computador es: C:\\Users\\EE1107036727\\Desktop\\ftp_inicial\\prueba_excel.xlsx*/
	/* urlLocal":"C:\\Users\\EE1107036727\\Desktop\\ftp_inicial\\prueba_excel.xlsx */
	/*Path de los archivos del SERVERLOCAL: C:\\Users\\EE1107036727\\Desktop\\SERVERJAVA\\*/
	
	/* Contraseña Sinet demo: Gly65Pct */
	
	@Autowired
	private BsAudGarantizadosDao bsAudGarantizadosDao;
	@Autowired
	private RpaCanalesEstadosFinancierosDao rpaCanalesEstadosFinancierosDao;
	@Autowired
	private GarantizadosService garantizadosService;
	private Logger logger = LoggerFactory.getLogger(GarantizadosService.class);
	
	DatosGarantizados datosGarantizados = new DatosGarantizados();
	RpaCanalesEstadosFinancieros rpaCanalesEstadosFinancieros = new RpaCanalesEstadosFinancieros();
		
	@PostMapping(value="/garantizados")
	public int mostrarTabla(@RequestBody BsAudGarantizados bsAudGarantizados) { // @RequestBody BsAudGarantizados bsAudGarantizados
		System.out.println("Entró a garantizados localmente");
		int comprobacion = Constantes.PROCESO_EXITOSO;
		this.datosGarantizados.copiarRutasTxt();
		
		// El metodo tablaEstados retorna true si todos los robots estan sin ejecutar.
		if(garantizadosService.tablaEstados()) {
			rpaCanalesEstadosFinancieros = rpaCanalesEstadosFinancierosDao.getCredencialesFtp();
			
			comprobacion = garantizadosService.crearArchivoFecha(bsAudGarantizados.getFechaSistema().toString(),datosGarantizados.getRutaArchivoFechaCreado()); // Crea archivo para fecha
			if(comprobacion == Constantes.PROCESO_EXITOSO) {
				comprobacion = garantizadosService.enviarArchivo(datosGarantizados.getRutaArchivoFechaCreado(),
						datosGarantizados.getRutaFtp(),rpaCanalesEstadosFinancieros); // envía archivo con la fecha
				
			}
			if(comprobacion == Constantes.PROCESO_EXITOSO) {
				
				Date date = new Date();
				bsAudGarantizados.setFechaSistema(new java.sql.Date(date.getTime()));
				comprobacion = garantizadosService.ingresarRegistro(bsAudGarantizados);
				System.out.println("Comprobación ingresarRegistro: "+comprobacion);
				rpaCanalesEstadosFinancieros = rpaCanalesEstadosFinancierosDao.getCredencialesSsh();
			}
			
			
			if(comprobacion == Constantes.PROCESO_EXITOSO) {
				try {
					System.out.println("Comando BAT: "+datosGarantizados.getComandoBat());
					comprobacion = garantizadosService.ejecutarBat(rpaCanalesEstadosFinancieros,datosGarantizados.getComandoBat());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					comprobacion = Constantes.ERROR_EJECUTAR_BAT;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					comprobacion = Constantes.ERROR_EJECUTAR_BAT;
				}
			}
			
			return comprobacion;
			
		}else {
			logger.info("OCUPADO: Intentelo nuevamente en 10 minutos");
			System.out.println("OCUPADO: Intentelo nuevamente en 10 minutos");
			return Constantes.ERROR_ESTADOS_ROBOTS;
		}
	}
	
	// Se retorna 1 cuando están ocupados los Robots.
	@PostMapping(path ="/estadosrobots")
	public int estadosRobots() {
		
		// El metodo tablaEstados retorna true si todos los robots estan sin ejecutar.
		if(garantizadosService.tablaEstados()) {
		return Constantes.PROCESO_EXITOSO;
		}else {
			return Constantes.ERROR_ESTADOS_ROBOTS;
		}
	}
		
	@RequestMapping("/saludos")
	   public @ResponseBody String hellow()
	   {
	       System.out.println("----------------> Saludo");        
	       return "Bienvenido a Garantizados. Modulo: Contabilidad->Contabilizacion Masiva";
	   }
	
	// -------------- metodo para cargar archivo ftp -------  
    @PostMapping(path ="/cargararchivos/{nombrearchivo}")
   public int CargarArchivo(@RequestBody MultipartFile file, @PathVariable("nombrearchivo") String nombreArchivo){
    	int comprobacion = Constantes.PROCESO_EXITOSO;
    	logger.info("Entró a cargararchivo");
    	this.datosGarantizados.copiarRutasTxt();
       try {
           // Extrae los bytes del archivo
           byte[] bytes = file.getBytes();
           
           //crea el archivo en el servidor
           File serverFile = new File(datosGarantizados.getRutaServidorJava()+ nombreArchivo);
           BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
           // Pasa los bytes al archivo en el servidor
           stream.write(bytes);
           stream.close();
           
       } catch (Exception e) {
    	   logger.info("ERROR al  cargar el archivo: "+e);
    	   return Constantes.ERROR_CARGAR_ARCHIVO;
       }
       
       //envía archivo excel
       rpaCanalesEstadosFinancieros = rpaCanalesEstadosFinancierosDao.getCredencialesFtp();
       comprobacion = garantizadosService.enviarArchivo(datosGarantizados.getRutaServidorJava()+nombreArchivo,datosGarantizados.getRutaFtp(),rpaCanalesEstadosFinancieros); 
       
       // El try y catch borra el archivo Excel del server Java después de enviado al servidor FTP 
       try {
    	   File serverFile = new File(datosGarantizados.getRutaServidorJava()+ nombreArchivo);
           serverFile.delete();
           logger.info("Se borro el archivo en el server java de manera satisfactoria");
    	   System.out.println("Se borro el archivo en el server java de manera satisfactoria");
    	   
       }catch(Exception e) {
    	   logger.info("ERROR al  borrar el archivo: "+e);
    	   System.out.println("Error al borrar el archivo");
       }
       
       return comprobacion;
   }
    
    @PostMapping(value="/retornardatos")
	public List<BsAudGarantizados> retornarDatos() {
		List<BsAudGarantizados> listaGarantizados = null;
		try {
			listaGarantizados = bsAudGarantizadosDao.findAll();
			logger.info("Se mostraron los registros en JSON");
			return listaGarantizados;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.info("NO se mostraron los registros en JSON: " + e);
			return null;
		}
	}
}
