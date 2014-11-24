package cl.masvida.poc.dao;

import java.math.BigDecimal;
import com.redhat.masvida.vo.RcmVO;



public interface RcmDAOLocal {
	public RcmVO buscarRcm(BigDecimal i);
	public void guardarRcm(RcmVO rcmVO);
	public void eliminarRcm(BigDecimal i);
}
