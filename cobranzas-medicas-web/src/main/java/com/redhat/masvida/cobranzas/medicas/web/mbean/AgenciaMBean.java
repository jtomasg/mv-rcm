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
import com.redhat.masvida.vo.AgenciaVO;

@ManagedBean
@ViewScoped
public class AgenciaMBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = -6239437588285327644L;
	private static final Logger LOG = LoggerFactory
			.getLogger(AgenciaMBean.class);
	@Inject
	private FuseRestClient client;
	
	private List<AgenciaVO> agencias;
	
	@PostConstruct
	public void init() {
		agencias = new ArrayList<AgenciaVO>();
		findAll(null);
	}

	public void findAll(AjaxBehaviorEvent event) {
		
		try {
			if (this.agencias.isEmpty()){ //simulamos cache
				this.agencias =client.findAllAgencias();
			}
		} catch (Exception e) {
			//TODO Definir errores
		}
	}
	
	public List<AgenciaVO> getAgencias() {
		return agencias;
	}
	
	
}