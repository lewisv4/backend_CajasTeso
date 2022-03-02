
package co.estrategias.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RPA_CANALES_ESTADOSFINANCIEROS")
public class RpaCanalesEstadoFinanciero {
	
	// Se le agregaron las columnas a los demás 
	
	@Id
	@Column(name = "ID")
	private int id;
	@Column(name = "SERVERNAME")
	private String ServerName;
	@Column(name = "PORT")
	private int port;
	@Column(name = "USUARIO")
	private String Usuario;
	@Column(name = "PASSWORD")
	private String password ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getServerName() {
		return ServerName;
	}
	public void setServerName(String serverName) {
		ServerName = serverName;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUsuario() {
		return Usuario;
	}
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public RpaCanalesEstadoFinanciero(int id, String serverName, int port, String usuario, String password) {
		super();
		this.id = id;
		ServerName = serverName;
		this.port = port;
		Usuario = usuario;
		this.password = password;
	}
	public RpaCanalesEstadoFinanciero() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "RpaCanalesEstadoFinanciero [id=" + id + ", ServerName=" + ServerName + ", port=" + port + ", Usuario="
				+ Usuario + ", password=" + password + "]";
	}
	
	
}
