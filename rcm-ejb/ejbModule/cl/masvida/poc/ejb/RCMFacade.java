package cl.masvida.poc.ejb;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import com.redhat.masvida.vo.AgenciaVO;
import com.redhat.masvida.vo.OaVO;
import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.TipoPagoVO;

@Remote
public interface RCMFacade {

	public RcmVO buscarRcm(BigDecimal i);
	public void eliminarRcm(BigDecimal i);
	public String buscarAgencia(BigDecimal i);
	public List<AgenciaVO> buscarAgencias();
	public List<TipoPagoVO> buscarTipoPagos();
	public String buscarTipoPago(BigDecimal i);
	public OaVO buscarOa(BigDecimal i);
	public void guardarRcm(RcmVO rcmVO);
}
