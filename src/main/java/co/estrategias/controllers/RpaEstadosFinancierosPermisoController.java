package co.estrategias.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.estrategias.utilities.ConnectServerSSH;
import co.estrategias.Config;
import co.estrategias.daos.BsConexionDao;
import co.estrategias.daos.RpaEstadoFincieroDAO;
import co.estrategias.entities.Bs_Nom_corto;
import co.estrategias.entities.ConexionesEstrategias;
import com.estrategias.helper.HelperEF;
import com.estrategias.helper.Helpers;

import co.estrategias.entities.FtpRpaReglas;
import co.estrategias.entities.RpaAudEjecRobot;
import co.estrategias.entities.RpaCanalesEstadoFinanciero;
import co.estrategias.entities.RpaEstadosFinacierosFtp;
import co.estrategias.entities.RpaEstadosFinacierosPermisos;
import co.estrategias.entities.RpaMensajeEstadoFinanciero;
import co.estrategias.entities.SshRpaReglas;

@Controller
@RestController
@RequestMapping(value = "${app.path_rest_service}")
@CrossOrigin("*")
public class RpaEstadosFinancierosPermisoController implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private BsConexionDao conexionesEstrategiasDao;
	private Logger logger = LoggerFactory.getLogger(RpaEstadosFinancierosPermisoController.class);

	private int estado;

	/*
	 * Servicio que consulta slas concesiones
	 */
	@GetMapping("/Cta-find-all/{cedula}")
	public List<RpaEstadosFinacierosPermisos> getPermisosFin(@PathVariable String cedula) {

		Config configuration = new Config();

		try {
			List<ConexionesEstrategias> con = conexionesEstrategiasDao.findByNitAndPlataforma(Helpers.nitSinet,
					Helpers.plataforma);
			DataSource datasource = configuration.conexion(con.get(0));
			RpaEstadoFincieroDAO p = new RpaEstadoFincieroDAO();

			return p.getCedulaPermiso(datasource, cedula);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Consultar la cedula: " + e.toString());
			return null;
		}
	}

	@GetMapping("/estado-ejecucion/{EMPRESA}")
	public String GetEstado(@PathVariable String EMPRESA) {

		Config configuration = new Config();
		
		String resultado="";
		try {
			List<ConexionesEstrategias> con = conexionesEstrategiasDao.findByNitAndPlataforma(Helpers.nitSinet,
					Helpers.plataforma);
			DataSource datasource = configuration.conexion(con.get(0));
			RpaEstadoFincieroDAO u = new RpaEstadoFincieroDAO();


			List<RpaEstadosFinacierosFtp> listaEStado = new ArrayList<>();
			listaEStado = u.GetEstado(datasource, EMPRESA);
			
			for (RpaEstadosFinacierosFtp a : listaEStado) {
				
				estado=a.getESTADO();
				System.out.println(estado);
				logger.info("estado del robot"+estado);
			}
			resultado=Integer.toString(estado);
			return resultado;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Buscar la empresa por el estado: " + e.toString());
			return null;
		}
	}
  
	@GetMapping("/estado-mensaje/{EMPRESA}")
    public List<RpaMensajeEstadoFinanciero> GetEstadoMensaje(@PathVariable String EMPRESA) {
        Config configuration = new Config();

        try {
            List<ConexionesEstrategias> con = conexionesEstrategiasDao.findByNitAndPlataforma(Helpers.nitSinet,
                    Helpers.plataforma);
            DataSource datasource = configuration.conexion(con.get(0));
            RpaEstadoFincieroDAO u = new RpaEstadoFincieroDAO();

            return u.GetEstadoMensaje(datasource, EMPRESA);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Buscar la empresa por el estado: " + e.toString());
            return null;
        }

    }

	@GetMapping("/into-audito-rpa/{EMPRESA}/{ano}/{mes}/{cedula}/{florin}")
	public List<RpaAudEjecRobot> auditoriaEsatdoFinciero(@PathVariable String EMPRESA, @PathVariable String ano,
			@PathVariable int mes, @PathVariable String cedula, @PathVariable int florin) {
		/*
		 * variables para uso de parametros de la base de datos
		 */
		String nombreCorto = "";
		String server = "";
		int port = 0;
		String userShh="";
		String passShh="";
		int portBat = 0;
		String host="";
		String ruta="";
		String user = "";
		String pass = "";
		String puntoBar="";
		String rutaEjecucion = "";
		Config configuration = new Config();

		try {
			
			List<ConexionesEstrategias> con = conexionesEstrategiasDao.findByNitAndPlataforma(Helpers.nitSinet,
					Helpers.plataforma);
			DataSource datasource = configuration.conexion(con.get(0));
			RpaEstadoFincieroDAO p = new RpaEstadoFincieroDAO();
			
			List<RpaAudEjecRobot> insertar = new ArrayList<>();
			
			RpaAudEjecRobot creacionLista = new RpaAudEjecRobot();
			creacionLista.setEMPRESA(EMPRESA);
			creacionLista.setANO(ano);
			creacionLista.setMES(mes);
			creacionLista.setUSUARIO_CREACION(cedula);
			creacionLista.setFLORIN(florin);
			insertar.add(creacionLista);
			
			System.out.println(insertar);
			
			int intoNuevosPuntos = p.insertCamposAuditoria(datasource, insertar);
			System.out.println("insercion" + intoNuevosPuntos);
			
			List<Bs_Nom_corto> empresaNombreCorto = new ArrayList<>();
			empresaNombreCorto = p.getEmprNomCorto(datasource, EMPRESA);
			for (Bs_Nom_corto cort : empresaNombreCorto) {
				nombreCorto = cort.getNombre();
			}
			
			List<RpaEstadosFinacierosFtp> direccionServe = new ArrayList<>();
			direccionServe = p.getRutaGuardado(datasource, EMPRESA);

			List<RpaCanalesEstadoFinanciero> reglas = new ArrayList<>();
			reglas = p.GetCanalesFinanciero(datasource);
	
			for (int i = 0; i < reglas.size(); i++) {
				int id = reglas.get(i).getId() ;	
					if (id==1) {
						server = reglas.get(i).getServerName();	
						port = reglas.get(i).getPort();
						user = reglas.get(i).getUsuario();
						pass = reglas.get(i).getPassword();
						} 
					if (id==2) { // Credenciales SSH BAT
						userShh=reglas.get(i).getUsuario();	
						host = reglas.get(i).getServerName();	
						passShh=reglas.get(i).getPassword();
						portBat=reglas.get(i).getPort();
					}
						
				} 
			
			for (RpaEstadosFinacierosFtp u : direccionServe) {
				rutaEjecucion = u.getRUTAPARAMETRO();
				ruta=u.getRUTABAT();
			}

			FTPClient ftp = new FTPClient();
			
			String fichero = HelperEF.crearArchivo(EMPRESA, nombreCorto, ano, mes, florin);
			System.out.println(" validacion de creacion: " + fichero);
			try {
				ftp.connect(server, port);
				int replyCode = ftp.getReplyCode();
				if (!FTPReply.isPositiveCompletion(replyCode)) {
					System.out.println("Operation failed. Server reply code: " + replyCode);
					return insertar;
				}
				boolean success = ftp.login(user, pass);
				if (!success) {
					System.out.println("Failed to log into the server");
					return insertar;
				} else {
					System.out.println("LOGGED IN SERVER");
					logger.info("validar la ruta ejecucion de archivo "+rutaEjecucion);
					ftp.changeWorkingDirectory(rutaEjecucion);
					System.out.println(ftp.changeToParentDirectory());
					int replay = ftp.getReplyCode();
					if (FTPReply.isPositiveCompletion(replay)) {
						File file = new File(HelperEF.fichero.valdRutaGuarda);
						FileInputStream input = new FileInputStream(file);
						ftp.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
						ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
						ftp.enterLocalPassiveMode();
						System.out.println("Subió satisfactoriamente el archivo");

						if (!ftp.storeFile(file.getName(), input)) {
							System.out.println("Subida fallida!");
						}
						input.close();
					}
				}
				
			} finally {
				try {
					if (ftp.isConnected()) {
						ftp.logout();
						ftp.disconnect();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			

			logger.info("validar la ejecucion del ssh "+ruta);
			puntoBar=ConnectServerSSH.getCommandSSH(userShh, host, passShh, portBat, ruta);		 // Ejecutar el comando SSH.
			System.out.println("ya se iso la conexion");		
			
			logger.info("validar la ejecucion del ssh "+puntoBar);
		} catch (Exception e) {
			System.out.println("Error al conectarse al ftp(): " + e.toString());
		}
		return null;

	}
	
	@GetMapping("/find-estado")
	public int getEstado() {

		Config configuration = new Config();
		int estado = 0;
		try {
			List<ConexionesEstrategias> con = conexionesEstrategiasDao.findByNitAndPlataforma(Helpers.nitSinet,
					Helpers.plataforma);
			DataSource datasource = configuration.conexion(con.get(0));
			RpaEstadoFincieroDAO u = new RpaEstadoFincieroDAO();

			List<RpaEstadosFinacierosFtp> listaEStado=new ArrayList<>();
			listaEStado=u.getEstado(datasource);
			
			for (RpaEstadosFinacierosFtp a : listaEStado) {
				
				estado=a.getESTADO();
				
			}
			return estado;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Buscar la empresa por el estado: " + e.toString());
			
		}
		return estado;
	}

	@GetMapping("/find-estado-modal")
	public List<RpaMensajeEstadoFinanciero>  getEstadoModal() {

		Config configuration = new Config();
		String estado = "";
		try {
			List<ConexionesEstrategias> con = conexionesEstrategiasDao.findByNitAndPlataforma(Helpers.nitSinet,
					Helpers.plataforma);
			DataSource datasource = configuration.conexion(con.get(0));
			RpaEstadoFincieroDAO u = new RpaEstadoFincieroDAO();

			return u.getEstadoModal(datasource);
			
			 

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Buscar la empresa por el estado: " + e.toString());

			return null;
			
		}
	}
}
	
	



