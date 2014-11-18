package cl.masvida.poc.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

import cl.masvida.poc.entities.Rcm;

import com.redhat.masvida.vo.AgenciaVO;
import com.redhat.masvida.vo.CobradorVO;
import com.redhat.masvida.vo.OrdenAtencionVO;
import com.redhat.masvida.vo.PagoVO;
import com.redhat.masvida.vo.PersonaVO;
import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.RecepcionCobranzaMedicaVO;
import com.redhat.masvida.vo.TipoPagoVO;

@Stateless
public class RcmDAO implements RcmDAOLocal {

	@PersistenceContext(unitName = "masvida-ejb-jpa")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public RcmDAO() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Método para buscar un RCM vía ID.
	 */
	public RcmVO buscarRcm(BigDecimal folio) {

		// 1. Se ejecuta la consulta contra la BD...
		// Query query = em.createNamedQuery("Rcm.findByFolio");
		// Query query2 =
		// em.createQuery("SELECT rcm FROM Rcm rcm WHERE rcm.rcmFolio = :folio");
		// Query query3 = em.createNativeQuery("{call ...}"); // Ejemplo llamada
		// a SP en Oracle
		// if (lsRcms != null && lsRcms.size() == 1) {
		// rcmEntity = lsRcms.get(0);
		// }
		RcmVO rcmVO = null;
		// Obtenemos la sesión desde el EM.
				Session session = (Session) em.getDelegate();

				/*
				 * Se especifica el nivel de isolation de esta forma, ya que
				 * session.connection, está deprecado en Hibernate 4+. Hay otras formas
				 * de obtener la conexión y hacer el set del nivel de isolation
				 * http://stackoverflow
				 * .com/questions/3526556/session-connection-deprecated-on-hibernate
				 */
				session.doWork(new Work() {

					@Override
					public void execute(Connection connection) throws SQLException {
						connection
								.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
					}

				});
				// Comienza la Transacción
				Transaction tx = null;
				try {
					tx = session.beginTransaction();
					System.out.println("Buscando RCM por Folio....");
					// Cambiando tipo de la Query.
					org.hibernate.Query query = session
							.createQuery("FROM Rcm WHERE rcmFolio=:folio");
					query.setParameter("folio", folio);
					@SuppressWarnings("unchecked")
					List<Rcm> list = query.list();
					tx.commit();
                    
					// Si se encontro un registro en la BD...
					if (list.size() > 0){
						rcmVO = getRcmEntityToVO(list.get(0));
					}

				} catch (Exception e) {
					if (tx != null)
						tx.rollback();
					e.printStackTrace();
				} finally {
					session.close();
				}
		return rcmVO;

	}

	private RcmVO getRcmEntityToVO(Rcm rcmEntity) {

		RcmVO rcmVO = null;

		try {

			if (rcmEntity != null) {

				rcmVO = new RcmVO();

				RecepcionCobranzaMedicaVO recepcion = new RecepcionCobranzaMedicaVO();

				recepcion.setFolio(rcmEntity.getRcmFolio().intValue());

				// 1 ) Seteamos los valores de RecepcionCobranzaMedicaVO
				// Settings AgenciaVO
				AgenciaVO agenciaRecep = new AgenciaVO();
				agenciaRecep.setId(rcmEntity.getAgencia1().getAgeCodigo()
						.intValue());
				agenciaRecep.setDescripcion(rcmEntity.getAgencia1()
						.getAgeNombre());
				recepcion.setAgenciaRecepcion(agenciaRecep);

				// Settings fecha recepcion
				recepcion.setFechaRecepcion(rcmEntity.getRcmFechaRecepcion());

				// Settings fecha registro
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
				recepcion.setFechaRegistro(formatter.parse(rcmEntity
						.getRcmFecreg()));

				// Settings Observación
				recepcion.setObservacion(rcmEntity.getRcmObserv());

				// Setting de los Datos de RCM
				rcmVO.setRcm(recepcion);

				// 2 ) Setting de los Datos de Pago
				// Inicializamos un PagoVO
				PagoVO pago = new PagoVO();

				// Inicializamos un CobradorVO y seteamos sus datos
				CobradorVO cobrador = new CobradorVO();
				cobrador.setNombre(rcmEntity.getRcmNombrede());
				cobrador.setRut(Integer.parseInt(rcmEntity.getRcmRutCobrador()));
				pago.setCobrador(cobrador);

				// Inicializamos un TipoPagoVO y seteamos sus datos
				TipoPagoVO tp = new TipoPagoVO();
				tp.setId(rcmEntity.getTipoPagoDoc().getTpdCodigo().intValue());
				tp.setNombre(rcmEntity.getTipoPagoDoc().getTpdDescripcion());
				pago.setTipoPago(tp);

				// Inicializamos un AgenciaVO y seteamos sus datos
				AgenciaVO agenciaPago = new AgenciaVO();
				agenciaPago.setId(rcmEntity.getAgencia2().getAgeCodigo()
						.intValue());
				agenciaPago.setDescripcion(rcmEntity.getAgencia2()
						.getAgeNombre());
				pago.setAgenciaPago(agenciaPago);

				// Seteamos el valor de convenio (Dummy)
				pago.setConvenioPago("1212");

				// Seteamos el valor de la fecha de pago
				pago.setFechaPago(rcmEntity.getRcmFechaPago());

				// 3 ) Setting de los datos de las ordenes de atencion
				// Convertir la Lista de Entities OA en VO
				List<OrdenAtencionVO> ordenes = new ArrayList<OrdenAtencionVO>();
				for (int i = 0; i < rcmEntity.getOas().size(); i++) {
					OrdenAtencionVO oa = new OrdenAtencionVO();
					oa.setFolioOA(rcmEntity.getOas().get(i).getOdaFolio()
							.intValue());
					oa.setFechaEmision(rcmEntity.getOas().get(i)
							.getOdaFechaemi());
					oa.setValor(rcmEntity.getOas().get(i).getValor()
							.doubleValue());
					oa.setCopago(rcmEntity.getOas().get(i).getCopago()
							.doubleValue());
					oa.setBonificacion(rcmEntity.getOas().get(i)
							.getBonificacion().intValue());
					oa.setEstado(rcmEntity.getOas().get(i).getEstadoOa()
							.getEoaDescripcion());

					// Seteamos los datos del titular
					PersonaVO persona = new PersonaVO();
					persona.setRut(Integer.parseInt(rcmEntity.getOas().get(i)
							.getCotizante().getCotRut()));
					persona.setDv(rcmEntity.getOas().get(i).getCotizante()
							.getCotDv().charAt(0));
					persona.setNombre(rcmEntity.getOas().get(i).getCotizante()
							.getCotNombres()
							+ " "
							+ rcmEntity.getOas().get(i).getCotizante()
									.getCotPaterno()
							+ " "
							+ rcmEntity.getOas().get(i).getCotizante()
									.getCotMaterno());
					oa.setTitular(persona);

					// agregamos la ordenVO a la lista
					ordenes.add(oa);
				}

				// Seteamos los pagos y ordenes al rcmVO
				rcmVO.setPago(pago);
				rcmVO.setOrdenes(ordenes);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rcmVO;
	}

}
