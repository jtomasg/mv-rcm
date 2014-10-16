package com.redhat.masvida.cobranzas.medicas.web.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import org.richfaces.component.UIExtendedDataTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.masvida.cobranzas.medicas.common.exception.AgenciaNoEncontradaException;
import com.redhat.masvida.cobranzas.medicas.common.exception.TipoPagoNoEncontradaException;
import com.redhat.masvida.cobranzas.medicas.web.mbean.base.BaseManagedBean;
import com.redhat.masvida.cobranzas.medicas.web.rest.client.FuseRestClient;
import com.redhat.masvida.cobranzas.medicas.web.util.JsfUtil;
import com.redhat.masvida.cobranzas.medicas.web.util.RichFacesUtil;
import com.redhat.masvida.vo.AgenciaVO;
import com.redhat.masvida.vo.CobradorVO;
import com.redhat.masvida.vo.PagoVO;
import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.RecepcionCobranzaMedicaVO;
import com.redhat.masvida.vo.TipoPagoVO;

@ManagedBean
@ViewScoped
public class CobradorMBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = -6239437588285327644L;
	private static final Logger LOG = LoggerFactory
			.getLogger(CobradorMBean.class);
	@Inject
	private FuseRestClient client;

	// para richfaces extended data table
	private Collection<Object> agenciaSelection;
	private Collection<Object> tipoPagoSelection;

	private RecepcionCobranzaMedicaVO rcm;
	private PagoVO pago;
	private boolean nuevoFolio;
	private AgenciaVO preselectedAgencia;
	private TipoPagoVO preselectedTipoPago;
	private boolean found;
	

	@ManagedProperty("#{agenciaMBean}")
	private AgenciaMBean agenciaMBean;

	@ManagedProperty("#{ordenPagoMBean}")
	private OrdenPagoMBean ordenPagoMBean;

	@ManagedProperty("#{tipoPagoMBean}")
	private TipoPagoMBean tipoPagoMBean;

	@PostConstruct
	public void init() {

		this.rcm = new RecepcionCobranzaMedicaVO();
		this.rcm.setAgenciaRecepcion(new AgenciaVO());
		this.rcm.setFechaRecepcion(new Date());
		this.rcm.setFechaRegistro(new Date());

		this.pago = new PagoVO();
		this.pago.setCobrador(new CobradorVO());
		this.pago.setFechaPago(new Date());

		// cargamos el tipo de pago por defecto del cliente
		this.pago.setTipoPago(client.getTipoPagoDefecto());

		// this.ordenes = new ArrayList<OrdenAtencionVO>();
		nuevoFolio = true; // incialmente siempre es nuevo

		agenciaSelection = new ArrayList<Object>();

	}

	public void resolveAgencia(AjaxBehaviorEvent event) {
		String nombreAgencia;
		try {
			Integer id = this.rcm.getAgenciaRecepcion().getId();
			nombreAgencia = client.getNombreAgencia(id);
			this.rcm.getAgenciaRecepcion().setDescripcion(nombreAgencia);
			this.pago.setAgenciaPago(this.rcm.getAgenciaRecepcion());
		} catch (AgenciaNoEncontradaException e) {
			LOG.info("No encontre agencia!!");
			JsfUtil.addMessage(
					event.getComponent().getClientId(),
					"Agencia inválida",
					" El código de la agencia no se encuentra en los "
							+ "registros presione <F9> para ver la lista de valores",
					FacesMessage.SEVERITY_WARN);
			RichFacesUtil.changeFocusTo("btnCerraPopupMensajesId");
		}
	}

	public void resolveTipoPago(AjaxBehaviorEvent event) {
		String nombrePago;
		try {
			Integer id = this.pago.getTipoPago().getId();
			nombrePago = client.getNombreTipoPago(id);
			this.pago.getTipoPago().setNombre(nombrePago);
		} catch (TipoPagoNoEncontradaException e) {
			LOG.info("No encontre Pago!!");
			JsfUtil.addMessage(
					event.getComponent().getClientId(),
					"Pago no válido",
					" El código del pago ingresado no se encuentra en los "
							+ "registros presione <F8> para ver la lista de valores",
					FacesMessage.SEVERITY_WARN);
			RichFacesUtil.changeFocusTo("btnCerraPopupMensajesId");
		}
	}

	public void prepareNuevo() {
		// limpiamos todo
		init();
		//Se reestablecen los valores correspondientes de las Ordenes de Atención Pagadas
		ordenPagoMBean.setTotalvalor(null);
		ordenPagoMBean.setValorCopago(null);
		ordenPagoMBean.setTotalBonificacion(null);
		ordenPagoMBean.init();
	}
	
	public void eliminar() {
		try {
			System.out.println("Eliminando el RCM con ID: "+this.rcm.getFolio());
			client.deleteRcm(this.rcm, pago, ordenPagoMBean.getOrdenes());
			prepareNuevo();
			
		} catch (Exception ex) {
			JsfUtil.addMessage(
					null,
					"Error inesperado",
					"Ha ocurrido un error al eliminar el registro, intente nuevamente más tarde",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public void resolveNombreCobrador(AjaxBehaviorEvent event) {
		String nombre = client.getNombreRutCobrador(this.pago.getCobrador()
				.getRut() + "");
		this.pago.getCobrador().setNombre(nombre);
	}

	public void selectAgencia(AjaxBehaviorEvent event) {
		UIExtendedDataTable dataTable = (UIExtendedDataTable) event
				.getComponent();
		Object originalKey = dataTable.getRowKey();
		for (Object selectionKey : agenciaSelection) {
			dataTable.setRowKey(selectionKey);
			if (dataTable.isRowAvailable()) {
				preselectedAgencia = (AgenciaVO) dataTable.getRowData();
				// AgenciaVO ag = (AgenciaVO) dataTable.getRowData();
				// this.rcm.setAgenciaRecepcion(ag);
				// this.pago.setAgenciaPago(ag);
			}
		}
		dataTable.setRowKey(originalKey);
	}
	
	public void selectTipoPago(AjaxBehaviorEvent event) {
		UIExtendedDataTable dataTable = (UIExtendedDataTable) event
				.getComponent();
		Object originalKey = dataTable.getRowKey();
		for (Object selectionKey : tipoPagoSelection) {
			dataTable.setRowKey(selectionKey);
			if (dataTable.isRowAvailable()) {
				preselectedTipoPago = (TipoPagoVO) dataTable.getRowData();
			}
		}
		dataTable.setRowKey(originalKey);
	}
	
	public void confirmarTipoPagoSelect(AjaxBehaviorEvent event) {
		TipoPagoVO tp = preselectedTipoPago;
		this.pago.setTipoPago(tp);
	}


	public void confirmarAgenciaSelect(AjaxBehaviorEvent event) {
		AgenciaVO ag = preselectedAgencia;
		this.rcm.setAgenciaRecepcion(ag);
		this.pago.setAgenciaPago(ag);
	}
	
	public void resolveRcm() {
		System.out.println("Buscando el RCM con ID para editar: "+this.rcm.getFolio());
		RcmVO rcmVO = client.findFolio(this.rcm.getFolio());
		try {
			this.rcm = rcmVO.getRcm();
			this.pago = rcmVO.getPago();
			this.ordenPagoMBean.setOrdenes(rcmVO.getOrdenes());
			
			//RW: Se actualizan los valores de totalpago, bonif y copago al editar un RCM existente.
			this.ordenPagoMBean.actualizarDatosResumen();
		} catch (Exception ex) {// Si cae aca es por que el folio es nuevo
			Integer oldFOlio = this.rcm.getFolio();
			init();
			this.rcm.setFolio(oldFOlio);
		}
	}
	
	public void findRcm(AjaxBehaviorEvent event) {
		System.out.println("Buscando el RCM con ID: "+this.rcm.getFolio());
		this.found = false;
		RcmVO rcmVO = client.findFolio(this.rcm.getFolio());
		try {
			this.rcm = rcmVO.getRcm();
			if(this.rcm!=null){
			this.found = true;
			}
		} catch (Exception ex) {// Si cae aca es por que el folio es nuevo
		}
	}

	public void guardarRcm() {
		//Verificamos folio RCM para ver que esta pasando
		System.out.println("Guardando el RCM con ID: "+this.rcm.getFolio());
		if(this.rcm.getFolio()!=null){
		client.guardarRcm(this.rcm, this.pago, ordenPagoMBean.getOrdenes());

		// TODO: metodo cliente para guardar todo
		JsfUtil.addMessage(null, "Operación exitosa:",
				" Los datos se han registrado exitosamente",
				FacesMessage.SEVERITY_INFO);
		this.nuevoFolio = false;
		}
		else{
			//TODO Desglosar mensajes de campos faltantes
			JsfUtil.addMessage(null, "Operación Fallida:",
					" Se han detectado campo(s) no completados. Revisar RCM.",
					FacesMessage.SEVERITY_ERROR);
			//this.nuevoFolio = false;	
		}
	}
	
	public void setClient(FuseRestClient client) {
		this.client = client;
	}

	public void setRcm(RecepcionCobranzaMedicaVO rcm) {
		this.rcm = rcm;
	}

	public RecepcionCobranzaMedicaVO getRcm() {
		return rcm;
	}

	public PagoVO getPago() {
		return pago;
	}

	public void setPago(PagoVO pago) {
		this.pago = pago;
	}

	public boolean isNuevoFolio() {
		return nuevoFolio;
	}

	public void setNuevoFolio(boolean nuevoFolio) {
		this.nuevoFolio = nuevoFolio;
	}

	public void setAgenciaMBean(AgenciaMBean agenciaMBean) {
		this.agenciaMBean = agenciaMBean;
	}

	public void setOrdenPagoMBean(OrdenPagoMBean ordenPagoMBean) {
		this.ordenPagoMBean = ordenPagoMBean;
	}

	public void setTipoPagoMBean(TipoPagoMBean tipoPagoMBean) {
		this.tipoPagoMBean = tipoPagoMBean;
	}

	public Collection<Object> getAgenciaSelection() {
		return agenciaSelection;
	}

	public void setAgenciaSelection(Collection<Object> agenciaSelection) {
		this.agenciaSelection = agenciaSelection;
	}

	public Collection<Object> getTipoPagoSelection() {
		return tipoPagoSelection;
	}

	public void setTipoPagoSelection(Collection<Object> tipoPagoSelection) {
		this.tipoPagoSelection = tipoPagoSelection;
	}

	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}

}