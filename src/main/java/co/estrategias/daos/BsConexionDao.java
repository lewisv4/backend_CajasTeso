package co.estrategias.daos;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.estrategias.entities.ConexionesEstrategias;



public interface BsConexionDao extends CrudRepository<ConexionesEstrategias, Long>, Serializable{

	public List<ConexionesEstrategias>findByNitAndTipo(String nit,int tipo);
	public List<ConexionesEstrategias>findByNitAndPlataforma(String nit,String plataforma);
	public List<ConexionesEstrategias>findByNitAndPlataformaAndTipoAndUsuario(String nit,String plataforma, int tipo, String usuario);
	
	@Query(value =  " SELECT H1.tocadezo, " +
			"       H1.tocadece, " + 
			"       H1.tocadepu, " + 
			"       SUM(H1.total_efectivo) total_efectivo, " + 
			"       (CASE " + 
			"            WHEN SUM(H1.total_efectivo) <= H1.tocatomi THEN SUM(H1.total_efectivo) - H1.tocatomi " + 
			"            WHEN SUM(H1.total_efectivo) >= H1.tocatoma THEN SUM(H1.total_efectivo) - H1.tocatomi " + 
			"            ELSE 0 " + 
			"        END) saldo, " + 
			"       ROUND((SUM(H1.total_efectivo) / H1.tocatoma) * 100, 3) porc " + 
			"FROM " + 
			"  (SELECT V.ACVTOPER, " + 
			"          t.tocacozo, " + 
			"          t.tocadezo, " + 
			"          t.tocacoce, " + 
			"          t.tocadece, " + 
			"          t.tocacopu, " + 
			"          t.tocadepu, " + 
			"          t.tocatomi, " + 
			"          t.tocatoma, " + 
			"          sum(v.acvttovt) total_efectivo " + 
			"   FROM BS_TOPES_CAJAS t, " + 
			"        bs_acumula_vtas v " + 
			"   WHERE v.acvtfech = trunc(sysdate) " + 
			"     AND v.acvtcozo = t.tocacozo " + 
			"     AND v.acvtcoce = t.tocacoce " + 
			"     AND v.acvtcopu = t.tocacopu " + 
			"     AND v.acvtcozo = :acvtcozo " + 
			"     AND v.acvtcoce = :acvtcoce " + 
			"     AND v.acvtcopu = :acvtcopu " + 
			"     AND v.emprnit = :emprnit " + 
			"     AND V.ACVTTOVT <> 0 " + 
			"   GROUP BY V.ACVTOPER, " + 
			"            t.tocacozo, " + 
			"            t.tocadezo, " + 
			"            t.tocacoce, " + 
			"            t.tocadece, " + 
			"            t.tocacopu, " + 
			"            t.tocadepu, " + 
			"            t.tocatomi, " + 
			"            t.tocatoma) H1 " + 
			"GROUP BY H1.tocacozo, " + 
			"         H1.tocadezo, " + 
			"         H1.tocacoce, " + 
			"         H1.tocadece, " + 
			"         H1.tocacopu, " + 
			"         H1.tocadepu, " + 
			"         H1.tocatomi, " + 
			"         H1.tocatoma " + 
			"HAVING (CASE " + 
			"            WHEN SUM(H1.total_efectivo) <= H1.tocatomi THEN SUM(H1.total_efectivo) - H1.tocatomi " + 
			"            WHEN SUM(H1.total_efectivo) >= H1.tocatoma THEN SUM(H1.total_efectivo) - H1.tocatoma " + 
			"            ELSE 0 " + 
			"        END) != 0", nativeQuery = true)

List<Object[]> findBySaldoPunto(@Param("acvtcozo") int acvtcozo,
							 @Param("acvtcoce") int acvtcoce,
							 @Param("acvtcopu") int acvtcopu,
							 @Param("emprnit") String emprnit);
@Query(value =  "SELECT u.usuaemai " + 
		"FROM bs_costos_user c, bs_usuarios u " + 
		"WHERE c.cecocodi = :cecocodi " + 
		"  AND c.puntcodi = :puntcodi " + 
		"  AND u.usuacedu = c.usuacedu " + 
		"  AND u.usuaemai IS NOT NULL", nativeQuery = true)

List<Object[]> findEmails(@Param("cecocodi") int cecocodi,
				 @Param("puntcodi") int puntcodi);


}