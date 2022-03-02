package co.estrategias.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import co.estrategias.entities.BsAudGarantizados;

public class BsAudGarantizadosDaoPrueba {
	/*
	 BsAudGarantizados bsAudGarantizados;
	 
	 public BsAudGarantizadosDao() {
		 bsAudGarantizados = new BsAudGarantizados();
	 }
	 */
	 public void crearRegistro(BsAudGarantizados bsAudGarantizados) {
		 System.out.println("Objeto bsAudGarantizados: "+bsAudGarantizados.toString());
		 try {

				String sql = "INSERT INTO  BS_AUDGARANTIZADOS P ("
						+ "P.IDGARANTIZADOS , " + // 1
						" P.USUARIOLOGEADO, " + // 2
						" P.FECHASISTEMA, " + // 3
						" P.NITEMPRESA, " + // 4
						" P.RAZONEMPRESA) " + // 5
						" VALUES (?,?,?,?,?)";
				System.out.println("Metodo que inserta registro de utilización de RPA Grantizados");

				// Quemo la credenciales para ingresar a la base de datos en Sinte demo
				
				DriverManagerDataSource dataSource = new DriverManagerDataSource();
		        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		        dataSource.setUrl("jdbc:oracle:thin:@172.23.30.116:1521:FPLPOOL");
		        dataSource.setUsername("SINET");
		        dataSource.setPassword("Gly65Pct");
				
		        // **************************************************************
		        
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {

						//RpaAudEjecRobot BSauditoria = financiero.get(i);
						//ps.setInteger(1, bsAudGarantizados.getId());
						ps.setString(2, bsAudGarantizados.getUsuarioLogeado());
						//ps.setDate(3, bsAudGarantizados.getFechaSistema());
						ps.setString(4, bsAudGarantizados.getNitEmpresa());
						ps.setString(5, bsAudGarantizados.getRazonEmpresa());
					}

					@Override
					public int getBatchSize() {
						// return financiero.size();
						return 0;
					}
				});

				// return financiero.size();
				
			} catch (Exception e) {
				System.out.println("Error insertPromediosPtoVtSpeciales(): " + e.toString());
			}
			
	 }
	
}
