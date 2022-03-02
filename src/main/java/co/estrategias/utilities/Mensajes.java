package co.estrategias.utilities;

public class Mensajes {

	 public static void main(String[] args) {
	        // TODO code application logic here
	        
	        int estado=0;//toma de la base de datos
	        int banderaTipoMensaje=0; //Este es el dato para comparar y mostrar el mensaje
	        String mensajeType=new String();
	        
	        if(estado!=0){
	        
	            if(estado==1){
	            banderaTipoMensaje=1; 
	            mensajeType=TipoMensaje.typeMensaje(1);//mensaje del modal, es decir trae el mensaje con el id que llega desdela base de datos
	            }
	            
	            else{
	            mensajeType=TipoMensaje.typeMensaje(estado);
	            banderaTipoMensaje=1;
	            }
	        }
	        
	        if(estado==0){
	        banderaTipoMensaje=0;
	        }
	              
	        System.out.println("Se muestra el mensaje= " + mensajeType); 
	        System.out.println("Bandera= " + banderaTipoMensaje); 
	              
	    }

}
