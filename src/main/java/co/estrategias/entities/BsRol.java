package co.estrategias.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "BS_ROLES")
public class BsRol implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ROLECODI")
	private Long roleCodi; 
	
	@Column(name="ROLEDESC")
	private String roleDesc;

	public BsRol() {
		super();
	}

	public BsRol(Long roleCodi, String roleDesc) {
		super();
		this.roleCodi = roleCodi;
		this.roleDesc = roleDesc;
	}

	public Long getRoleCodi() {
		return roleCodi;
	}

	public void setRoleCodi(Long roleCodi) {
		this.roleCodi = roleCodi;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
