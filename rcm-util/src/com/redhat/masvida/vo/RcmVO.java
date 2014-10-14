package com.redhat.masvida.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class RcmVO implements Serializable {
	private static final long serialVersionUID = 1L;


	private BigDecimal rcmFolio;
	private BigDecimal ageCodPago;
	private BigDecimal ageCodRecep;
	private BigDecimal rcmCantidadOa;
	private BigDecimal rcmDescuento;
	private Date rcmFechaPago;
	private Date rcmFechaRecepcion;
	private String rcmFecreg;
	private BigDecimal rcmMonto;
	private String rcmNombrede;
	private String rcmObserv;
	private String rcmRutCobrador;
	private BigDecimal tpdCodigo;
	private List<OaVO> oas;
	private AgenciaVO agencia1;
	private AgenciaVO agencia2;
	private TipoPagoDocVO tipoPagoDoc;

	public RcmVO() {
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

	public List<OaVO> getOas() {
		return this.oas;
	}

	public void setOas(List<OaVO> oas) {
		this.oas = oas;
	}

	public OaVO addOa(OaVO oa) {
		getOas().add(oa);
		oa.setRcm(this);

		return oa;
	}

	public OaVO removeOa(OaVO oa) {
		getOas().remove(oa);
		oa.setRcm(null);

		return oa;
	}

	public AgenciaVO getAgencia1() {
		return this.agencia1;
	}

	public void setAgencia1(AgenciaVO agencia1) {
		this.agencia1 = agencia1;
	}

	public AgenciaVO getAgencia2() {
		return this.agencia2;
	}

	public void setAgencia2(AgenciaVO agencia2) {
		this.agencia2 = agencia2;
	}

	public TipoPagoDocVO getTipoPagoDoc() {
		return this.tipoPagoDoc;
	}

	public void setTipoPagoDoc(TipoPagoDocVO tipoPagoDoc) {
		this.tipoPagoDoc = tipoPagoDoc;
	}

}