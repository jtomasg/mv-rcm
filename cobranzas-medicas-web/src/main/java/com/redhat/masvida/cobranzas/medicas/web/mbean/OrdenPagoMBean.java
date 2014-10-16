package com.redhat.masvida.cobranzas.medicas.web.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.masvida.cobranzas.medicas.common.exception.FolioOrdeAtencionNoEncontradoException;
import com.redhat.masvida.cobranzas.medicas.web.mbean.base.BaseManagedBean;
import com.redhat.masvida.cobranzas.medicas.web.rest.client.FuseRestClient;
import com.redhat.masvida.cobranzas.medicas.web.util.JsfUtil;
import com.redhat.masvida.vo.OrdenAtencionVO;
import com.redhat.masvida.vo.PersonaVO;

@ManagedBean
@ViewScoped
public class OrdenPagoMBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = -6239437538235327344L;
	private static final Logger LOG = LoggerFactory
			.getLogger(OrdenPagoMBean.class);
	@Inject
	private FuseRestClient client;

	private Double totalvalor;
	private Double valorCopago;
	private Integer totalBonificacion;
	private boolean errorFlag;

	private List<OrdenAtencionVO> ordenes;

	@PostConstruct
	public void init() {
		this.ordenes = new ArrayList<OrdenAtencionVO>();

		// Siempre mantenemos una fila limpia al final
		agregarNuevaOrdenAlFinal();

	}

	private void agregarNuevaOrdenAlFinal() {
		int last = this.ordenes.size() - 1;
		// es primero || no es primero y el ultimo es 0
		if (last == -1
				|| (last >= 0 && this.ordenes.get(last).getFolioOA() != 0)) {
			OrdenAtencionVO vo = new OrdenAtencionVO();
			vo.setValor(0d);
			vo.setBonificacion(0);
			vo.setCopago(0d);
			vo.setEstado("ESTADO"); // TODO determinar estados
			vo.setFechaEmision(new Date());
			vo.setFolioOA(0);
			PersonaVO p = new PersonaVO();
			p.setNombre("");
			p.setRut(0);
			vo.setTitular(p);
			this.ordenes.add(this.ordenes.size(), vo);
		}
	}

	public void eliminarOA(AjaxBehaviorEvent event) {
		// Verificamos la ROW ID correspondiente
		Integer rowIndex = (Integer) event.getComponent().getAttributes()
				.get("rowIndexId");
		LOG.info("A Eliminar Fila: " + rowIndex + " con Valor: "
				+ rowIndex.intValue());

		try {
			// Simularemos una excepción a partir de un simble DivideByZero
				Random r = new Random();
				int rnumber = r.nextInt(2);
				int result = 10 / rnumber;
			
			// Desvinculamos la OA del RCM
			this.ordenes.remove(rowIndex.intValue());
			actualizarDatosResumen();
			this.errorFlag = false;
			if (this.ordenes.size() <= 0) {
				agregarNuevaOrdenAlFinal();
			}
		} catch (Exception e) {
			LOG.info("Simulando excepción...");
			/*
			 * Fijamos desde aquí el valor de flagError para crear las
			 * condiciones necesarias para levantar el popup de error definido
			 * en el index. Esto, dado a que JsfUtil.addMessage no está
			 * funcionando desde acá...
			 */
			this.errorFlag = true;
		}
	}

	public void actualizarDatosResumen() {
		this.totalBonificacion = 0;
		this.totalvalor = 0d;
		this.valorCopago = 0d;
		for (OrdenAtencionVO oa : this.ordenes) {
			this.totalBonificacion += oa.getBonificacion() != null ? oa
					.getBonificacion() : 0;
			this.totalvalor += oa.getValor() != null ? oa.getValor() : 0;
			this.valorCopago += oa.getCopago() != null ? oa.getCopago() : 0;
		}
	}

	public void verificarFolioOA(AjaxBehaviorEvent event) {
		// String strRowIndex = (String) ;

		try {
			// Verificamos que sea integer
			Integer rowIndex = (Integer) event.getComponent().getAttributes()
					.get("rowIndexId");

			OrdenAtencionVO ordenVo = this.ordenes.get(rowIndex.intValue());

			// consultamos a los servicios
			OrdenAtencionVO found = client.loadOrdenAtencion(ordenVo
					.getFolioOA());
			this.ordenes.set(rowIndex, found);

			JsfUtil.addMessage(event.getComponent().getClientId(),
					"se ha cargado un folio OA");

			// finalmente , si encuentra algo arreglamos la lista para crear un
			// nuevo item
			// agregarNuevaOrdenAlFinal();
			actualizarDatosResumen();

		} catch (NumberFormatException nfe) {
			JsfUtil.addMessage(event.getComponent().getClientId(),
					"Folio inválido",
					"El folio debe estar compuesto solamente por números",
					FacesMessage.SEVERITY_ERROR);
		} catch (FolioOrdeAtencionNoEncontradoException foane) {
			// significa que se va a agregar uno
			// agregarNuevaOrdenAlFinal();
		}
	}

	/*
	 * Método para agregar Fila a la Tabla de Ordenes de Atención.
	 */
	public void addRowLastColumn() {
		LOG.info("Añadiendo nueva OA a la tabla...");
		agregarNuevaOrdenAlFinal();
		// actualizarDatosResumen();
	}

	public void setClient(FuseRestClient client) {
		this.client = client;
	}

	public Double getTotalvalor() {
		return totalvalor;
	}

	public void setTotalvalor(Double totalvalor) {
		this.totalvalor = totalvalor;
	}

	public Double getValorCopago() {
		return valorCopago;
	}

	public void setValorCopago(Double valorCopago) {
		this.valorCopago = valorCopago;
	}

	public Integer getTotalBonificacion() {
		return totalBonificacion;
	}

	public void setTotalBonificacion(Integer totalBonificacion) {
		this.totalBonificacion = totalBonificacion;
	}

	public List<OrdenAtencionVO> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(List<OrdenAtencionVO> ordenes) {
		this.ordenes = ordenes;
	}

	public boolean isErrorFlag() {
		return errorFlag;
	}

	public void setErrorFlag(boolean errorFlag) {
		this.errorFlag = errorFlag;
	}

}