package com.redhat.masvida.vo;

import java.io.Serializable;
import java.util.List;



public class CotizanteVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cotRut;
	private String cotDv;
	private String cotMaterno;
	private String cotNombres;
	private String cotPaterno;
	private List<OaVO> oas;

	public CotizanteVO() {
	}

	public String getCotRut() {
		return this.cotRut;
	}

	public void setCotRut(String cotRut) {
		this.cotRut = cotRut;
	}

	public String getCotDv() {
		return this.cotDv;
	}

	public void setCotDv(String cotDv) {
		this.cotDv = cotDv;
	}

	public String getCotMaterno() {
		return this.cotMaterno;
	}

	public void setCotMaterno(String cotMaterno) {
		this.cotMaterno = cotMaterno;
	}

	public String getCotNombres() {
		return this.cotNombres;
	}

	public void setCotNombres(String cotNombres) {
		this.cotNombres = cotNombres;
	}

	public String getCotPaterno() {
		return this.cotPaterno;
	}

	public void setCotPaterno(String cotPaterno) {
		this.cotPaterno = cotPaterno;
	}

	public List<OaVO> getOas() {
		return this.oas;
	}

	public void setOas(List<OaVO> oas) {
		this.oas = oas;
	}

	public OaVO addOa(OaVO oa) {
		getOas().add(oa);
		oa.setCotizante(this);

		return oa;
	}

	public OaVO removeOa(OaVO oa) {
		getOas().remove(oa);
		oa.setCotizante(null);

		return oa;
	}

}