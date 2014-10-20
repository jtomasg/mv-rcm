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
	public RcmVO buscarRcm(BigDecimal folio) {
		
		//1. Se ejecuta la consulta contra la BD...
			//Query query = em.createNamedQuery("Rcm.findByFolio");
			//Query query2 = em.createQuery("SELECT rcm FROM Rcm rcm WHERE rcm.rcmFolio = :folio");
			//Query query3 = em.createNativeQuery("{call ...}"); // Ejemplo llamada a SP en Oracle
			Rcm rcmEntity = em.find(Rcm.class, folio);
			
	//		if (lsRcms != null && lsRcms.size() == 1) {
	//			rcmEntity = lsRcms.get(0);
	//		}

		RcmVO rcmVO = null;
		
		// Si se encontro un registro en la BD...
		if(rcmEntity!=null){
			
			//2. Informacion de a nivel de log para visualizar los datos obtenidos
				System.out.println("--------------------------------------------------");
				System.out.println("RCM Información");
				System.out.println("--------------------------------------------------");
				System.out.println("RCM Folio id:"+rcmEntity.getRcmFolio());
				System.out.println("Fecha Recepción:"+rcmEntity.getRcmFechaRecepcion());
				System.out.println("Agencia:"+rcmEntity.getAgencia1().getAgeNombre());
				System.out.println("Observación:"+rcmEntity.getRcmObserv());
		
			//3. Se obtiene el VO desde el entity algunos parámetros del VO
				rcmVO = getRcmEntityToVO(rcmEntity);
			
		}
		
		return rcmVO;
		
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
