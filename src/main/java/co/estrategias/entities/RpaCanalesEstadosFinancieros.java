package co.estrategias.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RPA_CANALES_ESTADOSFINANCIEROS")
public class RpaCanalesEstadosFinancieros {
	
	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "SERVERNAME")
	private String serverName;
	
	@Column(name = "PORT")
	private int port;
	
	@Column(name = "USUARIO")
	private String usuario;
	
	@Column(name = "PASSWORD")
	private String password ;
	
	public RpaCanalesEstadosFinancieros(int id, String serverName, int port, String usuario, String password) {
		super();
		this.id = id;
		this.serverName = serverName;
		this.port = port;
		this.usuario = usuario;
		this.password = password;
	}
	public RpaCanalesEstadosFinancieros() {super();}
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getServerName() {return serverName;}
	public void setServerName(String serverName) {this.serverName = serverName;}
	public int getPort() {return port;}
	public void setPort(int port) {this.port = port;}
	public String getUsuario() {return usuario;}
	public void setUsuario(String usuario) {this.usuario = usuario;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;
	}
	@Override
	public String toString() {
		return "RpaCanalesEstadoFinanciero [id=" + id + ", ServerName=" + serverName + ", port=" + port + ", Usuario="
				+ usuario + ", password=" + password + "]";
	}
	
	
	
	

}
