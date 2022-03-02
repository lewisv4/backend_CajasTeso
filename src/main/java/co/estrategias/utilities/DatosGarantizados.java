package co.estrategias.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.estrategias.services.GarantizadosService;

public class DatosGarantizados {
	
	private Logger logger = LoggerFactory.getLogger(GarantizadosService.class);
	private String rutaFtp; 
	private String rutaArchivoFechaCreado;
	private String rutaServidorJava;
	private String comandoBat;
	
	private String rutas; // Esta ruta se debe CAMBIAR cuando se suba a producción.
	
	public DatosGarantizados() {
		
		// Esta ruta se cambia para que lea el archivo desde el servidor. La única ruta que debe ir quemada.
		this.rutas = "C:\\Users\\Analista\\Desktop\\SERVERJAVA\\rutas.txt"; // Rutas local
		//this.rutas="/v1app/sinet_production/SERVERJAVA/rutas.txt"; // Rutas Servidor 10.67.36.90
	}
	
	public void copiarRutasTxt() {
		File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File (rutas);
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	         
	         // Lectura del fichero
	         String linea;
	         int i = 0;
	         String[] arrayArchivo = new String[4];
	         while((linea=br.readLine())!=null) {
	        	 if(i==1) {
	        		 System.out.println("Línea: "+ linea);
	        		 arrayArchivo = linea.split(";");
	        		 this.setRutaFtp(arrayArchivo[0]);
	        		 this.setRutaArchivoFechaCreado(arrayArchivo[1]);
	        		 this.setRutaServidorJava(arrayArchivo[2]);
	        		 this.setComandoBat(arrayArchivo[3]);
	        	 }
	        	 i++;
	         }
	      }catch(Exception e){
	    	  logger.info("ERROR: No se leyó el archivo: "+e);
	         //e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	}
	
	public String getRutaFtp() {return rutaFtp;}
	public void setRutaFtp(String rutaFtp) {this.rutaFtp = rutaFtp;}
	public String getRutaArchivoFechaCreado() {return rutaArchivoFechaCreado;}
	public void setRutaArchivoFechaCreado(String rutaArchivoFechaCreado) {this.rutaArchivoFechaCreado = rutaArchivoFechaCreado;}
	public String getRutaServidorJava() {return rutaServidorJava;}
	public void setRutaServidorJava(String rutaServidorJava) {this.rutaServidorJava = rutaServidorJava;}
	public String getComandoBat() {return comandoBat;}
	public void setComandoBat(String comandoBat) {this.comandoBat = comandoBat;}
	
	}
