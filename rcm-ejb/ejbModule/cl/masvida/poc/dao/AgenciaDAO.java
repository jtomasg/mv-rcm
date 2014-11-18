package cl.masvida.poc.dao;

import java.math.BigDecimal;
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

import cl.masvida.poc.entities.Agencia;
import cl.masvida.poc.entities.TipoPagoDoc;

import com.redhat.masvida.vo.AgenciaVO;
import com.redhat.masvida.vo.TipoPagoVO;

@Stateless
public class AgenciaDAO implements AgenciaDAOLocal {

	@PersistenceContext(unitName = "masvida-ejb-jpa")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public AgenciaDAO() {
		// TODO Auto-generated constructor stub
	}

	public List<AgenciaVO> buscarAgencias() {
		// Creamos nuestra lista vacía
		List<AgenciaVO> agencias = new ArrayList<AgenciaVO>();

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
		// Comienza la Transacción
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// Cambiando tipo de la Query.
			org.hibernate.Query query = session
					.createQuery("SELECT agencia FROM Agencia agencia");
			@SuppressWarnings("unchecked")
			List<Agencia> list = query.list();
			tx.commit();

			// Volcamos cada entidad en la lista de VO
			for (int i = 0; i < list.size() && list != null; i++) {
				AgenciaVO vo = new AgenciaVO();
				vo.setId(list.get(i).getAgeCodigo().intValue());
				vo.setDescripcion(list.get(i).getAgeNombre());
				agencias.add(vo);
			}

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return agencias;
	}

	
	public String buscarAgencia(BigDecimal i) {
		AgenciaVO ageVO = null;
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
					System.out.println("Buscando Agencia por Código....");
					// Cambiando tipo de la Query.
					org.hibernate.Query query = session
							.createQuery("FROM Agencia WHERE ageCodigo=:agecodigo");
					query.setParameter("agecodigo", i);
					@SuppressWarnings("unchecked")
					List<Agencia> list = query.list();
					tx.commit();
                    
					// Si se encontro un registro en la BD...
					if (list.size() > 0){
						ageVO = new AgenciaVO();
						ageVO.setId(list.get(0).getAgeCodigo().intValue());
						ageVO.setDescripcion(list.get(0).getAgeNombre());
					}

				} catch (Exception e) {
					if (tx != null)
						tx.rollback();
					e.printStackTrace();
				} finally {
					session.close();
				}
		return ageVO.getDescripcion();
	}

}
