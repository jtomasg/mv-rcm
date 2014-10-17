package cl.masvida.poc.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the "TIPO_PAGO_DOC" database table.
 * 
 */
@Entity
@Table(name="tipo_pago_doc")
@NamedQuery(name="TipoPagoDoc.findAll", query="SELECT t FROM TipoPagoDoc t")
public class TipoPagoDoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="tpd_codigo", unique=true, nullable=false, precision=2)
	private BigDecimal tpdCodigo;

	@Column(name="tpd_descripcion", nullable=false, length=250)
	private String tpdDescripcion;

	@Column(name="tpd_vigencia", nullable=false, length=1)
	private String tpdVigencia;

	//bi-directional many-to-one association to Rcm
	@OneToMany(mappedBy="tipoPagoDoc")
	private List<Rcm> rcms;

	public TipoPagoDoc() {
	}

	public BigDecimal getTpdCodigo() {
		return this.tpdCodigo;
	}

	public void setTpdCodigo(BigDecimal tpdCodigo) {
		this.tpdCodigo = tpdCodigo;
	}

	public String getTpdDescripcion() {
		return this.tpdDescripcion;
	}

	public void setTpdDescripcion(String tpdDescripcion) {
		this.tpdDescripcion = tpdDescripcion;
	}

	public String getTpdVigencia() {
		return this.tpdVigencia;
	}

	public void setTpdVigencia(String tpdVigencia) {
		this.tpdVigencia = tpdVigencia;
	}

	public List<Rcm> getRcms() {
		return this.rcms;
	}

	public void setRcms(List<Rcm> rcms) {
		this.rcms = rcms;
	}

	public Rcm addRcm(Rcm rcm) {
		getRcms().add(rcm);
		rcm.setTipoPagoDoc(this);

		return rcm;
	}

	public Rcm removeRcm(Rcm rcm) {
		getRcms().remove(rcm);
		rcm.setTipoPagoDoc(null);

		return rcm;
	}

}