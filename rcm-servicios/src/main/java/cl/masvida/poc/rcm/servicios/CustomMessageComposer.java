package cl.masvida.poc.rcm.servicios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.switchyard.Exchange;
import org.switchyard.Message;
import org.switchyard.component.common.label.EndpointLabel;
import org.switchyard.component.resteasy.composer.RESTEasyBindingData;
import org.switchyard.component.resteasy.composer.RESTEasyContextMapper;
import org.switchyard.component.resteasy.composer.RESTEasyMessageComposer;

import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.RecepcionCobranzaMedicaVO;

public class CustomMessageComposer extends RESTEasyMessageComposer {

	DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Message compose(RESTEasyBindingData source, Exchange exchange)
			throws Exception {
		final Message message = super.compose(source, exchange);

		RcmVO rcmVO = null;

		System.out.println("========Operation Name: "
				+ source.getOperationName());

		// si es el metodo get de busqueda por folio...
		if (source.getOperationName().equals("buscarPorFolio") ) {

			// se recorren e imprimen por cosola los parametros ingresados en la URL...
			for (int i = 0; i < source.getParameters().length; i++) {
				System.out.println("================== Param [" + i + "]: "
						+ source.getParameters()[i]);
			}

			// se envuelven los parametros en un RcmVO
			rcmVO = new RcmVO();
			RecepcionCobranzaMedicaVO cobranzaMedicaVO = new RecepcionCobranzaMedicaVO();
			cobranzaMedicaVO.setFolio(Integer.parseInt((String) source.getParameters()[0]));
			rcmVO.setRcm(cobranzaMedicaVO);

		}
		
		System.out.println((String) message.toString());

		// se asocia el contenido dentro del mensaje
		message.setContent(rcmVO);
		
		System.out.println((String) message.toString());

		return message;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RESTEasyBindingData decompose(Exchange exchange,
			RESTEasyBindingData target) throws Exception {
		
		try{
			
			Object content = exchange.getMessage().getContent();
			String opName = exchange.getContract().getProviderOperation().getName();
			
			if (opName.equals("getRCM") && (content == null)) {
				exchange.getContext()
						.setProperty(RESTEasyContextMapper.HTTP_RESPONSE_STATUS,
								404)
						.addLabels(new String[] { EndpointLabel.HTTP.label() });
			}
	
			target = super.decompose(exchange, target);
	
			System.out.println("Decomposing: "+target.getOperationName()+"...");
			
			if (target.getOperationName().equals("buscarPorFolio")
					&& (content != null)) {
				System.out.println("Unwraping RcmVO...");
	
				System.out.println("Fecha: "
						+ this.dateToString(((RcmVO) content).getRcm().getFechaRegistro()));
	
				// Unwrap the parameters
	//			target.setParameters(new Object[] { ((RcmVO) content).getFolio()
	//					+ ":"
	//					+ this.dateToString(((RcmVO) content).getFechaRecepcion())
	//					+ ":"
	//					+ this.dateToString(((RcmVO) content).getFechaRegistro()) });
				
				target.setParameters(new Object[] {(RcmVO) content});
			}
			
			System.out.println(target.getOperationName() + " Decomposed! ("+(content == null)+")");
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return target;
	}

	/**
	 * 
	 * @param fecha
	 * @return Date (fecha parseada como Date)
	 */
	private Date stringToDate(String strFecha) {
		Date fecha = null;
		try {
			fecha = df.parse(strFecha);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out
					.println("Formato de fecha o valores ingresados incorrectos!");
		}
		return fecha;
	}

	/**
	 * 
	 * @param Date
	 * @return String (fecha en formato string)
	 */
	private String dateToString(Date fecha) {
		String strFecha = null;
		try {
			strFecha = df.format(fecha);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Imposible parsear la fecha a formato String!");
		}
		return strFecha;
	}

}
