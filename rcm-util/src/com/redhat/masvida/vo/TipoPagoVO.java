package com.redhat.masvida.vo;

import java.io.Serializable;

public class TipoPagoVO implements Serializable {

	private static final long serialVersionUID = -1742787302956714544L;
	private Integer id;
	private String nombre;

	public TipoPagoVO() {
		
	}
	public TipoPagoVO(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
