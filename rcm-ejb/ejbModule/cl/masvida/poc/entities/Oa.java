package cl.masvida.poc.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the "OA" database table.
 * 
 */
@Entity
@Table(name="oa")
@NamedQuery(name="Oa.findAll", query="SELECT o FROM Oa o")
public class Oa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="oda_folio", unique=true, nullable=false, precision=12)
	private BigDecimal odaFolio;

	@Column(name="bonificacion", precision=8)
	private BigDecimal bonificacion;

	@Column(name="copago", precision=8)
	private BigDecimal copago;

	@Column(name="eoa_codigo", precision=2)
	private BigDecimal eoaCodigo;

	@Temporal(TemporalType.DATE)
	@Column(name="oda_fechaemi")
	private Date odaFechaemi;

	@Column(name="rcm_folio", precision=8)
	private BigDecimal rcmFolio;

	@Column(name="tit_rut", length=10)
	private String titRut;

	@Column(name="valor", precision=8)
	private BigDecimal valor;

	//bi-directional many-to-one association to Cotizante
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="tit_rut",referencedColumnName="cot_rut", insertable = false, updatable = false)
		})
	private Cotizante cotizante;

	//bi-directional many-to-one association to EstadoOa
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="eoa_codigo",referencedColumnName="eoa_codigo", insertable = false, updatable = false)
		})
	private EstadoOa estadoOa;

	//bi-directional many-to-one association to Rcm
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="rcm_folio",referencedColumnName="rcm_folio", insertable = false, updatable = false)
		})
	private Rcm rcm;

	public Oa() {
	}

	public BigDecimal getOdaFolio() {
		return this.odaFolio;
	}

	public void setOdaFolio(BigDecimal odaFolio) {
		this.odaFolio = odaFolio;
	}

	public BigDecimal getBonificacion() {
		return this.bonificacion;
	}

	public void setBonificacion(BigDecimal bonificacion) {
		this.bonificacion = bonificacion;
	}

	public BigDecimal getCopago() {
		return this.copago;
	}

	public void setCopago(BigDecimal copago) {
		this.copago = copago;
	}

	public BigDecimal getEoaCodigo() {
		return this.eoaCodigo;
	}

	public void setEoaCodigo(BigDecimal eoaCodigo) {
		this.eoaCodigo = eoaCodigo;
	}

	public Date getOdaFechaemi() {
		return this.odaFechaemi;
	}

	public void setOdaFechaemi(Date odaFechaemi) {
		this.odaFechaemi = odaFechaemi;
	}

	public BigDecimal getRcmFolio() {
		return this.rcmFolio;
	}

	public void setRcmFolio(BigDecimal rcmFolio) {
		this.rcmFolio = rcmFolio;
	}

	public String getTitRut() {
		return this.titRut;
	}

	public void setTitRut(String titRut) {
		this.titRut = titRut;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Cotizante getCotizante() {
		return this.cotizante;
	}

	public void setCotizante(Cotizante cotizante) {
		this.cotizante = cotizante;
	}

	public EstadoOa getEstadoOa() {
		return this.estadoOa;
	}

	public void setEstadoOa(EstadoOa estadoOa) {
		this.estadoOa = estadoOa;
	}

	public Rcm getRcm() {
		return this.rcm;
	}

	public void setRcm(Rcm rcm) {
		this.rcm = rcm;
	}

}