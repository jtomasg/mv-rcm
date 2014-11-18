package cl.masvida.poc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

import cl.masvida.poc.entities.TipoPagoDoc;
import com.redhat.masvida.vo.TipoPagoVO;

@Stateless
public class TipoPagoDAO implements TipoPagoDAOLocal {

	@PersistenceContext(unitName = "masvida-ejb-jpa")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public TipoPagoDAO() {
		// TODO Auto-generated constructor stub
	}

	public List<TipoPagoVO> buscarTipoPagos() {
		// Creamos nuestra lista vacía
		List<TipoPagoVO> tipopagos = new ArrayList<TipoPagoVO>();
		// Obtendremos la lista de Entidades inicialmente para Transformarlas en
		// VO

		// NO LOCK Sim.
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

		// Comienza la transacción...
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// Cambiando tipo de la Query.
			org.hibernate.Query query = session
					.createQuery("SELECT tpd FROM TipoPagoDoc tpd");
			@SuppressWarnings("unchecked")
			List<TipoPagoDoc> list = query.list();
			tx.commit();

			// Volcamos cada entidad en la lista de VO
			for (int i = 0; i < list.size() && list != null; i++) {
				TipoPagoVO tp = new TipoPagoVO();
				tp.setId(list.get(i).getTpdCodigo().intValue());
				tp.setNombre(list.get(i).getTpdDescripcion());
				tipopagos.add(tp);
			}

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return tipopagos;
	}

}
