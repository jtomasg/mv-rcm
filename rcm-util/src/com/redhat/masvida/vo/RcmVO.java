package com.redhat.masvida.vo;

import java.io.Serializable;
import java.util.List;

public class RcmVO implements Serializable {

	private static final long serialVersionUID = -3450306769242381268L;
	private RecepcionCobranzaMedicaVO rcm;
	private PagoVO pago;
	private List<OrdenAtencionVO> ordenes;

	public RcmVO() {
	}

	public RcmVO(RecepcionCobranzaMedicaVO rcm, PagoVO pago,
			List<OrdenAtencionVO> ordenes) {
		super();
		this.rcm = rcm;
		this.pago = pago;
		this.ordenes = ordenes;
	}

	public RecepcionCobranzaMedicaVO getRcm() {
		return rcm;
	}

	public void setRcm(RecepcionCobranzaMedicaVO rcm) {
		this.rcm = rcm;
	}

	public PagoVO getPago() {
		return pago;
	}

	public void setPago(PagoVO pago) {
		this.pago = pago;
	}

	public List<OrdenAtencionVO> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(List<OrdenAtencionVO> ordenes) {
		this.ordenes = ordenes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
