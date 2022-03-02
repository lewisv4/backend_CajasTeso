package co.estrategias.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

import co.estrategias.daos.BsAudGarantizadosDao;
import co.estrategias.daos.RpaEstadosFinancierosDao;
import co.estrategias.entities.BsAudGarantizados;
import co.estrategias.entities.RpaCanalesEstadosFinancieros;
import co.estrategias.utilities.DatosGarantizados;
import co.estrategias.utilities.SUserInfo;
import co.estrategias.utilities.Constantes;

@Service
public class GarantizadosService {
	
	//DatosGarantizados datosGarantizados;
	@Autowired
	private RpaEstadosFinancierosDao rpaEstadosFinancierosDao;
	@Autowired
	private BsAudGarantizadosDao bsAudGarantizadosDao;
	private Logger logger = LoggerFactory.getLogger(GarantizadosService.class);
	
	public GarantizadosService() {
		//datosGarantizados = new DatosGarantizados();
	}
	
	public int ejecutarBat(RpaCanalesEstadosFinancieros rpaCanalesEstadosFinancieros, String comando) throws ParseException, IOException{
		System.out.println("Credenciales pasadas para SSH:");
		System.out.println(rpaCanalesEstadosFinancieros.toString());
		
		//String comando = "schtasks /run /TN BajoPutumayo";
		String linea = new String();

		try {
			JSch jsch = new JSch();
			Session session;
			session = jsch.getSession(rpaCanalesEstadosFinancieros.getUsuario(), rpaCanalesEstadosFinancieros.getServerName(), rpaCanalesEstadosFinancieros.getPort());
			UserInfo ui = new SUserInfo(rpaCanalesEstadosFinancieros.getPassword(), null);
			session.setUserInfo(ui);
			session.setPassword(rpaCanalesEstadosFinancieros.getPassword());
			session.setConfig("StrictHostKeyChecking", "no");
			session.setTimeout(5000);
			session.connect();
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(comando); // Acá envía el comando.
			channel.connect();
			
			logger.info("validar la ejecucion del ssh "+comando);

			StringBuilder outputBuffer = new StringBuilder();
			InputStream commandOutput = channel.getInputStream();
			int readByte = commandOutput.read();

			while (readByte != 0xffffffff) {
				outputBuffer.append((char) readByte);
				readByte = commandOutput.read();
			}
			linea = outputBuffer.toString();

			channel.disconnect();
			session.disconnect();
			
			logger.info("El .BAT se ejecutó de manera satisfactoria");
			System.out.println("El .BAT se ejecutó de manera satisfactoria");
			return Constantes.PROCESO_EXITOSO;
			
		} catch (JSchException e) {
			logger.info("ERROR: No se ejecuto el .BAT de manera satisfactoria");
			System.out.println("ERROR: No se ejecuto el .BAT de manera satisfactoria");
			return Constantes.ERROR_EJECUTAR_BAT;
		}

	}
	
	public boolean tablaEstados() {
		if (rpaEstadosFinancierosDao.estadosRpa() == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public int crearArchivoFecha(String fecha, String rutaArchivoFecha) {
		// Se fragmenta la fecha para dividirlo en año, mes y día, y posteriormente enviarlo de las dos formas.
		String[] arrayFecha = new String[3];
		arrayFecha = fecha.split("-");
		
		// Inicio creación de Archivo
		File fichero = new File(rutaArchivoFecha);
		if (fichero.delete()) {
			logger.info("El archivo se ha borrado satisfactoriamente");
			System.out.println("El archivo se ha borrado satisfactoriamente");
		}else {
			logger.info("No se borro el archivo");
			System.out.println("No se borro el archivo");
		}
		FileWriter flwriter = null;
		try {
			//crea el flujo para escribir en el archivo
			flwriter = new FileWriter(rutaArchivoFecha);									
			//crea un buffer o flujo intermedio antes de escribir directamente en el archivo
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			bfwriter.write("fecha|"+arrayFecha[2]+"/"+arrayFecha[1]+"/"+arrayFecha[0]+"**\n");
			bfwriter.write("fecha1|"+arrayFecha[0]+"/"+arrayFecha[1]+"/"+arrayFecha[2]+"**");
			bfwriter.close();
		} catch (IOException e) {
			logger.info("ERROR: No creo el archivo fecha.txt: "+e);
			System.out.println("ERROR: No creo el archivo fecha.txt: "+e);
			return Constantes.ERROR_CREAR_ARCHIVO;
		} finally {
			if (flwriter != null) {
				try {//cierra el flujo principal
					flwriter.close();
				} catch (IOException e) {
					logger.info("ERROR: No creo el archivo: "+e);
					System.out.println("ERROR: No creo el archivo: "+e);
					return Constantes.ERROR_CREAR_ARCHIVO;
				}
			}
		}
		logger.info("Archivo creado satisfactoriamente..");
		System.out.println("Archivo creado satisfactoriamente..");
		return Constantes.PROCESO_EXITOSO;
	}
	
	public int ingresarRegistro(BsAudGarantizados bsAudGarantizados) {
		int id_registro = bsAudGarantizadosDao.obtieneUltimoId()+1;
		bsAudGarantizados.setId(id_registro);
		
		try {
			
			bsAudGarantizadosDao.save(bsAudGarantizados);
			return Constantes.PROCESO_EXITOSO;
		} catch (Exception e) {
			logger.info("ERROR: No creo el registro: "+e);
			System.out.println("ERROR: No creo el registro: "+e);
			return Constantes.ERROR_INGRESAR_REGISTRO;
		}
	} // FIN ingresarRegistro ---------------------------------------
	
	// Si hay error al enviar el archivo retorna el número 3.
	public int enviarArchivo(String rutaInicial, String rutaFinal, RpaCanalesEstadosFinancieros rpaCanalesEstadosFinancieros) {
		FTPClient ftp = new FTPClient();
		
		try {
			ftp.connect(rpaCanalesEstadosFinancieros.getServerName(), rpaCanalesEstadosFinancieros.getPort());
			int replyCode = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				logger.info("Operation failed. Server reply code: " + replyCode);
				System.out.println("Operation failed. Server reply code: " + replyCode);
				return Constantes.ERROR_ENVIAR_ARCHIVO;
			}
			boolean success = ftp.login(rpaCanalesEstadosFinancieros.getUsuario(), rpaCanalesEstadosFinancieros.getPassword());
			if (!success) {
				logger.info("Error al loggerse en el sistema");
				System.out.println("Error al loggerse en el sistema");
				return Constantes.ERROR_ENVIAR_ARCHIVO;
			} else {
				logger.info("LOGGED IN SERVER");
				System.out.println("LOGGED IN SERVER");
				
				ftp.changeWorkingDirectory(rutaFinal);
				int replay = ftp.getReplyCode();
				if (FTPReply.isPositiveCompletion(replay)) {
					File file = new File(rutaInicial); // HelperEF.fichero.valdRutaGuarda: Indica la ruta donde tengo mi archivo.
					FileInputStream input = new FileInputStream(file);
					ftp.setFileType(FTP.BINARY_FILE_TYPE);
					ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
					ftp.enterLocalPassiveMode();
					logger.info("Archivo subido satisfactoriamente.");
					System.out.println("Archivo subido satisfactoriamente.");
					
					if (!ftp.storeFile(file.getName(), input)) {
						logger.info("Subida fallida!");
						System.out.println("Subida fallida!");
						return Constantes.ERROR_ENVIAR_ARCHIVO;
					}
					input.close();
				}
			}
			
		}catch(IOException ioe) {
			logger.info("ERROR de entrada de datos: " + ioe);
			System.out.println("ERROR de entrada de datos: " + ioe);
			return Constantes.ERROR_ENVIAR_ARCHIVO;
		}
		finally {
			try {
				if (ftp.isConnected()) {
					ftp.logout();
					ftp.disconnect();
				}
			} catch (Exception ex) {
				logger.info("ERROR en el FTP: " + ex);
				System.out.println("ERROR en el FTP: " + ex);
				return Constantes.ERROR_ENVIAR_ARCHIVO;
			}
		}
		return Constantes.PROCESO_EXITOSO;
	} // FIN enviarArchivo ---------------------------------------
}
