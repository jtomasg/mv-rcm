package com.redhat.masvida.vo;

import java.io.Serializable;

public class PersonaVO implements Serializable {

	private static final long serialVersionUID = -1742787302956714544L;
	private Integer rut;
	private char dv;
	private String nombre;

	public Integer getRut() {
		return rut;
	}

	public void setRut(Integer rut) {
		this.rut = rut;
	}

	public char getDv() {
		return dv;
	}

	public void setDv(char dv) {
		this.dv = dv;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
