package co.estrategias.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bs_conexiones")
public class ConexionesEstrategias {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String usuario;
	String url;
	String nit;
	String password;
	String plataforma;
	int tipo;//0 producion,1 replica
	public ConexionesEstrategias() {}
	
	
	public ConexionesEstrategias(Long id, String usuario, String url, String nit, String password, String plataforma,
			int tipo) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.url = url;
		this.nit = nit;
		this.password = password;
		this.plataforma = plataforma;
		this.tipo = tipo;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	

	public int getTipo() {
		return tipo;
	}


	public void setTipo(int tipo) {
		this.tipo = tipo;
	}


	@Override
	public String toString() {
		return "ConexionesEstrategias [id=" + id + ", usuario=" + usuario + ", url=" + url + ", nit=" + nit
				+ ", password=" + password + ", plataforma=" + plataforma + ", tipo=" + tipo + "]";
	}

	
	
}
