package cl.masvida.poc.rcm.servicios;

import com.redhat.masvida.vo.AgenciaVO;
import com.redhat.masvida.vo.OaVO;
import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.TipoPagoVO;

public interface Rcm {
	
	RcmVO buscarPorFolio(RcmVO folio);

	AgenciaVO[] obtenerAgencias();

	TipoPagoVO[] obtenerTipoPago();
	
    String buscarTipoPago(Integer tipopago);

    String buscarAgencia(Integer id);
    
    OaVO buscarOaPorFolio(Integer id);

	public void guardarRcm(RcmVO rcmVO) throws Exception;
	
	public void eliminarPorFolio(Integer folio);
}
