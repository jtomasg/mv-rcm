package com.redhat.masvida.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.redhat.masvida.entities.Rcm;
import com.redhat.masvida.vo.RcmVO;

@Stateless
public class RcmDAO implements RcmDAOLocal {

	private Rcm r;

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
		List<Rcm> rcms = query.getResultList();
		if (rcms != null && rcms.size() == 1) {
			this.r = rcms.get(0);
		}

		//Veamos que tiene la Entity correspondiente
		System.out.println("--------------------------------------------------");
		System.out.println("RCM Información");
		System.out.println("--------------------------------------------------");
		System.out.println("RCM Folio id:"+this.r.getRcmFolio());
		System.out.println("Fecha Recepción:"+this.r.getRcmFechaRecepcion());
		System.out.println("Agencia:"+this.r.getAgencia1().getAgeNombre());
		System.out.println("Observación:"+this.r.getRcmObserv());
	
		//Seteamos algunos parámetros del VO
		RcmVO rcm = new RcmVO();
		rcm.setRcmFolio(r.getRcmFolio());
		rcm.setRcmObserv(r.getRcmObserv());
		rcm.setRcmFechaRecepcion(r.getRcmFechaRecepcion());
		rcm.setRcmFechaPago(r.getRcmFechaPago());
		rcm.setRcmNombrede(r.getRcmNombrede());
		
		return rcm;
		
	}

	public Rcm getR() {
		return r;
	}

	public void setR(Rcm r) {
		this.r = r;
	}

}
