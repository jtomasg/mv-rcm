package cl.masvida.poc.ejb;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import cl.masvida.poc.dao.RcmDAOLocal;
import cl.masvida.poc.dao.AgenciaDAOLocal;
import cl.masvida.poc.dao.TipoPagoDAOLocal;

import com.redhat.masvida.vo.AgenciaVO;
import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.TipoPagoVO;

/**
 * Session Bean implementation class RCMFacadeBean
 */
@Stateless
public class RCMFacadeBean implements RCMFacade {

	@EJB
	RcmDAOLocal rcmDAOLocal;
	
	@EJB
	AgenciaDAOLocal agenciaDAOLocal;
	
	@EJB
	TipoPagoDAOLocal tipoPagoDAOLocal;
	
    /**
     * Default constructor. 
     */
    public RCMFacadeBean() {
        // TODO Auto-generated constructor stub
    }
    
    public RcmVO buscarRcm(BigDecimal i){
    	return rcmDAOLocal.buscarRcm(i);
    }

	public List<AgenciaVO> buscarAgencias() {
		return agenciaDAOLocal.buscarAgencias();
	}

	public List<TipoPagoVO> buscarTipoPagos() {
		return tipoPagoDAOLocal.buscarTipoPagos();
	}

}
