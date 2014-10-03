package com.redhat.masvida.cobranzas.medicas.common.vo.business;

import java.io.Serializable;

public class AgenciaVO implements Serializable {

	private static final long serialVersionUID = 211475637527354243L;
	private Integer id;
	private String descripcion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
