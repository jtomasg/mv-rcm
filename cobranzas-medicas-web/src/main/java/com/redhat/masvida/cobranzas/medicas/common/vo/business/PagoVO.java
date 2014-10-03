package com.redhat.masvida.cobranzas.medicas.common.vo.business;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PagoVO implements Serializable {

	private static final long serialVersionUID = 589602625211803874L;
	private CobradorVO cobrador;
	private TipoPagoVO tipoPago;
	private Date fechaPago;
	private AgenciaVO agenciaPago;
	private String convenioPago;

	public PagoVO() {
		this.cobrador = new CobradorVO();
		this.tipoPago = new TipoPagoVO();
		this.fechaPago = new Date();
		this.agenciaPago = new AgenciaVO();
		this.convenioPago = "";
	}
/****************** cambio ***********************/
	public CobradorVO getCobrador() {
		return cobrador;
	}

	public void setCobrador(CobradorVO cobrador) {
		this.cobrador = cobrador;
	}

	public TipoPagoVO getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TipoPagoVO tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public AgenciaVO getAgenciaPago() {
		return agenciaPago;
	}

	public void setAgenciaPago(AgenciaVO agenciaPago) {
		this.agenciaPago = agenciaPago;
	}

	public String getConvenioPago() {
		return convenioPago;
	}

	public void setConvenioPago(String convenioPago) {
		this.convenioPago = convenioPago;
	}

}
