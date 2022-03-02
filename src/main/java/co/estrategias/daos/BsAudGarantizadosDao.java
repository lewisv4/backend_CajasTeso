package co.estrategias.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.estrategias.entities.BsAudGarantizados;

@Repository
public interface BsAudGarantizadosDao extends JpaRepository<BsAudGarantizados, Integer> {
	/*
	@Query(value = "INSERT INTO BS_AUDGARANTIZADOS (ID, USUARIOLOGEADO, FECHASISTEMA, NITEMPRESA, RAZONEMPRESA)"
			+ "VALUES (?1, ?2, ?3, ?4, ?5) ", nativeQuery = true)
	public List<Object> registroDatos(Integer id, String usuarioLogeado, String fechaSistema, String nitEmpresa, String razonEmpresa);
	*/
	/*
	//Obtiene cantidad registros para poder determinar el id
    @Query(value = "SELECT COUNT(*) FROM BS_AUDGARANTIZADOS", nativeQuery = true)
    public int obtieneUltimoId();
    */
	
  //Obtiene cantidad registros para poder determinar el id
    @Query(value = "SELECT CASE WHEN MAX(a.id) IS NULL THEN 0 ELSE MAX(a.id) END FROM BS_AUDGARANTIZADOS a", nativeQuery = true)
    public int obtieneUltimoId();
    

}
