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

import cl.masvida.poc.entities.Oa;
import cl.masvida.poc.entities.Rcm;

import com.redhat.masvida.vo.AgenciaVO;
import com.redhat.masvida.vo.CobradorVO;
import com.redhat.masvida.vo.CotizanteVO;
import com.redhat.masvida.vo.EstadoOaVO;
import com.redhat.masvida.vo.OaVO;
import com.redhat.masvida.vo.OrdenAtencionVO;
import com.redhat.masvida.vo.PagoVO;
import com.redhat.masvida.vo.PersonaVO;
import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.RecepcionCobranzaMedicaVO;
import com.redhat.masvida.vo.TipoPagoVO;

@Stateless
public class OaDAO implements OaDAOLocal {

	@PersistenceContext(unitName = "masvida-ejb-jpa")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public OaDAO() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Método para buscar un Orden de Atención vía ID.
	 */
	public OaVO buscarOa(BigDecimal id) {
		OaVO oaVO = null;
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
					System.out.println("Buscando OA por ID....");
					// Cambiando tipo de la Query.
					org.hibernate.Query query = session
							.createQuery("FROM Oa WHERE (odaFolio=:folio) AND (rcmFolio is null)");
					query.setParameter("folio", id);
					@SuppressWarnings("unchecked")
					List<Oa> list = query.list();
					tx.commit();
                    
					// Si se encontro un registro en la BD...
					if (list.size() > 0){
						oaVO = getOaEntityToVO(list.get(0));
					}

				} catch (Exception e) {
					if (tx != null)
						tx.rollback();
					e.printStackTrace();
				} finally {
					session.close();
				}
		return oaVO;

	}

	private OaVO getOaEntityToVO(Oa oa) {

		OaVO oaVO = null;

		try {

			if (oa != null) {

				oaVO = new OaVO();
                oaVO.setOdaFolio(oa.getOdaFolio());
                oaVO.setBonificacion(oa.getBonificacion());
                oaVO.setCopago(oa.getCopago());
                CotizanteVO cotVO = new CotizanteVO();
                cotVO.setCotRut(oa.getCotizante().getCotRut());
                cotVO.setCotMaterno(oa.getCotizante().getCotMaterno());
                cotVO.setCotNombres(oa.getCotizante().getCotNombres());
                cotVO.setCotPaterno(oa.getCotizante().getCotPaterno());
                oaVO.setCotizante(cotVO);
                oaVO.setEoaCodigo(oa.getEoaCodigo());
                EstadoOaVO eoaVO = new EstadoOaVO();
                eoaVO.setEoaCodigo(oa.getEoaCodigo());
                eoaVO.setEoaDescripcion(oa.getEstadoOa().getEoaDescripcion());
                oaVO.setEstadoOa(eoaVO);
                oaVO.setOdaFechaemi(oa.getOdaFechaemi());
                oaVO.setRcmFolio(oa.getRcmFolio());
                oaVO.setTitRut(oa.getTitRut());
                oaVO.setValor(oa.getValor());
                
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oaVO;
	}

}
