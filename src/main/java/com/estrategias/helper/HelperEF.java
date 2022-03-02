package com.estrategias.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelperEF {
	private static Logger logger = LoggerFactory.getLogger(HelperEF.class);
	private static String mounth;

	/*Se coloca  la empresa de curacao para la validacion  de cuando se va 
	 * a crear el archivo de configuracion*/
	public final class curacao{
		public final static String nit="6024640";
	}
	/*Se coloca las ruta donde va a guardar  el archivo de configuracion 
	  y  en donde  el ftp va  ir a buscar el archivo*/
	public final class fichero{
		//public final static String ruta ="/v1app/sinet_production/FTPRPATXT/parametroConfig.txt";
		//public final static String valdRutaGuarda = "/v1app/sinet_production/FTPRPATXT/parametroConfig.txt";
		public final static String ruta ="D:/parametroConfig.txt";
		public final static String valdRutaGuarda = "D:/parametroConfig.txt";
	}
	
	/*Se coloca el nombre del mes en archivo de configuracion */
	public static String nomMes(String mes) {

		String mounth = new String();

		switch (mes) {
		case "01":
			mounth = "Enero";
			break;

		case "02":
			mounth = "Febrero";
			break;
		case "03":
			mounth = "Marzo";
			break;
		case "04":
			mounth = "Abril";
			break;

		case "05":
			mounth = "Mayo";
			break;

		case "06":
			mounth = "Junio";
			break;
		case "07":
			mounth = "Julio";
			break;

		case "08":
			mounth = "Agosto";
			break;

		case "09":
			mounth = "Septiembre";
			break;
		
		case "10":
			mounth = "Octubre";
			break;
	
		case "11":
			mounth = "Noviembre";
			break;
	
		case "12":
			mounth = "Diciembre";
			break;

		}
		return mounth;
	}

	/*Se coloca la validacion  de que   cuando el mes llega en String ejemplo 5 
	 * coloque el antes 0 antes del digito 05*/
	public static String validacionMes(int mes) {
		
		String validacionMes="";
		
		if (mes<10) {
			validacionMes="0"+mes;
			System.out.println(validacionMes);
		} else {
			validacionMes=Integer.toString(mes);
				System.out.println("dentro al else "+validacionMes);
		}
		return validacionMes;
		
	}

	/*
	 * Se crea el metodo para generar el archivo de configuracion para llevarlo al
	 * servidor:172.23.30.36
	 */
	public static String crearArchivo(String nitt,String nombreCorto,String ano,int mes,int florin) {
		
		String nit= curacao.nit;
		String mesValido = validacionMes(mes);
		String nombreMes = nomMes(mesValido);
	
		
		/*Codigo de borrar y crear  txto plano */
		File fichero = new File(HelperEF.fichero.ruta);
		logger.info("nombre del mes validar  "+nombreMes);
		if (fichero.delete()) {
			System.out.println("El fichero se a borrado satisfactoriamente ");
		}else {
			System.out.println("No se borro el fichero");
		}
		FileWriter flwriter = null;
		try {
			//crea el flujo para escribir en el archivo
			flwriter = new FileWriter(HelperEF.fichero.ruta);
			//crea un buffer o flujo intermedio antes de escribir directamente en el archivo
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
				
				if(nitt.equals(nit)) {
						bfwriter.write( "Parametros"+"|**"+"\n"+"Empresa"+"|"+nombreCorto+"|"+"parametroEmpresa"+"|**"+"\n"+
						"Nombre del archivo de configuracion"+"|"+"config"+nombreCorto+".ini"+"|**"+"\n"+
						"Parametro 1 del script BCEMNG"+"|"+ano+"|"+"parametroAnio"+"|**"+"\n"+
						"Parametro 2 del script BCEMNG"+"|"+mesValido+"|"+"parametroMes"+"|"+nombreMes+"**"+"\n"+
						"Nit empresa"+"|"+nitt+"|"+"parametroNit"+"|**"+"\n"+
						"Parametro 3 del script BCEMNG"+"|"+florin+"|"+"Florin"+"|**"+"\n");
						}else {
							bfwriter.write( "Parametros"+"|**"+"\n"+"Empresa"+"|"+nombreCorto+"|"+"parametroEmpresa"+"|**"+"\n"+
									"Nombre del archivo de configuracion"+"|"+"config"+nombreCorto+".ini"+"|**"+"\n"+
									"Parametro 1 del script BCEMNG"+"|"+ano+"|"+"parametroAnio"+"|**"+"\n"+
									"Parametro 2 del script BCEMNG"+"|"+mesValido+"|"+"parametroMes"+"|"+nombreMes+"**"+"\n"+
									"Nit empresa"+"|"+nitt+"|"+"parametroNit|**"+"\n");
							
						}
			 
		
			//cierra el buffer intermedio
			bfwriter.close();
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (flwriter != null) {
				try {//cierra el flujo principal
					flwriter.close();
				} catch (IOException e) {
					 e.printStackTrace();
				}
			}
		}
		return "Archivo creado satisfactoriamente..";
		
	}
	
	
}
