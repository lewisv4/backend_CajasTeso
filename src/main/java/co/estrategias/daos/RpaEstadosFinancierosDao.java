package co.estrategias.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.estrategias.entities.RpaEstadosFinancieros;

@Repository
public interface RpaEstadosFinancierosDao extends JpaRepository<RpaEstadosFinancieros, Integer> {
	
	//public List<RpaEstadosFinancieros>findByEstados(int estados);
	@Query(value = "SELECT SUM(ESTADO) FROM RPA_ESTADOSFINANCIEROS", nativeQuery = true)
	public int estadosRpa();
	
}
