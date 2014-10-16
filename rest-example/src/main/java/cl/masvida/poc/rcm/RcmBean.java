package cl.masvida.poc.rcm;

import java.util.Date;

import org.switchyard.component.bean.Service;

import cl.masvida.poc.rcm.vo.RcmVO;

@Service(Rcm.class)
public class RcmBean implements Rcm {

	@Override
	public RcmVO buscarPorFolio(RcmVO rcm) {
		System.out.println("Numero de folio: "+rcm.getFolio()+" desde SwitchYard!");
		// se retorna RCM con datos Dummy. Reemplazar esta l—gica con busqueda en BD.
		return new RcmVO(rcm.getFolio(), new Date(), new Date());
	}

}
