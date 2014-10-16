package cl.masvida.poc.rcm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.switchyard.Exchange;
import org.switchyard.Message;
import org.switchyard.component.common.label.EndpointLabel;
import org.switchyard.component.resteasy.composer.RESTEasyBindingData;
import org.switchyard.component.resteasy.composer.RESTEasyContextMapper;
import org.switchyard.component.resteasy.composer.RESTEasyMessageComposer;

import cl.masvida.poc.rcm.vo.RcmVO;

public class CustomMessageComposer extends RESTEasyMessageComposer {

	DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Message compose(RESTEasyBindingData source, Exchange exchange)
			throws Exception {
		final Message message = super.compose(source, exchange);

		RcmVO rcm = null;

		System.out.println("========Operation Name: "
				+ source.getOperationName());

		if (source.getOperationName().equals("buscarPorFolio") ) {

			for (int i = 0; i < source.getParameters().length; i++) {
				System.out.println("================== Param [" + i + "]: "
						+ source.getParameters()[i]);
			}

			// se envuelven los parametros en un RcmVO
			rcm = new RcmVO((String) source.getParameters()[0],
					this.stringToDate((String) source.getParameters()[1]),
					this.stringToDate((String) source.getParameters()[2]));

		}
		
		System.out.println((String) message.toString());

		// se asocia el contenido dentro del mensaje
		message.setContent(rcm);
		
		System.out.println((String) message.toString());

		return message;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RESTEasyBindingData decompose(Exchange exchange,
			RESTEasyBindingData target) throws Exception {
		Object content = exchange.getMessage().getContent();
		String opName = exchange.getContract().getProviderOperation().getName();
		if (opName.equals("getRCM") && (content == null)) {
			exchange.getContext()
					.setProperty(RESTEasyContextMapper.HTTP_RESPONSE_STATUS,
							404)
					.addLabels(new String[] { EndpointLabel.HTTP.label() });
		}

		target = super.decompose(exchange, target);

		if (target.getOperationName().equals("buscarPorFolio")
				&& (content != null) && (content instanceof RcmVO)) {
			System.out.println("Unwraping RcmVO...");

			System.out.println("Fecha: "
					+ this.dateToString(((RcmVO) content).getFechaRegistro()));

			// Unwrap the parameters
//			target.setParameters(new Object[] { ((RcmVO) content).getFolio()
//					+ ":"
//					+ this.dateToString(((RcmVO) content).getFechaRecepcion())
//					+ ":"
//					+ this.dateToString(((RcmVO) content).getFechaRegistro()) });
			
			target.setParameters(new Object[] {(RcmVO) content});
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
