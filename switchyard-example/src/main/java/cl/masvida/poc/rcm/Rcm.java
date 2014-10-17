package cl.masvida.poc.rcm;

import com.redhat.masvida.vo.AgenciaVO;
import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.TipoPagoVO;

public interface Rcm {

	RcmVO buscarPorFolio(RcmVO folio);
	
	AgenciaVO[] obtenerAgencias();
	
	TipoPagoVO[] obtenerTipoPago();
	
	public void guardarRcm(RcmVO rcmVO)throws Exception;
	
}
