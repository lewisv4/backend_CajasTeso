package co.estrategias.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RPA_ESTADOSFINANCIEROS")
public class RpaEstadosFinancieros {
	
		@Id
		@Column(name = "ID") // Id registro garantizados
		private Integer id;
		
		@Column(name = "EMPRESA") // Cédula usuario logeado
		private String empresa;
		
		@Column(name = "RUTAPARAMETRO") // Cédula usuario logeado
		private String rutaParametro;
		
		@Column(name = "RUTABAT") // Cédula usuario logeado
		private String rutaBat;
		
		@Column(name = "ESTADO") // Cédula usuario logeado
		private Integer estado;

		public RpaEstadosFinancieros() {}
		
		public RpaEstadosFinancieros(Integer id, String empresa, String rutaParametro, String rutaBat, Integer estado) {
			super();
			this.id = id;
			this.empresa = empresa;
			this.rutaParametro = rutaParametro;
			this.rutaBat = rutaBat;
			this.estado = estado;
		}
		public Integer getId() {return id;}
		public void setId(Integer id) {this.id = id;}
		public String getEmpresa() {return empresa;}
		public void setEmpresa(String empresa) {this.empresa = empresa;}
		public String getRutaParametro() {return rutaParametro;}
		public void setRutaParametro(String rutaParametro) {this.rutaParametro = rutaParametro;}
		public String getRutaBat() {return rutaBat;}
		public void setRutaBat(String rutaBat) {this.rutaBat = rutaBat;}
		public Integer getEstado() {return estado;}
		public void setEstado(Integer estado) {this.estado = estado;}

		@Override
		public String toString() {
			return "RpaEstadosFinancieros [id=" + id + ", empresa=" + empresa + ", rutaParametro=" + rutaParametro
					+ ", rutaBat=" + rutaBat + ", estado=" + estado + "]";
		}
		
		
		
		
}
