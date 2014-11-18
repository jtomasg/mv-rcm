package cl.masvida.poc.dao;

import java.math.BigDecimal;
import java.util.List;

import com.redhat.masvida.vo.AgenciaVO;

public interface AgenciaDAOLocal {
	public List<AgenciaVO> buscarAgencias();
	public String buscarAgencia(BigDecimal i);
}
