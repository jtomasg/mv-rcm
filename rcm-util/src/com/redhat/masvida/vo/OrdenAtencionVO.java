package com.redhat.masvida.vo;

import java.io.Serializable;
import java.util.Date;

public class OrdenAtencionVO implements Serializable {

	private static final long serialVersionUID = 5353945223808324870L;
	private Integer folioOA;
	private Date fechaEmision;
	private Double valor;
	private Double copago;
	private Integer bonificacion;
	private String estado;
	private PersonaVO titular;

	public Integer getFolioOA() {
		return folioOA;
	}

	public void setFolioOA(Integer folioOA) {
		this.folioOA = folioOA;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getCopago() {
		return copago;
	}

	public void setCopago(Double copago) {
		this.copago = copago;
	}

	public Integer getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(Integer bonificacion) {
		this.bonificacion = bonificacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public PersonaVO getTitular() {
		return titular;
	}

	public void setTitular(PersonaVO titular) {
		this.titular = titular;
	}

}
