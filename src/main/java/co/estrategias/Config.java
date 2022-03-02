package co.estrategias;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import co.estrategias.entities.ConexionesEstrategias;

public class Config {
//SE GENERA LA CONEXION DE BASE DE DATOS
	public DataSource conexion(ConexionesEstrategias con) {
		System.out.println(con.getUrl());
		System.out.println(con.getUsuario());
		System.out.println(con.getPassword());
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(con.getUrl());
		driverManagerDataSource.setUsername(con.getUsuario());
		driverManagerDataSource.setPassword(con.getPassword());
		driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		return driverManagerDataSource;
	}
}
