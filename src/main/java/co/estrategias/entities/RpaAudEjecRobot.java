package co.estrategias.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RPA_AUD_EJEC_ROBOT")

public class RpaAudEjecRobot {
	
	
	@Id
	@Column(name = "ID")
	private int id;
	private String EMPRESA;
	private String ANO;
	private int MES;
	private String USUARIO_CREACION;
	private Date FECHA_CREACION;
	private int FLORIN;

	
	
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

	public String getANO() {
		return ANO;
	}

	public void setANO(String aNO) {
		ANO = aNO;
	}

	public int getMES() {
		return MES;
	}

	public void setMES(int mES) {
		MES = mES;
	}

	public String getUSUARIO_CREACION() {
		return USUARIO_CREACION;
	}

	public void setUSUARIO_CREACION(String uSUARIO_CREACION) {
		USUARIO_CREACION = uSUARIO_CREACION;
	}

	public Date getFECHA_CREACION() {
		return FECHA_CREACION;
	}

	public void setFECHA_CREACION(Date fECHA_CREACION) {
		FECHA_CREACION = fECHA_CREACION;
	}

	public int getFLORIN() {
		return FLORIN;
	}

	public void setFLORIN(int fLORIN) {
		FLORIN = fLORIN;
	}

	
	
	public RpaAudEjecRobot() {
		super();
	}

	public RpaAudEjecRobot(int id, String eMPRESA, String aNO, int mES, String uSUARIO_CREACION, Date fECHA_CREACION,
			int fLORIN) {
		super();
		this.id = id;
		EMPRESA = eMPRESA;
		ANO = aNO;
		MES = mES;
		USUARIO_CREACION = uSUARIO_CREACION;
		FECHA_CREACION = fECHA_CREACION;
		FLORIN = fLORIN;
	}

	

}
