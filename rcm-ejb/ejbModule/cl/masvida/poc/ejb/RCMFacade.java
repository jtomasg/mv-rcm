package cl.masvida.poc.ejb;

import java.math.BigDecimal;

import javax.ejb.Remote;

import com.redhat.masvida.vo.RcmVO;

@Remote
public interface RCMFacade {

	public RcmVO buscarRcm(BigDecimal i);
}
