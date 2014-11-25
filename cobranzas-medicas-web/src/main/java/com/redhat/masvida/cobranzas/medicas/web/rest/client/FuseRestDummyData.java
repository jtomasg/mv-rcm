package com.redhat.masvida.cobranzas.medicas.web.rest.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.masvida.cobranzas.medicas.common.exception.FolioOrdeAtencionNoEncontradoException;
import com.redhat.masvida.cobranzas.medicas.common.util.RutDigitoVerificadorUtil;
import com.redhat.masvida.vo.AgenciaVO;
import com.redhat.masvida.vo.OrdenAtencionVO;
import com.redhat.masvida.vo.PagoVO;
import com.redhat.masvida.vo.PersonaVO;
import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.RecepcionCobranzaMedicaVO;
import com.redhat.masvida.vo.TipoPagoVO;

@ManagedBean
@Singleton
class FuseRestDummyData {

	Logger LOG = LoggerFactory.getLogger(FuseRestDummyData.class);
	private HashMap<Integer, AgenciaVO> dbAgencias;
	private HashMap<Integer, PersonaVO> dbPersonas;
	private HashMap<Integer, TipoPagoVO> dbTipoPagos;
	private HashMap<Integer, RcmVO> dbRcm;

	@PostConstruct
	public void init() {
		LOG.info("Inicio creacion db dummy");
		//buildAgencias();
		//buildPersonas();
		//buildTipoPago();
		//buildRcm();
		LOG.info("Fin creacion db dummy");
	}

	private void buildRcm() {
		this.dbRcm = new HashMap<Integer, RcmVO>();
	}

	public String getNombreAgencia(Integer id) {
		return this.dbAgencias.get(id).getDescripcion();
	}

	// METODOS UTILES PARA CREACION DE DATOS DE PRUEBA
	private void buildPersonas() {
		this.dbPersonas = new HashMap<Integer, PersonaVO>();
		for (int i = 1; i < 20; i++) {
			PersonaVO p = new PersonaVO();
			p.setNombre("Persona {" + p.getRut() + "}");
			p.setRut(i * 10000000);
			Integer dv = RutDigitoVerificadorUtil.getVerificationDigit(p
					.getRut().intValue() + "");
			p.setDv(dv <= 9 ? dv.toString().charAt(0) : dv <= 10 ? 'K' : '0');
			this.dbPersonas.put(p.getRut(), p);
		}

	}

	public String getNombreRutCobrador(String rut) {
		return "Cobrador={" + rut + "}";
	}

	public List<AgenciaVO> getAllAgencias() {
		return new ArrayList<AgenciaVO>(dbAgencias.values());
	}

	public TipoPagoVO getTipoPagoDefecto() {
		return this.dbTipoPagos.get(2);
	}

	public List<TipoPagoVO> getAllTipoPago() {
		return new ArrayList<TipoPagoVO>(dbTipoPagos.values());
	}

	public OrdenAtencionVO loadOrdenAtencion(Integer folioOA)
			throws FolioOrdeAtencionNoEncontradoException {
		// TODO simular orden de pago existentes y no existentes
		OrdenAtencionVO vo = new OrdenAtencionVO();
		if ((folioOA % 2) == 0) {
			Random random = new Random();
			// Los pares existen
			vo.setValor((double)random.nextInt(30000));
			vo.setBonificacion(random.nextInt(20000));
			vo.setCopago(random.nextDouble() * 1000);
			vo.setEstado("ESTADO"); // TODO determinar estados
			vo.setFechaEmision(new Date());
			vo.setFolioOA(folioOA);
			PersonaVO p = new PersonaVO();
			int rut = random.nextInt(999999999);
			p.setNombre("persona {" + rut + "}");
			p.setRut(rut);
			vo.setTitular(p);
		} else { // impar
			throw new FolioOrdeAtencionNoEncontradoException();
		}
		return vo;
	}

	public void guardarRcm(RecepcionCobranzaMedicaVO rcm, PagoVO pago,
			List<OrdenAtencionVO> ordenes) {
		this.dbRcm.put(rcm.getFolio(), new RcmVO(rcm, pago, ordenes));
	}
	
	public RcmVO findFolio(Integer folio){
		return this.dbRcm.get(folio);
	}

	public void deleteRcm(RecepcionCobranzaMedicaVO rcm) {
		this.dbRcm.remove(rcm.getFolio());
	}

	public String getNombreTipoPago(Integer id) {
		return this.dbTipoPagos.get(id).getNombre();
	}

}
