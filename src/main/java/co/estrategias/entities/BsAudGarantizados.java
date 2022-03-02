package co.estrategias.entities;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "BS_AUDGARANTIZADOS")
public class BsAudGarantizados{

	@Id
	@Column(name = "ID") // Id registro garantizados
	private Integer id;
	
	@Column(name = "USUARIOLOGEADO") // Cédula usuario logeado
	private String usuarioLogeado;
		
	//@Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "FECHASISTEMA") // Fecha del sistema
	private Date fechaSistema;
	
	@Column(name = "NITEMPRESA") // Nit de la empresa
	private String nitEmpresa;
	
	@Column(name = "RAZONEMPRESA") // Nombre de la empresa
	private String razonEmpresa;
	
	public BsAudGarantizados(Integer id, String usuarioLogeado, Date fechaSistema, String nitEmpresa, String razonEmpresa) {
		super();
		this.id = id;
		this.usuarioLogeado = usuarioLogeado;
		this.fechaSistema = fechaSistema;
		this.nitEmpresa = nitEmpresa;
		this.razonEmpresa = razonEmpresa;
	}
	
	public BsAudGarantizados() {super();} // Constructor vacio
	
	public Integer getId() {return id;}
	public void setId(Integer id) {this.id = id;}
	public String getUsuarioLogeado() {return usuarioLogeado;}
	public void setUsuarioLogeado(String usuarioLogeado) {this.usuarioLogeado = usuarioLogeado;}
	public Date getFechaSistema() {return fechaSistema;}
	public void setFechaSistema(Date fechaSistema) {this.fechaSistema = fechaSistema;}
	public String getNitEmpresa() {return nitEmpresa;}
	public void setNitEmpresa(String nitEmpresa) {this.nitEmpresa = nitEmpresa;}
	public String getRazonEmpresa() {return razonEmpresa;}
	public void setRazonEmpresa(String razonEmpresa) {this.razonEmpresa = razonEmpresa;}

	@Override
	public String toString() {
		return "BsAudGarantizados [id=" + id + ", usuarioLogeado=" + usuarioLogeado + ", fechaSistema=" + fechaSistema
				+ ", nitEmpresa=" + nitEmpresa + ", razonEmpresa=" + razonEmpresa + "]";
	}

}
