package co.estrategias.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "BS_SUBPROCESOS")
public class BsSubproceso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long idSubproceso;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@ManyToOne()
	@JoinColumn(name="ID_PROCESO")
	private BsProceso proceso;

	public BsSubproceso() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BsSubproceso(Long idSubproceso, String descripcion, BsProceso proceso) {
		super();
		this.idSubproceso = idSubproceso;
		this.descripcion = descripcion;
		this.proceso = proceso;
	}

	public Long getIdSubproceso() {
		return idSubproceso;
	}

	public void setIdSubproceso(Long idSubproceso) {
		this.idSubproceso = idSubproceso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BsProceso getProceso() {
		return proceso;
	}

	public void setProceso(BsProceso proceso) {
		this.proceso = proceso;
	}
	
}
