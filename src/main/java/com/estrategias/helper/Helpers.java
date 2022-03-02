package com.estrategias.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Helpers {

	public static String nitSinet = "9010085883";
	public static String plataforma = "SINETPROD";

	//public static String nitSinet="9010085883";
	//public static String plataforma ="SINETDEMO";



	public static String getUltimoDiaMes(String mounth, String year) {

		int mes = Integer.parseInt(mounth);
		int anios = Integer.parseInt(year);
		Calendar cal = Calendar.getInstance();
		cal.set(anios, mes - 1, 1);
		int ultimoDia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		String fechaInicio = "" + ultimoDia + "/" + mes + "/" + anios;
		return fechaInicio;
	}

	public static String FechaActual() {

		Calendar c = Calendar.getInstance();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH) + 1);
		String annio = Integer.toString(c.get(Calendar.YEAR));
		String fechaActual = dia + "/" + mes + "/" + annio;
		return fechaActual;

	}

	public static String FechaMonito() {

		Calendar c = Calendar.getInstance();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH) + 1);
		String annio = Integer.toString(c.get(Calendar.YEAR));
		String fechaFinal = "01" + "/" + mes + "/" + annio;

		return fechaFinal;
	}

	public final class Sanadres {
		public final static int ZONA = 2565;

	}

	public final class tulua {
		public final static int ZONA = 1092;

	}

	public final class Cartago {

		public final static int ZONA = 1957;
	}

	public final class CAUCA {

		public final static int ZONA = 1082;
	}
	public static String ultimoDiaMes(String mounth, String year) {

		int mes = Integer.parseInt(mounth);
		int anios = Integer.parseInt(year);
		Calendar cal = Calendar.getInstance();
		cal.set(anios, mes - 1, 1);
		int ultimoDia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		String fechaInicio = "" + ultimoDia + "/" + mes + "/" + anios;
		return fechaInicio;
	}
	
	
	public static String getDiaSemana(String fecha) {

        String dia = new String();
      try {
          Date fec = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
          Calendar cal = Calendar.getInstance();
          cal.setTime(fec);

          switch (cal.get(Calendar.DAY_OF_WEEK)) {

              case 1:
                  dia = "DOMINGO";
                  break;
              case 2:
                  dia = "LUNES";
                  break;
              case 3:
                  dia = "MARTES";
                  break;
              case 4:
                  dia = "MIERCOLES";
                  break;
              case 5:
                  dia = "JUEVES";
                  break;
              case 6:
                  dia = "VIERNES";
                  break;
              case 7:
                  dia = "SABADO";
                  break;

          }

      } catch (ParseException ex) {
         
          System.out.println("Error getFecha  " + ex);
             return new String();
      }
      return dia;

  }
	
	
	public static int obtenerUltimoDiaMes (int mes) {

		Calendar calendario=Calendar.getInstance();		
		int  anio =calendario.get(Calendar.YEAR);
		calendario.set(anio, mes-1, 1);
		return calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
		 
		}
	
	public static int yearsActual () {

		Calendar calendario=Calendar.getInstance();		
		int  anio =calendario.get(Calendar.YEAR);
		
		return anio;
		 
		}
}
