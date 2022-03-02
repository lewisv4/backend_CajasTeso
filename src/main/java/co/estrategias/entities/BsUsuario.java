package co.estrategias.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "BS_USUARIOS")
public class BsUsuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id	
	@Column(name="USUACEDU")
	private String usuacedu;

	@Column(name="USUAPRNO")
	private String usuaprno;

	@Column(name="USUASENO")
	private String usuaseno;

	@Column(name="USUAPRAP")
	private String usuaprap;

	@Column(name="USUASEAP")
	private String usuaseap;
	
	@Column(name="USUALOGI")
	private String usualogi;

	@Column(name="USUAPASS")
	@JsonIgnore
	private String usuapass;

	@Column(name="USUAESTA")
	private String usuaesta;

	@Column(name="USUACALO")
	private int usuacalo;

	@ManyToOne
	@JoinColumn(name = "ROLECODI")
	private BsRol rolecodi;

	@ManyToOne
	@JoinColumn(name = "EMPRNIT")
	private BsEmpresa emprnit;

	@Column(name="USUAEMAI")
	private String usuaemai;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="TOKEN")
	private String token;
	
	@Column(name="URL_IMAGE")	
	private String urlImage;
	
	@Column(name="URLIMAGE")	
	private String urlImage2;
	
	@ManyToOne
	@JoinColumn(name = "ID_SUBPROCESO")
	private BsSubproceso bsSubproceso;

	@Column(name="ID_TPCUENTA")	
	private String idTpcuenta;
	
	public BsUsuario() {
		super();
	}
	

	public String getUsuacedu() {
		return usuacedu;
	}

	public void setUsuacedu(String usuacedu) {
		this.usuacedu = usuacedu;
	}

	public String getUsuaprno() {
		return usuaprno;
	}

	public void setUsuaprno(String usuaprno) {
		this.usuaprno = usuaprno;
	}

	public String getUsuaseno() {
		return usuaseno;
	}

	public void setUsuaseno(String usuaseno) {
		this.usuaseno = usuaseno;
	}

	public String getUsuaprap() {
		return usuaprap;
	}

	public void setUsuaprap(String usuaprap) {
		this.usuaprap = usuaprap;
	}

	public String getUsuaseap() {
		return usuaseap;
	}

	public void setUsuaseap(String usuaseap) {
		this.usuaseap = usuaseap;
	}

	public String getUsualogi() {
		return usualogi;
	}

	public void setUsualogi(String usualogi) {
		this.usualogi = usualogi;
	}

	public String getUsuapass() {
		return usuapass;
	}

	public void setUsuapass(String usuapass) {
		this.usuapass = usuapass;
	}

	public String getUsuaesta() {
		return usuaesta;
	}

	public void setUsuaesta(String usuaesta) {
		this.usuaesta = usuaesta;
	}

	public int getUsuacalo() {
		return usuacalo;
	}

	public void setUsuacalo(int usuacalo) {
		this.usuacalo = usuacalo;
	}


	public String getUsuaemai() {
		return usuaemai;
	}

	public void setUsuaemai(String usuaemai) {
		this.usuaemai = usuaemai;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getUrlImage2() {
		return urlImage2;
	}

	public void setUrlImage2(String urlImage2) {
		this.urlImage2 = urlImage2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BsSubproceso getBsSubproceso() {
		return bsSubproceso;
	}

	public void setBsSubproceso(BsSubproceso bsSubproceso) {
		this.bsSubproceso = bsSubproceso;
	}

	public BsRol getRolecodi() {
		return rolecodi;
	}

	public void setRolecodi(BsRol rolecodi) {
		this.rolecodi = rolecodi;
	}

	public BsEmpresa getEmprnit() {
		return emprnit;
	}

	public void setEmprnit(BsEmpresa emprnit) {
		this.emprnit = emprnit;
	}

	public String getIdTpcuenta() {
		return idTpcuenta;
	}

	public void setIdTpcuenta(String idTpcuenta) {
		this.idTpcuenta = idTpcuenta;
	}
	
	
	
}
