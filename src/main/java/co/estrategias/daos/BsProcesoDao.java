package co.estrategias.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.estrategias.entities.BsProceso;

@Repository
public interface BsProcesoDao extends JpaRepository<BsProceso, Long>{

}
