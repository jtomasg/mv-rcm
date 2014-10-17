package cl.masvida.poc.rcm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.switchyard.component.bean.Service;

import cl.masvida.poc.ejb.RCMFacade;
import cl.masvida.poc.ejb.RCMFacadeBean;

import com.redhat.masvida.vo.AgenciaVO;
import com.redhat.masvida.vo.CobradorVO;
import com.redhat.masvida.vo.OrdenAtencionVO;
import com.redhat.masvida.vo.PagoVO;
import com.redhat.masvida.vo.PersonaVO;
import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.TipoPagoVO;

@Service(Rcm.class)
public class RcmBean implements Rcm {

	private static HashMap<Integer, RcmVO> dbRcm;

	@Override
	public RcmVO buscarPorFolio(RcmVO rcmIn) {

		RcmVO rcmVO = null;

		System.out.println("Numero de folio: " + rcmIn.getRcm().getFolio()
				+ " desde SwitchYard!");
		// se retorna RCM con datos Dummy. Reemplazar esta l�gica con busqueda
		// en BD.
		try {

			// if(dbRcm==null)
			// dbRcm= new HashMap<Integer, RcmVO>();

			// rcmVO = dbRcm.get(rcmIn.getRcm().getFolio());

			rcmVO = rcmIn;

			List<OrdenAtencionVO> lsOrdenAtencion = new ArrayList<OrdenAtencionVO>();
			for (int i = 0; i < 200; i++) {

				OrdenAtencionVO oa = new OrdenAtencionVO();
				oa.setBonificacion(10 * i);
				oa.setCopago(new Double(i));
				oa.setEstado("ESTADO");
				oa.setFechaEmision(new Date());
				oa.setFolioOA(12 + i);
				PersonaVO persona = new PersonaVO();
				persona.setNombre("Persona " + i);
				persona.setRut(11111111);
				persona.setDv('1');
				oa.setTitular(persona);
				oa.setValor(new Double(11 * i));
				lsOrdenAtencion.add(oa);

				rcmVO.setOrdenes(lsOrdenAtencion);
			}

			// Agencia...
			rcmVO.getRcm().setAgenciaRecepcion(new AgenciaVO());
			rcmVO.getRcm().getAgenciaRecepcion()
					.setDescripcion("Agencia Dummy");
			rcmVO.getRcm().getAgenciaRecepcion().setId(1);
			rcmVO.getRcm().setObservacion("Blablablavbla...");
			rcmVO.getRcm().setFechaRecepcion(new Date());
			rcmVO.getRcm().setFechaRegistro(new Date());

			// Pago
			rcmVO.setPago(new PagoVO());
			rcmVO.getPago()
					.setAgenciaPago(rcmVO.getRcm().getAgenciaRecepcion());
			rcmVO.getPago().setCobrador(new CobradorVO());
			rcmVO.getPago().getCobrador().setNombre("Cobrador Dummy");
			rcmVO.getPago().getCobrador().setRut(111111111);
			rcmVO.getPago().setConvenioPago("1212");
			rcmVO.getPago().setFechaPago(new Date());
			rcmVO.getPago().setTipoPago(new TipoPagoVO());
			rcmVO.getPago().getTipoPago().setId(1);
			rcmVO.getPago().getTipoPago().setNombre("Tipo Pago Dummy");

			// llamada a capa de negocio (EJB)
			RCMFacade rcmFacade = (RCMFacade) lookup(
					"rcm-ejb", 
					RCMFacadeBean.class.getSimpleName(), 
					RCMFacade.class.getName());
			
			rcmFacade.buscarRcm(new BigDecimal(rcmIn.getRcm().getFolio()));
			
			System.out.println("Busqueda RCM finalizada!");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rcmVO;
	}

	@Override
	public AgenciaVO[] obtenerAgencias() {

		AgenciaVO[] arrAgencias = null;

		try {

			// Llamada a EJB de negocio

			arrAgencias = new AgenciaVO[20];
			for (int i = 0; i < 20; i++) {
				arrAgencias[i] = new AgenciaVO();
				arrAgencias[i].setId(i * 100);
				arrAgencias[i].setDescripcion("Agencia " + (i * 100));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrAgencias;
	}

	@Override
	public TipoPagoVO[] obtenerTipoPago() {
		TipoPagoVO[] arrTipoPago = null;

		try {

			// Llamada a EJB de negocio

			arrTipoPago = new TipoPagoVO[5];
			for (int i = 0; i < 5; i++) {
				arrTipoPago[i] = new TipoPagoVO();
				arrTipoPago[i].setId(i);
				arrTipoPago[i].setNombre("Pago Tipo " + i);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrTipoPago;
	}

	@Override
	public void guardarRcm(RcmVO rcmVO) throws Exception {

		if (dbRcm == null)
			dbRcm = new HashMap<Integer, RcmVO>();

		dbRcm.put(rcmVO.getRcm().getFolio(), rcmVO);

	}

	private Object lookup(String moduleName, String beanName, String interfaceName){
		
		Object ejbReference = null;
		
		try{
			
			final Hashtable<String, String> jndiProperties = new Hashtable();
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context context = new InitialContext(jndiProperties);
			
			final String appName = "";
			//final String moduleName = "rcm-ejb";
			final String distinctName = "";
			//final String beanName = SampleBeanRemoteImpl.class.getSimpleName();
			//final String viewClassName = SampleBeanRemote.class.getName();
			
			String strJndiLookup = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + interfaceName;
			
			System.out.println("Looking EJB via JNDI ");
			System.out.println(strJndiLookup);
			
			ejbReference = context.lookup(strJndiLookup);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ejbReference;
	}
	
}
