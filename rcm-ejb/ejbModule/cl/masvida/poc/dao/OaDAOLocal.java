package cl.masvida.poc.dao;

import java.math.BigDecimal;

import com.redhat.masvida.vo.OaVO;
import com.redhat.masvida.vo.RcmVO;



public interface OaDAOLocal {
	public OaVO buscarOa(BigDecimal i);
}
