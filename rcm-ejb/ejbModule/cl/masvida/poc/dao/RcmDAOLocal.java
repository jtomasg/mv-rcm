package cl.masvida.poc.dao;

import java.math.BigDecimal;
import java.util.List;

import com.redhat.masvida.vo.AgenciaVO;
import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.TipoPagoVO;


public interface RcmDAOLocal {
	public RcmVO buscarRcm(BigDecimal i);
	public List<AgenciaVO> buscarAgencias();
	public List<TipoPagoVO> buscarTipoPagos();
}
