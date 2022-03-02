package co.estrategias.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RPA_ESTADOSFINANCIEROS_PERMISO")

public class RpaEstadosFinacierosPermisos {

	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "EMPRESA") // NIT DE BS_EMPRESA
	private String EMPRESA;

	@Column(name = "CEDULA") // cedula de usuario en Bs_usuarios
	private String CEDULA;

	@Column(name = "ROL") // Rol en Bs_usuarios
	private String ROL;

	private Date FECHA_CREACION;
	private String nombre_empresa;
	
	@Column(name = "USUARIO_CREACION") // auditoria
	private String USUARIO_CREACION;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEMPRESA() {
		return EMPRESA;
	}

	public void setEMPRESA(String eMPRESA) {
		EMPRESA = eMPRESA;
	}

	public String getCEDULA() {
		return CEDULA;
	}

	public void setCEDULA(String cEDULA) {
		CEDULA = cEDULA;
	}

	public String getROL() {
		return ROL;
	}

	public void setROL(String rOL) {
		ROL = rOL;
	}

	public Date getFECHA_CREACION() {
		return FECHA_CREACION;
	}

	public void setFECHA_CREACION(Date fECHA_CREACION) {
		FECHA_CREACION = fECHA_CREACION;
	}

	public String getNombre_empresa() {
		return nombre_empresa;
	}

	public void setNombre_empresa(String nombre_empresa) {
		this.nombre_empresa = nombre_empresa;
	}

	public String getUSUARIO_CREACION() {
		return USUARIO_CREACION;
	}

	public void setUSUARIO_CREACION(String uSUARIO_CREACION) {
		USUARIO_CREACION = uSUARIO_CREACION;
	}

	
	public RpaEstadosFinacierosPermisos() {
		super();
	}

	public RpaEstadosFinacierosPermisos(int id, String eMPRESA, String cEDULA, String rOL, Date fECHA_CREACION,
			String nombre_empresa, String uSUARIO_CREACION) {
		super();
		this.id = id;
		EMPRESA = eMPRESA;
		CEDULA = cEDULA;
		ROL = rOL;
		FECHA_CREACION = fECHA_CREACION;
		this.nombre_empresa = nombre_empresa;
		USUARIO_CREACION = uSUARIO_CREACION;
	}

	
	
}
