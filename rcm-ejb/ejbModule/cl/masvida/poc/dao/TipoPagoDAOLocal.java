package cl.masvida.poc.dao;


import java.math.BigDecimal;
import java.util.List;

import com.redhat.masvida.vo.TipoPagoVO;


public interface TipoPagoDAOLocal {
	public List<TipoPagoVO> buscarTipoPagos();
	public String buscarTipoPago(BigDecimal i);
}
