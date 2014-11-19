package com.redhat.masvida.cobranzas.medicas.web.rest.client;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.masvida.cobranzas.medicas.common.exception.AgenciaNoEncontradaException;
import com.redhat.masvida.cobranzas.medicas.common.exception.FolioOrdeAtencionNoEncontradoException;
import com.redhat.masvida.cobranzas.medicas.common.exception.TipoPagoNoEncontradaException;
import com.redhat.masvida.cobranzas.medicas.web.util.RestClientCallUtil;
import com.redhat.masvida.vo.AgenciaVO;
import com.redhat.masvida.vo.OaVO;
import com.redhat.masvida.vo.OrdenAtencionVO;
import com.redhat.masvida.vo.PagoVO;
import com.redhat.masvida.vo.PersonaVO;
import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.RecepcionCobranzaMedicaVO;
import com.redhat.masvida.vo.TipoPagoVO;

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
		String nombre = null;
		try {
			LOG.debug("Consultando a los metodos REST por la id={} de Agencia",
					id);
			String endpointURL = "http://localhost:8080/rest/rcm/agencia/" + id;
			String json = restClientCallUtil.callJsonRemoteRest(endpointURL);
			System.out.println("JSON 1: " + json);
			nombre = json;
		} catch (Exception e) { // TODO: definir errores de negocio
			LOG.debug("Ocurrio un error al buscar una agencia con la ID {}", id);
			throw new AgenciaNoEncontradaException();
		}
		return nombre;
	}

	public String getNombreRutCobrador(String rut) {
		return db.getNombreRutCobrador(rut);
	}

	// CDI
	public void setDb(FuseRestDummyData db) {
		this.db = db;
	}

	public List<AgenciaVO> findAllAgencias() {

		AgenciaVO[] arrAgencias = null;
		List<AgenciaVO> lsAgencias = new ArrayList<AgenciaVO>();

		try {

			String endpointURL = "http://localhost:8080/rest/rcm/agencias";
			String json = restClientCallUtil.callJsonRemoteRest(endpointURL);

			ObjectMapper mapper = new ObjectMapper();
			arrAgencias = mapper.readValue(json, AgenciaVO[].class);

			for (AgenciaVO a : arrAgencias) {
				lsAgencias.add(a);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lsAgencias;
	}

	public TipoPagoVO getTipoPagoDefecto() {
		return db.getTipoPagoDefecto();
	}

	public List<TipoPagoVO> findAllTipoPagoValido() {
		TipoPagoVO[] arrTipoPago = null;
		List<TipoPagoVO> lsTiposPago = new ArrayList<TipoPagoVO>();

		try {

			String endpointURL = "http://localhost:8080/rest/rcm/tipospago";
			String json = restClientCallUtil.callJsonRemoteRest(endpointURL);

			ObjectMapper mapper = new ObjectMapper();
			arrTipoPago = mapper.readValue(json, TipoPagoVO[].class);

			for (TipoPagoVO tp : arrTipoPago) {
				lsTiposPago.add(tp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lsTiposPago;
	}

	public OrdenAtencionVO loadOrdenAtencion(Integer folioOA)
			throws FolioOrdeAtencionNoEncontradoException {
		OrdenAtencionVO oaVO = null;
		try {

			String endpointURL = "http://localhost:8080/rest/rcm/oa/" + folioOA;
			String json = restClientCallUtil.callJsonRemoteRest(endpointURL);

			System.out.println("JSON 1: " + json);

			ObjectMapper mapper = new ObjectMapper();
			OaVO vo = new OaVO();
			vo = mapper.readValue(json, OaVO.class);

			oaVO = new OrdenAtencionVO();
			
			// Mappings entre VO's
			oaVO.setBonificacion(vo.getBonificacion().intValue());
			oaVO.setCopago(vo.getCopago().doubleValue());
			oaVO.setEstado(vo.getEstadoOa().getEoaDescripcion());
			oaVO.setFechaEmision(vo.getOdaFechaemi());
			oaVO.setFolioOA(vo.getOdaFolio().intValue());

			PersonaVO pVO = new PersonaVO();
			pVO.setNombre(vo.getCotizante().getCotNombres() + " "
					+ vo.getCotizante().getCotPaterno() + " "
					+ vo.getCotizante().getCotMaterno());
			pVO.setRut(Integer.parseInt(vo.getCotizante().getCotRut()));
			//pVO.setDv(vo.getCotizante().getCotDv().charAt(0));
			oaVO.setTitular(pVO);
			oaVO.setValor(vo.getValor().doubleValue());

		} catch (Exception e) {
			LOG.info("No existe la OA");
			e.printStackTrace();
		}

		return oaVO;

	}

	public void guardarRcm(RecepcionCobranzaMedicaVO rcm, PagoVO pago,
			List<OrdenAtencionVO> ordenes) {

		try {

			RcmVO rcmVO = new RcmVO();
			rcmVO.setOrdenes(ordenes);
			rcmVO.setPago(pago);
			rcmVO.setRcm(rcm);

			String endpointURL = "http://localhost:8080/rest/rcm/guardar";
			restClientCallUtil.callJsonRemoteRest(endpointURL, rcmVO);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public RcmVO findFolio(Integer folio) {
		RcmVO rcmVO = null;
		try {

			String endpointURL = "http://localhost:8080/rest/rcm/buscar/"
					+ folio;
			String json = restClientCallUtil.callJsonRemoteRest(endpointURL);

			System.out.println("JSON 1: " + json);

			ObjectMapper mapper = new ObjectMapper();
			rcmVO = mapper.readValue(json, RcmVO.class);

			LOG.debug("Desde RcmVO[folio]: " + rcmVO.getRcm().getFolio());

		} catch (Exception e) {
			LOG.info("No existe la RCM");
			// e.printStackTrace();
		}

		return rcmVO;
	}

	public void deleteRcm(RecepcionCobranzaMedicaVO rcm, PagoVO pago,
			List<OrdenAtencionVO> ordenes) {
		db.deleteRcm(rcm);
	}

	public String getNombreTipoPago(Integer id)
			throws TipoPagoNoEncontradaException {
		String nombre = null;
		try {

			String endpointURL = "http://localhost:8080/rest/rcm/tipopago/"
					+ id;
			String json = restClientCallUtil.callJsonRemoteRest(endpointURL);

			System.out.println("JSON 1: " + json);
			nombre = json;

		} catch (Exception e) {
			LOG.info("No existe un tipo de pago con la ID especificada.");
			throw new TipoPagoNoEncontradaException();
		}
		return nombre;
	}

}
