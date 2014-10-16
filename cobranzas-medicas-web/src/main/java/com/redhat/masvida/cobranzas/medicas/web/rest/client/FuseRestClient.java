package com.redhat.masvida.cobranzas.medicas.web.rest.client;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.masvida.cobranzas.medicas.common.exception.AgenciaNoEncontradaException;
import com.redhat.masvida.cobranzas.medicas.common.exception.FolioOrdeAtencionNoEncontradoException;
import com.redhat.masvida.cobranzas.medicas.common.exception.TipoPagoNoEncontradaException;
import com.redhat.masvida.cobranzas.medicas.common.vo.business.AgenciaVO;
import com.redhat.masvida.cobranzas.medicas.common.vo.business.OrdenAtencionVO;
import com.redhat.masvida.cobranzas.medicas.common.vo.business.PagoVO;
import com.redhat.masvida.cobranzas.medicas.common.vo.business.RcmVO;
import com.redhat.masvida.cobranzas.medicas.common.vo.business.RecepcionCobranzaMedicaVO;
import com.redhat.masvida.cobranzas.medicas.common.vo.business.TipoPagoVO;
import com.redhat.masvida.cobranzas.medicas.web.util.RestClientCallUtil;

@ManagedBean
@RequestScoped
public class FuseRestClient {
	private static final Logger LOG = LoggerFactory
			.getLogger(FuseRestClient.class);

	@Inject
	private FuseRestDummyData db;
	
	@Inject
	private RestClientCallUtil restClientCallUtil;

	public String getNombreAgencia(Integer id)
			throws AgenciaNoEncontradaException {
		try {
			LOG.debug("Consultando a los metodos REST por la id={} de Agencia",
					id);
			return db.getNombreAgencia(id);
		} catch (NullPointerException e) { // TODO: definir errores de negocio
			LOG.debug("Ocurrio un error al buscar una agencia con la ID {}", id);
			throw new AgenciaNoEncontradaException();
		}

	}

	public String getNombreRutCobrador(String rut) {
		return db.getNombreRutCobrador(rut);
	}

	// CDI
	public void setDb(FuseRestDummyData db) {
		this.db = db;
	}

	public List<AgenciaVO> findAllAgencias() {
		// TODO definir la paginacion (lazy load)
		return db.getAllAgencias();
	}

	public TipoPagoVO getTipoPagoDefecto() {
		return db.getTipoPagoDefecto();
	}

	public List<TipoPagoVO> findAllTipoPagoValido() {
		return db.getAllTipoPago();
	}

	public OrdenAtencionVO loadOrdenAtencion(Integer folioOA)
			throws FolioOrdeAtencionNoEncontradoException {
		return db.loadOrdenAtencion(folioOA);

	}

	public void guardarRcm(RecepcionCobranzaMedicaVO rcm, PagoVO pago,
			List<OrdenAtencionVO> ordenes) {
		db.guardarRcm(rcm, pago, ordenes);
	}

	public RcmVO findFolio(Integer folio) {
		
		try{
			
			String endpointURL = "http://localhost:8080/miRest/rcm/buscar/"+folio+"&01-01-2014&31-12-2015";
			String json = restClientCallUtil.callJsonRemoteRest(endpointURL);
			
			System.out.println("JSON 1: "+json);
			
//			ObjectMapper mapper = new ObjectMapper();
//			RcmVO rcmVO = mapper.readValue(json, RcmVO.class);
			
			endpointURL = "http://localhost:8080/miRest/rcm/buscar/"+folio+"&01-01-2014&31-12-2015";
			json = restClientCallUtil.callJsonRemoteRest(endpointURL, new RcmVO());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return db.findFolio(folio);
	}

	public void deleteRcm(RecepcionCobranzaMedicaVO rcm, PagoVO pago,
			List<OrdenAtencionVO> ordenes) {
		db.deleteRcm(rcm);
	}

	public String getNombreTipoPago(Integer id)
			throws TipoPagoNoEncontradaException {
		try {
			return db.getNombreTipoPago(id);
		} catch (NullPointerException e) { // TODO: definir errores de negocio
			throw new TipoPagoNoEncontradaException();
		}

	}
}
