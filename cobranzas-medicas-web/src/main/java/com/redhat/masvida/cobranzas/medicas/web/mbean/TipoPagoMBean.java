package com.redhat.masvida.cobranzas.medicas.web.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.masvida.cobranzas.medicas.web.mbean.base.BaseManagedBean;
import com.redhat.masvida.cobranzas.medicas.web.rest.client.FuseRestClient;
import com.redhat.masvida.vo.TipoPagoVO;

@ManagedBean
@ViewScoped
public class TipoPagoMBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = -6239437588285327644L;
	private static final Logger LOG = LoggerFactory
			.getLogger(TipoPagoMBean.class);
	@Inject
	private FuseRestClient client;

	private List<TipoPagoVO> tiposPago;
	
	@PostConstruct
	public void init() {
		tiposPago = new ArrayList<TipoPagoVO>();
		loadTipoPago();
	}

	public void findAll(AjaxBehaviorEvent event) {
		loadTipoPago();
		
	}
	
	public List<TipoPagoVO> getAgencias() {
		return tiposPago;
	}
	
	private void loadTipoPago(){
		try {
			if (this.tiposPago.isEmpty()){ //simulamos cache
				this.tiposPago =client.findAllTipoPagoValido();
			}
		} catch (Exception e) {
			//TODO Definir errores
		}
	}

	public List<TipoPagoVO> getTiposPago() {
		return tiposPago;
	}

	public void setTiposPago(List<TipoPagoVO> tiposPago) {
		this.tiposPago = tiposPago;
	}
	
	
	
}