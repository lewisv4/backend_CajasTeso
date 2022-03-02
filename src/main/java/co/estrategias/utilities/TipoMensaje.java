package co.estrategias.utilities;

public class TipoMensaje {
	
	public static String typeMensaje(int estado) {
        String mensaje=new String();
       
        switch(estado){
        
            case 1:
                mensaje="modal";
                break;
                
            case 2:
                mensaje="E1";
           break;     
        }

           
                return mensaje;
    }
}
