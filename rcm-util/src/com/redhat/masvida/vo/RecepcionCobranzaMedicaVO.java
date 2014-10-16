package com.redhat.masvida.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class RecepcionCobranzaMedicaVO implements Serializable {

	private static final long serialVersionUID = -3450306769242381268L;

	@Min(value=1)
	@Max(value=500000)
	private Integer folio;
	private Date fechaRecepcion;
	private Date fechaRegistro;
	private AgenciaVO agenciaRecepcion;
	@Size(min=10, max=500)
	private String observacion;

	
	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public AgenciaVO getAgenciaRecepcion() {
		return agenciaRecepcion;
	}

	public void setAgenciaRecepcion(AgenciaVO agenciaRecepcion) {
		this.agenciaRecepcion = agenciaRecepcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
