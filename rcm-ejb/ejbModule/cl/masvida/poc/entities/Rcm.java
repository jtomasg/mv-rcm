package cl.masvida.poc.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "RCM" database table.
 * 
 */
@Entity
@Table(name="rcm")
@NamedQueries({
@NamedQuery(name="Rcm.findAll", query="SELECT r FROM Rcm r"),
@NamedQuery(name="Rcm.findByFolio", query="SELECT r FROM Rcm r WHERE r.rcmFolio = :rcmFolio")})
public class Rcm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="rcm_folio", unique=true, nullable=false, precision=8)
	private BigDecimal rcmFolio;

	@Column(name="age_cod_pago", precision=4)
	private BigDecimal ageCodPago;

	@Column(name="age_cod_recep", precision=4)
	private BigDecimal ageCodRecep;

	@Column(name="rcm_cantidad_oa", precision=5)
	private BigDecimal rcmCantidadOa;

	@Column(name="rcm_descuento", precision=12)
	private BigDecimal rcmDescuento;

	@Temporal(TemporalType.DATE)
	@Column(name="rcm_fecha_pago")
	private Date rcmFechaPago;

	@Temporal(TemporalType.DATE)
	@Column(name="rcm_fecha_recepcion")
	private Date rcmFechaRecepcion;

	@Column(name="rcm_fechareg", length=10)
	private String rcmFecreg;

	@Column(name="rcm_monto", precision=12)
	private BigDecimal rcmMonto;

	@Column(name="rcm_nombrede", length=250)
	private String rcmNombrede;

	@Column(name="rcm_observ", length=250)
	private String rcmObserv;

	@Column(name="rcm_rut_cobrador", length=10)
	private String rcmRutCobrador;

	@Column(name="tpd_codigo", precision=2)
	private BigDecimal tpdCodigo;

	//bi-directional many-to-one association to Oa
	@OneToMany(mappedBy="rcm")
	private List<Oa> oas;

	//bi-directional many-to-one association to Agencia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="AGE_COD_RECEP",referencedColumnName="AGE_CODIGO", insertable = false, updatable = false)
		})
	private Agencia agencia1;

	//bi-directional many-to-one association to Agencia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="AGE_COD_PAGO",referencedColumnName="AGE_CODIGO", insertable = false, updatable = false)
		})
	private Agencia agencia2;

	//bi-directional many-to-one association to TipoPagoDoc
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="TPD_CODIGO",referencedColumnName="TPD_CODIGO", insertable = false, updatable = false)
		})
	private TipoPagoDoc tipoPagoDoc;

	public Rcm() {
	}

	public BigDecimal getRcmFolio() {
		return this.rcmFolio;
	}

	public void setRcmFolio(BigDecimal rcmFolio) {
		this.rcmFolio = rcmFolio;
	}

	public BigDecimal getAgeCodPago() {
		return this.ageCodPago;
	}

	public void setAgeCodPago(BigDecimal ageCodPago) {
		this.ageCodPago = ageCodPago;
	}

	public BigDecimal getAgeCodRecep() {
		return this.ageCodRecep;
	}

	public void setAgeCodRecep(BigDecimal ageCodRecep) {
		this.ageCodRecep = ageCodRecep;
	}

	public BigDecimal getRcmCantidadOa() {
		return this.rcmCantidadOa;
	}

	public void setRcmCantidadOa(BigDecimal rcmCantidadOa) {
		this.rcmCantidadOa = rcmCantidadOa;
	}

	public BigDecimal getRcmDescuento() {
		return this.rcmDescuento;
	}

	public void setRcmDescuento(BigDecimal rcmDescuento) {
		this.rcmDescuento = rcmDescuento;
	}

	public Date getRcmFechaPago() {
		return this.rcmFechaPago;
	}

	public void setRcmFechaPago(Date rcmFechaPago) {
		this.rcmFechaPago = rcmFechaPago;
	}

	public Date getRcmFechaRecepcion() {
		return this.rcmFechaRecepcion;
	}

	public void setRcmFechaRecepcion(Date rcmFechaRecepcion) {
		this.rcmFechaRecepcion = rcmFechaRecepcion;
	}

	public String getRcmFecreg() {
		return this.rcmFecreg;
	}

	public void setRcmFecreg(String rcmFecreg) {
		this.rcmFecreg = rcmFecreg;
	}

	public BigDecimal getRcmMonto() {
		return this.rcmMonto;
	}

	public void setRcmMonto(BigDecimal rcmMonto) {
		this.rcmMonto = rcmMonto;
	}

	public String getRcmNombrede() {
		return this.rcmNombrede;
	}

	public void setRcmNombrede(String rcmNombrede) {
		this.rcmNombrede = rcmNombrede;
	}

	public String getRcmObserv() {
		return this.rcmObserv;
	}

	public void setRcmObserv(String rcmObserv) {
		this.rcmObserv = rcmObserv;
	}

	public String getRcmRutCobrador() {
		return this.rcmRutCobrador;
	}

	public void setRcmRutCobrador(String rcmRutCobrador) {
		this.rcmRutCobrador = rcmRutCobrador;
	}

	public BigDecimal getTpdCodigo() {
		return this.tpdCodigo;
	}

	public void setTpdCodigo(BigDecimal tpdCodigo) {
		this.tpdCodigo = tpdCodigo;
	}

	public List<Oa> getOas() {
		return this.oas;
	}

	public void setOas(List<Oa> oas) {
		this.oas = oas;
	}

	public Oa addOa(Oa oa) {
		getOas().add(oa);
		oa.setRcm(this);

		return oa;
	}

	public Oa removeOa(Oa oa) {
		getOas().remove(oa);
		oa.setRcm(null);

		return oa;
	}

	public Agencia getAgencia1() {
		return this.agencia1;
	}

	public void setAgencia1(Agencia agencia1) {
		this.agencia1 = agencia1;
	}

	public Agencia getAgencia2() {
		return this.agencia2;
	}

	public void setAgencia2(Agencia agencia2) {
		this.agencia2 = agencia2;
	}

	public TipoPagoDoc getTipoPagoDoc() {
		return this.tipoPagoDoc;
	}

	public void setTipoPagoDoc(TipoPagoDoc tipoPagoDoc) {
		this.tipoPagoDoc = tipoPagoDoc;
	}

}