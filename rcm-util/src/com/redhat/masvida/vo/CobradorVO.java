package com.redhat.masvida.vo;

import java.io.Serializable;

public class CobradorVO implements Serializable {
	private Integer rut;
	private String nombre;

	public Integer getRut() {
		return rut;
	}

	public void setRut(Integer rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
