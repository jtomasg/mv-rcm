package cl.masvida.poc.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the "ESTADO_OA" database table.
 * 
 */
@Entity
@Table(name="estado_oa")
@NamedQuery(name="EstadoOa.findAll", query="SELECT e FROM EstadoOa e")
public class EstadoOa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="eoa_codigo", unique=true, nullable=false, precision=2)
	private BigDecimal eoaCodigo;

	@Column(name="eoa_descripcion", length=250)
	private String eoaDescripcion;

	@Column(name="eoa_vigencia", nullable=false, length=1)
	private String eoaVigencia;

	//bi-directional many-to-one association to Oa
	@OneToMany(mappedBy="estadoOa")
	private List<Oa> oas;

	public EstadoOa() {
	}

	public BigDecimal getEoaCodigo() {
		return this.eoaCodigo;
	}

	public void setEoaCodigo(BigDecimal eoaCodigo) {
		this.eoaCodigo = eoaCodigo;
	}

	public String getEoaDescripcion() {
		return this.eoaDescripcion;
	}

	public void setEoaDescripcion(String eoaDescripcion) {
		this.eoaDescripcion = eoaDescripcion;
	}

	public String getEoaVigencia() {
		return this.eoaVigencia;
	}

	public void setEoaVigencia(String eoaVigencia) {
		this.eoaVigencia = eoaVigencia;
	}

	public List<Oa> getOas() {
		return this.oas;
	}

	public void setOas(List<Oa> oas) {
		this.oas = oas;
	}

	public Oa addOa(Oa oa) {
		getOas().add(oa);
		oa.setEstadoOa(this);

		return oa;
	}

	public Oa removeOa(Oa oa) {
		getOas().remove(oa);
		oa.setEstadoOa(null);

		return oa;
	}

}