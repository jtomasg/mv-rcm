package cl.masvida.poc.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.masvida.poc.entities.Rcm;

import com.redhat.masvida.vo.AgenciaVO;
import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.RecepcionCobranzaMedicaVO;

@Stateless
public class RcmDAO implements RcmDAOLocal {

	@PersistenceContext(unitName = "masvida-ejb-jpa")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public RcmDAO() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Método para buscar un RCM vía ID.
	 */
	public RcmVO buscarRcm(BigDecimal i) {
		
		Query query = em.createNamedQuery("Rcm.findByFolio");
		query.setParameter("rcmFolio", i);
		List<Rcm> lsRcms = query.getResultList();
		
		Rcm rcmEntity = null;
		if (lsRcms != null && lsRcms.size() == 1) {
			rcmEntity = lsRcms.get(0);
		}

		//Veamos que tiene la Entity correspondiente
		System.out.println("--------------------------------------------------");
		System.out.println("RCM Información");
		System.out.println("--------------------------------------------------");
		System.out.println("RCM Folio id:"+rcmEntity.getRcmFolio());
		System.out.println("Fecha Recepción:"+rcmEntity.getRcmFechaRecepcion());
		System.out.println("Agencia:"+rcmEntity.getAgencia1().getAgeNombre());
		System.out.println("Observación:"+rcmEntity.getRcmObserv());
	
		//Seteamos algunos parámetros del VO
		RcmVO rcm = getRcmEntityToVO(rcmEntity);
		
		return rcm;
		
	}

	private RcmVO getRcmEntityToVO(Rcm rcmEntity){
		
		RcmVO rcmVO = null;
		
		try{
			
			if(rcmEntity!=null){
				
				rcmVO = new RcmVO();
				
				RecepcionCobranzaMedicaVO recepcion = new RecepcionCobranzaMedicaVO();
				recepcion.setFolio(rcmEntity.getRcmFolio().intValue());
				AgenciaVO agenciaRecep = new AgenciaVO();
				agenciaRecep.setId(rcmEntity.getAgeCodRecep().intValue());
				recepcion.setAgenciaRecepcion(agenciaRecep);
				
				// ... seguir con los get y set
				
				rcmVO.setRcm(recepcion);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return rcmVO;
	}

}
