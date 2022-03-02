package co.estrategias.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import co.estrategias.entities.Bs_Nom_corto;

import co.estrategias.entities.FtpRpaReglas;
import co.estrategias.entities.RpaAudEjecRobot;
import co.estrategias.entities.RpaCanalesEstadoFinanciero;
import co.estrategias.entities.RpaEstadosFinacierosFtp;
import co.estrategias.entities.RpaEstadosFinacierosPermisos;
import co.estrategias.entities.RpaMensajeEstadoFinanciero;
import co.estrategias.entities.SshRpaReglas;



@Repository
public class RpaEstadoFincieroDAO {

	public List<RpaEstadosFinacierosPermisos> getCedulaPermiso(DataSource datasource, String cedula) {

		try {

			String sql = " select p.empresa,p.nombre_empresa  from rpa_estadosfinancieros_permiso p "
					+ " where p.cedula ='" + cedula + "' ";

			List<RpaEstadosFinacierosPermisos> empresaAsignada = new ArrayList<>();
			JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
			jdbcTemplate.query(sql, new ResultSetExtractor<Object>() {
				public List<RpaEstadosFinacierosPermisos> extractData(ResultSet rs) throws SQLException {
					while (rs.next()) {
						RpaEstadosFinacierosPermisos empresa = new RpaEstadosFinacierosPermisos();
						empresa.setEMPRESA(rs.getString(1));
						empresa.setNombre_empresa(rs.getString(2));
						empresaAsignada.add(empresa);
					}

					System.out.println("Registros " + empresaAsignada.size());

					return empresaAsignada;
				}
			});

			return empresaAsignada;

		} catch (Exception e) {
			System.out.println("Error getNombresDePuntosDeVenta(): " + e.toString());
			return null;
		}
	}

	public int insertCamposAuditoria(DataSource datasource, List<RpaAudEjecRobot> financiero) {
		try {

			String sql = "INSERT INTO  RPA_AUD_EJEC_ROBOT P " + "(P.id , " + " P.EMPRESA, " + // 1
					" P.AÑO, " + // 2
					" P.MES, " + // 3
					" P.USUARIO_CREACION," + "P.FLORIN," + "P.FECHA_CREACION) " + // 4
					" VALUES (SEQ_RPA_DIAPAGOCONVENIOS.nextval,?,?,?,?,?,sysdate)";
			System.out.println("Metodo que inserta los informe financiero" + sql);

			JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					
					RpaAudEjecRobot BSauditoria = financiero.get(i);
					ps.setString(1, BSauditoria.getEMPRESA());
					ps.setString(2, BSauditoria.getANO());
					ps.setInt(3, BSauditoria.getMES());
					ps.setString(4, BSauditoria.getUSUARIO_CREACION());
					ps.setInt(5, BSauditoria.getFLORIN());

				}

				@Override
				public int getBatchSize() {
					return financiero.size();
				}
			});

			return financiero.size();

		} catch (Exception e) {
			System.out.println("Error insertPromediosPtoVtSpeciales(): " + e.toString());
			return 0;
		}

	}

	public List<Bs_Nom_corto> getEmprNomCorto(DataSource datasource, String nit) {

		try {

			String sql = " select  * from bs_nom_corto_emp p " + " where p.nit = '" + nit + "'  ";

			List<Bs_Nom_corto> corto = new ArrayList<>();
			JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
			jdbcTemplate.query(sql, new ResultSetExtractor<Object>() {
				public List<Bs_Nom_corto> extractData(ResultSet rs) throws SQLException {
					while (rs.next()) {
						Bs_Nom_corto nombre = new Bs_Nom_corto();
						nombre.setId(rs.getInt(1));
						nombre.setNit(rs.getString(2));
						nombre.setNombre(rs.getString(3));

						corto.add(nombre);
					}

					System.out.println("Registros " + corto.size());

					return corto;
				}
			});

			return corto;

		} catch (Exception e) {
			System.out.println("Error getNombresDePuntosDeVenta(): " + e.toString());
			return null;
		}
	}

	public List<RpaEstadosFinacierosFtp> getRutaGuardado(DataSource datasource, String empresa) {

		try {

			String sql = " select *  from RPA_ESTADOSFINANCIEROS p  " + "where p.empresa ='" + empresa + "' ";

			List<RpaEstadosFinacierosFtp> ruta = new ArrayList<>();
			JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
			jdbcTemplate.query(sql, new ResultSetExtractor<Object>() {
				public List<RpaEstadosFinacierosFtp> extractData(ResultSet rs) throws SQLException {
					while (rs.next()) {
						RpaEstadosFinacierosFtp ejecucion = new RpaEstadosFinacierosFtp();
						ejecucion.setId(rs.getInt(1));
						ejecucion.setEmpresa(rs.getString(2));
						ejecucion.setRUTAPARAMETRO(rs.getString(3));
						ejecucion.setRUTABAT(rs.getString(4));
						ejecucion.setESTADO(rs.getInt(5));
						ruta.add(ejecucion);
					}

					System.out.println("Registros " + ruta.size());

					return ruta;
				}
			});

			return ruta;

		} catch (Exception e) {
			System.out.println("Error getNombresDePuntosDeVenta(): " + e.toString());
			return null;
		}
	}

	public List<RpaEstadosFinacierosFtp> GetEstado(DataSource datasource, String EMPRESA) {
		try {

			String sql = " select * from RPA_ESTADOSFINANCIEROS p " + " where p.empresa='" + EMPRESA + "' ";

			List<RpaEstadosFinacierosFtp> infoEstado = new ArrayList<>();
			JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
			jdbcTemplate.query(sql, new ResultSetExtractor<Object>() {
				public List<RpaEstadosFinacierosFtp> extractData(ResultSet rs) throws SQLException {
					while (rs.next()) {
						RpaEstadosFinacierosFtp ejecucion = new RpaEstadosFinacierosFtp();
						ejecucion.setEmpresa(rs.getString(2));
						ejecucion.setESTADO(rs.getInt(5));
						infoEstado.add(ejecucion);
					}

					System.out.println("Registros " + infoEstado.size());

					return infoEstado;
				}
			});

			return infoEstado;

		} catch (Exception e) {
			System.out.println("Error getNombresDePuntosDeVenta(): " + e.toString());
			return null;
		}
	}


	public List<RpaMensajeEstadoFinanciero> GetEstadoMensaje(DataSource datasource, String EMPRESA) {
		try {

			String sql = "select R.ESTADO, R.MENSAJE "
					+ " from RPA_MENSAJES_FINANCIEROS R "
					+ "WHERE R.ESTADO = (SELECT T.ESTADO FROM RPA_ESTADOSFINANCIEROS T " 
					+ " WHERE T.EMPRESA = '"+ EMPRESA + "')";

			List<RpaMensajeEstadoFinanciero> infoEstado = new ArrayList<>();
			JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
			jdbcTemplate.query(sql, new ResultSetExtractor<Object>() {
				public List<RpaMensajeEstadoFinanciero> extractData(ResultSet rs) throws SQLException {
					while (rs.next()) {
						RpaMensajeEstadoFinanciero ejecucion = new RpaMensajeEstadoFinanciero();
						ejecucion.setEstado(rs.getInt(1));
						ejecucion.setMensaje(rs.getString(2));
						infoEstado.add(ejecucion);
					}

					System.out.println("Registros " + infoEstado.size());

					return infoEstado;
				}
			});

			return infoEstado;

		} catch (Exception e) {
			System.out.println("Error al consultar el estado(): " + e.toString());
			return null;
		}
	}
	
	public List<RpaCanalesEstadoFinanciero>GetCanalesFinanciero(DataSource datasource) {
		try {

			String sql = "select * from RPA_CANALES_ESTADOSFINANCIEROS";

			List<RpaCanalesEstadoFinanciero> canales = new ArrayList<>();
			JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
			jdbcTemplate.query(sql, new ResultSetExtractor<Object>() {
				public List<RpaCanalesEstadoFinanciero> extractData(ResultSet rs) throws SQLException {
					while (rs.next()) {
						RpaCanalesEstadoFinanciero ejecucion = new RpaCanalesEstadoFinanciero();
						ejecucion.setId(rs.getInt(1));
						ejecucion.setServerName(rs.getString(2));
						ejecucion.setPort(rs.getInt(3));
						ejecucion.setUsuario(rs.getString(4));
						ejecucion.setPassword(rs.getString(5));
						canales.add(ejecucion);
					}
					
					System.out.println("Registros " + canales.size());

					return canales;
				}
			});

			return canales;

		} catch (Exception e) {
			System.out.println("Error al consultar GetCanalesFinanciero: " + e.toString());
			return null;
		}
	}
	
	public List<RpaEstadosFinacierosFtp> getEstado(DataSource datasource) {

		try {

			String sql = " select *  from RPA_ESTADOSFINANCIEROS p  " + "where p.estado =1 ";

			List<RpaEstadosFinacierosFtp> ruta = new ArrayList<>();
			JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
			jdbcTemplate.query(sql, new ResultSetExtractor<Object>() {
				public List<RpaEstadosFinacierosFtp> extractData(ResultSet rs) throws SQLException {
					while (rs.next()) {
						RpaEstadosFinacierosFtp ejecucion = new RpaEstadosFinacierosFtp();
						ejecucion.setESTADO(rs.getInt(5));
						ruta.add(ejecucion);
					}

					System.out.println("Registros " + ruta.size());

					return ruta;
				}
			});

			return ruta;

		} catch (Exception e) {
			System.out.println("Error estado en 1(): " + e.toString());
			return null;
		}
	}
	public List<RpaMensajeEstadoFinanciero> getEstadoModal(DataSource datasource) {

		try {

			String sql = " select p.mensaje from RPA_MENSAJES_FINANCIEROS p  " + "where p.estado =4 ";

			List<RpaMensajeEstadoFinanciero> ruta = new ArrayList<>();
			JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
			jdbcTemplate.query(sql, new ResultSetExtractor<Object>() {
				public List<RpaMensajeEstadoFinanciero> extractData(ResultSet rs) throws SQLException {
					while (rs.next()) {
						RpaMensajeEstadoFinanciero ejecucion = new RpaMensajeEstadoFinanciero();
						ejecucion.setMensaje(rs.getString(1));
						ruta.add(ejecucion);
					}

					System.out.println("Registros " + ruta.size());

					return ruta;
				}
			});

			return ruta;

		} catch (Exception e) {
			System.out.println("Error estado en 1(): " + e.toString());
			return null;
		}
	}


}
