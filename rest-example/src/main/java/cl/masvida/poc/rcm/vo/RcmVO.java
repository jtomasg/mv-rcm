package cl.masvida.poc.rcm.vo;

import java.util.Date;

public class RcmVO {

	public String folio;
	public Date fechaRecepcion;
	public Date fechaRegistro;
	public String codigoAgenciaRecep;
	public String nombreAgenciaRecep;
	public String observaciones;
	
	public RcmVO(String folio, Date fechaRecepcion, Date fechaRegistro) {
		super();
		this.folio = folio;
		this.fechaRecepcion = fechaRecepcion;
		this.fechaRegistro = fechaRegistro;
	}
	
	public RcmVO() {
		super();
	}
	
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
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
	public String getCodigoAgenciaRecep() {
		return codigoAgenciaRecep;
	}
	public void setCodigoAgenciaRecep(String codigoAgenciaRecep) {
		this.codigoAgenciaRecep = codigoAgenciaRecep;
	}
	public String getNombreAgenciaRecep() {
		return nombreAgenciaRecep;
	}
	public void setNombreAgenciaRecep(String nombreAgenciaRecep) {
		this.nombreAgenciaRecep = nombreAgenciaRecep;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
}
