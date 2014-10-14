package com.redhat.masvida.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class OaVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal odaFolio;
	private BigDecimal bonificacion;
	private BigDecimal copago;
	private BigDecimal eoaCodigo;
	private Date odaFechaemi;
	private BigDecimal rcmFolio;
	private String titRut;
	private BigDecimal valor;
	private CotizanteVO cotizante;
	private EstadoOaVO estadoOa;
	
	private RcmVO rcm;

	public OaVO() {
	}

	public BigDecimal getOdaFolio() {
		return this.odaFolio;
	}

	public void setOdaFolio(BigDecimal odaFolio) {
		this.odaFolio = odaFolio;
	}

	public BigDecimal getBonificacion() {
		return this.bonificacion;
	}

	public void setBonificacion(BigDecimal bonificacion) {
		this.bonificacion = bonificacion;
	}

	public BigDecimal getCopago() {
		return this.copago;
	}

	public void setCopago(BigDecimal copago) {
		this.copago = copago;
	}

	public BigDecimal getEoaCodigo() {
		return this.eoaCodigo;
	}

	public void setEoaCodigo(BigDecimal eoaCodigo) {
		this.eoaCodigo = eoaCodigo;
	}

	public Date getOdaFechaemi() {
		return this.odaFechaemi;
	}

	public void setOdaFechaemi(Date odaFechaemi) {
		this.odaFechaemi = odaFechaemi;
	}

	public BigDecimal getRcmFolio() {
		return this.rcmFolio;
	}

	public void setRcmFolio(BigDecimal rcmFolio) {
		this.rcmFolio = rcmFolio;
	}

	public String getTitRut() {
		return this.titRut;
	}

	public void setTitRut(String titRut) {
		this.titRut = titRut;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public CotizanteVO getCotizante() {
		return this.cotizante;
	}

	public void setCotizante(CotizanteVO cotizante) {
		this.cotizante = cotizante;
	}

	public EstadoOaVO getEstadoOa() {
		return this.estadoOa;
	}

	public void setEstadoOa(EstadoOaVO estadoOa) {
		this.estadoOa = estadoOa;
	}

	public RcmVO getRcm() {
		return this.rcm;
	}

	public void setRcm(RcmVO rcm) {
		this.rcm = rcm;
	}

}