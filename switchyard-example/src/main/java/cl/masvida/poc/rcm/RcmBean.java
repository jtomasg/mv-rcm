package cl.masvida.poc.rcm;

import java.util.Date;

import org.switchyard.component.bean.Service;

import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.RecepcionCobranzaMedicaVO;

@Service(Rcm.class)
public class RcmBean implements Rcm {
	

	@Override
	public RcmVO buscarPorFolio(RcmVO rcmIn) {
		
		RcmVO rcmVO = null;
		
		System.out.println("Numero de folio: "+rcmIn.getRcm().getFolio()+" desde SwitchYard!");
		// se retorna RCM con datos Dummy. Reemplazar esta l�gica con busqueda en BD.
		try{
			
			// llamada a capa de negocio (EJB)
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		rcmVO = new RcmVO();
		rcmVO.setRcm(new RecepcionCobranzaMedicaVO());
		rcmVO.getRcm().setFolio(rcmIn.getRcm().getFolio());
		rcmVO.getRcm().setFechaRecepcion(new Date());
		rcmVO.getRcm().setFechaRegistro(new Date());
		
		return rcmVO;
	}

}
