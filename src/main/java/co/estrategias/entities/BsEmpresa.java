package co.estrategias.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "BS_EMPRESAS")
public class BsEmpresa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EMPRNIT") // NIT EMPRESA
	private String emprnit;

	@Column(name = "EMPRRAZO") // RAZON SOCIAL EMPRESA
	private String emprrazo;

	@Column(name = "EMPRRELE") // REPRESENTANTE LEGAL
	private String emprrele;

	@Column(name = "EMPRTIPO") // TIPO EMPRESA (0 - PRNCIPAL, 1 - CLIENTES)
	private int emprtipo;

	@Column(name = "EMPRNOCO") // NOMBRE CORTO EMPRESA
	private String emprnoco;

	@Column(name = "NITCOMERCIAL") // NIT COMERCIAL
	private String nitComercial;

	/*
	@ManyToMany(mappedBy = "empresaList", fetch = FetchType.EAGER)
	//@JsonBackReference
    @JsonProperty(access = Access.WRITE_ONLY)
	private List<BsMensaje> bsMensaje = new ArrayList<BsMensaje>();

*/

	public BsEmpresa() {
	}

	public BsEmpresa(String emprnit, String emprrazo, String emprrele, int emprtipo, String emprnoco,
			String nitComercial) {
		super();
		this.emprnit = emprnit;
		this.emprrazo = emprrazo;
		this.emprrele = emprrele;
		this.emprtipo = emprtipo;
		this.emprnoco = emprnoco;
		this.nitComercial = nitComercial;
	}


	public String getEmprnit() {
		return emprnit;
	}

	public void setEmprnit(String emprnit) {
		this.emprnit = emprnit;
	}

	public String getEmprrazo() {
		return emprrazo;
	}

	public void setEmprrazo(String emprrazo) {
		this.emprrazo = emprrazo;
	}

	public String getEmprrele() {
		return emprrele;
	}

	public void setEmprrele(String emprrele) {
		this.emprrele = emprrele;
	}

	public int getEmprtipo() {
		return emprtipo;
	}

	public void setEmprtipo(int emprtipo) {
		this.emprtipo = emprtipo;
	}

	public String getEmprnoco() {
		return emprnoco;
	}

	public void setEmprnoco(String emprnoco) {
		this.emprnoco = emprnoco;
	}

	public String getNitComercial() {
		return nitComercial;
	}

	public void setNitComercial(String nitComercial) {
		this.nitComercial = nitComercial;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BsEmpresa [emprnit=" + emprnit + ", emprrazo=" + emprrazo + ", emprrele=" + emprrele + ", emprtipo="
				+ emprtipo + ", emprnoco=" + emprnoco + ", nitComercial=" + nitComercial + "]";
	}

}
