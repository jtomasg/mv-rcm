package com.redhat.masvida.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class AgenciaVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal ageCodigo;
	private String ageDisponible;
	private String ageNombre;

	private List<RcmVO> rcms1;
	private List<RcmVO> rcms2;

	public AgenciaVO() {
	}

	public BigDecimal getAgeCodigo() {
		return this.ageCodigo;
	}

	public void setAgeCodigo(BigDecimal ageCodigo) {
		this.ageCodigo = ageCodigo;
	}

	public String getAgeDisponible() {
		return this.ageDisponible;
	}

	public void setAgeDisponible(String ageDisponible) {
		this.ageDisponible = ageDisponible;
	}

	public String getAgeNombre() {
		return this.ageNombre;
	}

	public void setAgeNombre(String ageNombre) {
		this.ageNombre = ageNombre;
	}

	public List<RcmVO> getRcms1() {
		return this.rcms1;
	}

	public void setRcms1(List<RcmVO> rcms1) {
		this.rcms1 = rcms1;
	}

	public RcmVO addRcms1(RcmVO rcms1) {
		getRcms1().add(rcms1);
		rcms1.setAgencia1(this);

		return rcms1;
	}

	public RcmVO removeRcms1(RcmVO rcms1) {
		getRcms1().remove(rcms1);
		rcms1.setAgencia1(null);

		return rcms1;
	}

	public List<RcmVO> getRcms2() {
		return this.rcms2;
	}

	public void setRcms2(List<RcmVO> rcms2) {
		this.rcms2 = rcms2;
	}

	public RcmVO addRcms2(RcmVO rcms2) {
		getRcms2().add(rcms2);
		rcms2.setAgencia2(this);

		return rcms2;
	}

	public RcmVO removeRcms2(RcmVO rcms2) {
		getRcms2().remove(rcms2);
		rcms2.setAgencia2(null);

		return rcms2;
	}

}