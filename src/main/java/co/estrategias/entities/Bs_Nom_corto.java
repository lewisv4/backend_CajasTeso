package co.estrategias.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BS_NOM_CORTO_EMP")
public class Bs_Nom_corto {
	@Id
	@Column(name = "ID")
	private int id;
	private String nit; 
	private String nombre;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Bs_Nom_corto() {
		super();
	}
	public Bs_Nom_corto(int id, String nit, String nombre) {
		super();
		this.id = id;
		this.nit = nit;
		this.nombre = nombre;
	}
	
	
	

}
