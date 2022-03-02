package co.estrategias.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="RPA_ESTADOSFINANCIEROS")
public class RpaEstadosFinacierosFtp {
	
	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "EMPRESA") // NIT DE BS_EMPRESA
	private String EMPRESA;
	
	@Column(name = "RUTAPARAMETRO") // NIT DE BS_EMPRESA
	private String RUTAPARAMETRO;

	@Column(name = "RUTABAT") // NIT DE BS_EMPRESA
	private String RUTABAT;
	
	@Column(name = "ESTADO") // NIT DE BS_EMPRESA
	private int ESTADO;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpresa() {
		return EMPRESA;
	}

	public void setEmpresa(String empresa) {
		this.EMPRESA = empresa;
	}

	public String getRUTAPARAMETRO() {
		return RUTAPARAMETRO;
	}

	public void setRUTAPARAMETRO(String rUTAPARAMETRO) {
		RUTAPARAMETRO = rUTAPARAMETRO;
	}

	public String getRUTABAT() {
		return RUTABAT;
	}

	public void setRUTABAT(String rUTABAT) {
		RUTABAT = rUTABAT;
	}

	public int getESTADO() {
		return ESTADO;
	}

	public int setESTADO(int eSTADO) {
		return ESTADO = eSTADO;
	}

	public RpaEstadosFinacierosFtp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RpaEstadosFinacierosFtp(int id, String empresa, String rUTAPARAMETRO, String rUTABAT, int eSTADO) {
		super();
		this.id = id;
		this.EMPRESA = empresa;
		RUTAPARAMETRO = rUTAPARAMETRO;
		RUTABAT = rUTABAT;
		ESTADO = eSTADO;
	}

	
}
