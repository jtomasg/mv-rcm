package cl.masvida.poc.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the "AGENCIA" database table.
 * 
 */
@Entity
@Table(name="agencia")
@NamedQuery(name="Agencia.findAll", query="SELECT a FROM Agencia a")
public class Agencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="age_codigo", unique=true, nullable=false, precision=4)
	private BigDecimal ageCodigo;

	@Column(name="age_disponible", nullable=false, length=1)
	private String ageDisponible;

	@Column(name="age_nombre", length=250)
	private String ageNombre;

	//bi-directional many-to-one association to Rcm
	@OneToMany(mappedBy="agencia1")
	private List<Rcm> rcms1;

	//bi-directional many-to-one association to Rcm
	@OneToMany(mappedBy="agencia2")
	private List<Rcm> rcms2;

	public Agencia() {
	}

	public BigDecimal getAgeCodigo() {
		return this.ageCodigo;
	}

	public void setAgeCodigo(BigDecimal ageCodigo) {
		this.ageCodigo = ageCodigo;
	}

	public String getAgeDisponible() {
		return this.ageDisponible;
	}

	public void setAgeDisponible(String ageDisponible) {
		this.ageDisponible = ageDisponible;
	}

	public String getAgeNombre() {
		return this.ageNombre;
	}

	public void setAgeNombre(String ageNombre) {
		this.ageNombre = ageNombre;
	}

	public List<Rcm> getRcms1() {
		return this.rcms1;
	}

	public void setRcms1(List<Rcm> rcms1) {
		this.rcms1 = rcms1;
	}

	public Rcm addRcms1(Rcm rcms1) {
		getRcms1().add(rcms1);
		rcms1.setAgencia1(this);

		return rcms1;
	}

	public Rcm removeRcms1(Rcm rcms1) {
		getRcms1().remove(rcms1);
		rcms1.setAgencia1(null);

		return rcms1;
	}

	public List<Rcm> getRcms2() {
		return this.rcms2;
	}

	public void setRcms2(List<Rcm> rcms2) {
		this.rcms2 = rcms2;
	}

	public Rcm addRcms2(Rcm rcms2) {
		getRcms2().add(rcms2);
		rcms2.setAgencia2(this);

		return rcms2;
	}

	public Rcm removeRcms2(Rcm rcms2) {
		getRcms2().remove(rcms2);
		rcms2.setAgencia2(null);

		return rcms2;
	}

}