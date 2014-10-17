package cl.masvida.poc.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "COTIZANTE" database table.
 * 
 */
@Entity
@Table(name="cotizante")
@NamedQuery(name="Cotizante.findAll", query="SELECT c FROM Cotizante c")
public class Cotizante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cot_rut", unique=true, nullable=false, length=10)
	private String cotRut;

	@Column(name="cot_dv", length=1)
	private String cotDv;

	@Column(name="cot_materno", length=20)
	private String cotMaterno;

	@Column(name="cot_nombres", length=50)
	private String cotNombres;

	@Column(name="cot_paterno", length=20)
	private String cotPaterno;

	//bi-directional many-to-one association to Oa
	@OneToMany(mappedBy="cotizante")
	private List<Oa> oas;

	public Cotizante() {
	}

	public String getCotRut() {
		return this.cotRut;
	}

	public void setCotRut(String cotRut) {
		this.cotRut = cotRut;
	}

	public String getCotDv() {
		return this.cotDv;
	}

	public void setCotDv(String cotDv) {
		this.cotDv = cotDv;
	}

	public String getCotMaterno() {
		return this.cotMaterno;
	}

	public void setCotMaterno(String cotMaterno) {
		this.cotMaterno = cotMaterno;
	}

	public String getCotNombres() {
		return this.cotNombres;
	}

	public void setCotNombres(String cotNombres) {
		this.cotNombres = cotNombres;
	}

	public String getCotPaterno() {
		return this.cotPaterno;
	}

	public void setCotPaterno(String cotPaterno) {
		this.cotPaterno = cotPaterno;
	}

	public List<Oa> getOas() {
		return this.oas;
	}

	public void setOas(List<Oa> oas) {
		this.oas = oas;
	}

	public Oa addOa(Oa oa) {
		getOas().add(oa);
		oa.setCotizante(this);

		return oa;
	}

	public Oa removeOa(Oa oa) {
		getOas().remove(oa);
		oa.setCotizante(null);

		return oa;
	}

}