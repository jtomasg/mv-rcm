package cl.masvida.poc.ejb;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import cl.masvida.poc.dao.RcmDAOLocal;

import com.redhat.masvida.vo.RcmVO;

/**
 * Session Bean implementation class RCMFacadeBean
 */
@Stateless
public class RCMFacadeBean implements RCMFacade {

	@EJB
	RcmDAOLocal rcmDAOLocal;
	
    /**
     * Default constructor. 
     */
    public RCMFacadeBean() {
        // TODO Auto-generated constructor stub
    }
    
    public RcmVO buscarRcm(BigDecimal i){
    	return rcmDAOLocal.buscarRcm(i);
    }

}
