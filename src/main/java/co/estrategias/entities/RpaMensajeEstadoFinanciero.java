package co.estrategias.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RPA_MENSAJES_FINANCIEROS")
public class RpaMensajeEstadoFinanciero {
	
	@Id
	@Column(name = "ID")
	private int id;
	@Column(name = "ESTADO")
	private int estado;
	@Column(name = "MENSAJE")
	private String mensaje;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public RpaMensajeEstadoFinanciero(int id, int estado, String mensaje) {
		super();
		this.id = id;
		this.estado = estado;
		this.mensaje = mensaje;
	}


	public RpaMensajeEstadoFinanciero() {
		super();
	}
}
