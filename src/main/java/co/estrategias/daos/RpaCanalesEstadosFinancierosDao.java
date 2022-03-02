package co.estrategias.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.estrategias.entities.RpaCanalesEstadosFinancieros;

@Repository
public interface RpaCanalesEstadosFinancierosDao extends JpaRepository<RpaCanalesEstadosFinancieros, Integer> {

	@Query(value = "SELECT * FROM RPA_CANALES_ESTADOSFINANCIEROS WHERE ID = 3", nativeQuery = true)
    public RpaCanalesEstadosFinancieros getCredencialesFtp();
	
	@Query(value = "SELECT * FROM RPA_CANALES_ESTADOSFINANCIEROS WHERE ID = 2", nativeQuery = true)
    public RpaCanalesEstadosFinancieros getCredencialesSsh();
}


