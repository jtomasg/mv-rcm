package com.redhat.masvida.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class TipoPagoDocVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal tpdCodigo;
	private String tpdDescripcion;
	private String tpdVigencia;
	private List<RcmVO> rcms;

	public TipoPagoDocVO() {
	}

	public BigDecimal getTpdCodigo() {
		return this.tpdCodigo;
	}

	public void setTpdCodigo(BigDecimal tpdCodigo) {
		this.tpdCodigo = tpdCodigo;
	}

	public String getTpdDescripcion() {
		return this.tpdDescripcion;
	}

	public void setTpdDescripcion(String tpdDescripcion) {
		this.tpdDescripcion = tpdDescripcion;
	}

	public String getTpdVigencia() {
		return this.tpdVigencia;
	}

	public void setTpdVigencia(String tpdVigencia) {
		this.tpdVigencia = tpdVigencia;
	}

	public List<RcmVO> getRcms() {
		return this.rcms;
	}

	public void setRcms(List<RcmVO> rcms) {
		this.rcms = rcms;
	}

	public RcmVO addRcm(RcmVO rcm) {
		getRcms().add(rcm);
		rcm.setTipoPagoDoc(this);

		return rcm;
	}

	public RcmVO removeRcm(RcmVO rcm) {
		getRcms().remove(rcm);
		rcm.setTipoPagoDoc(null);

		return rcm;
	}

}