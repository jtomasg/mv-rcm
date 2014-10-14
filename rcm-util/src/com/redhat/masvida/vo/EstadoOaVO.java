package com.redhat.masvida.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class EstadoOaVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal eoaCodigo;
	private String eoaDescripcion;
	private String eoaVigencia;
	private List<OaVO> oas;

	public EstadoOaVO() {
	}

	public BigDecimal getEoaCodigo() {
		return this.eoaCodigo;
	}

	public void setEoaCodigo(BigDecimal eoaCodigo) {
		this.eoaCodigo = eoaCodigo;
	}

	public String getEoaDescripcion() {
		return this.eoaDescripcion;
	}

	public void setEoaDescripcion(String eoaDescripcion) {
		this.eoaDescripcion = eoaDescripcion;
	}

	public String getEoaVigencia() {
		return this.eoaVigencia;
	}

	public void setEoaVigencia(String eoaVigencia) {
		this.eoaVigencia = eoaVigencia;
	}

	public List<OaVO> getOas() {
		return this.oas;
	}

	public void setOas(List<OaVO> oas) {
		this.oas = oas;
	}

	public OaVO addOa(OaVO oa) {
		getOas().add(oa);
		oa.setEstadoOa(this);

		return oa;
	}

	public OaVO removeOa(OaVO oa) {
		getOas().remove(oa);
		oa.setEstadoOa(null);

		return oa;
	}

}