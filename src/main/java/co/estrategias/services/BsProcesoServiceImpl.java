package co.estrategias.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.estrategias.daos.BsProcesoDao;
import co.estrategias.entities.BsProceso;

@Service
public class BsProcesoServiceImpl implements BsProcesoService{

	@Autowired
	private BsProcesoDao procesoDao;
	
	@Override
	public List<BsProceso> procesos() {
		return procesoDao.findAll();
	}

}
