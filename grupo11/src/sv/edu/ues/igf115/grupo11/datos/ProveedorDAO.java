package sv.edu.ues.igf115.grupo11.datos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import sv.edu.ues.igf115.grupo11.dominio.Proveedor;
import sv.edu.ues.igf115.grupo11.utilidades.HibernateUtil;

public class ProveedorDAO {
	private HibernateUtil hibernateUtil = new HibernateUtil();
	private SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
	private Session sesion;
	private Transaction tx;

	private void iniciaOperacion() throws HibernateException {
		sesion = sessionFactory.openSession();
		tx = sesion.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he)
			throws HibernateException {
		tx.rollback();
		throw new HibernateException("Ocurrió un error en la capa DAO", he);
	}

	public void guardaActualiza(Proveedor proveedor) {
		try {
			iniciaOperacion();
			sesion.saveOrUpdate(proveedor);
			tx.commit();
			sesion.flush();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}

	public void eliminar(Proveedor proveedor) {
		try {
			iniciaOperacion();
			sesion.delete(proveedor);
			tx.commit();
			sesion.flush();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}

	public Proveedor daProveedorById(String id){
		sesion = sessionFactory.openSession() ;
		// Retorna la instancia persistente de la clase por medio del atributo identidad
		Proveedor idpro = (Proveedor) sesion.get(Proveedor.class,new String(id)) ;
		sesion.close() ;
		return idpro ;
		}
	public Proveedor daProveedorByIdPro(String id) {
		sesion = sessionFactory.openSession();
		Query query = sesion.getNamedQuery("Proveedores.findByIdPro");
		query.setParameter("idProveedor", id);
		Proveedor proveedor = (Proveedor) query.uniqueResult();
		sesion.close();
		return proveedor;
	}
	public List<Proveedor> daProveedores() {
		sesion = sessionFactory.openSession();
		Query query = sesion.getNamedQuery("Proveedores.findAll");
		List<Proveedor> proveedores = query.list();
		sesion.close();
		return proveedores;
	}

	public Proveedor daProveedorByNombre(String nombreProveedor) {
		sesion = sessionFactory.openSession();
		Query query = sesion.getNamedQuery("Proveedores.findByNombre");
		query.setParameter("nombreProveedor", nombreProveedor);
		Proveedor proveedor = (Proveedor) query.uniqueResult();
		sesion.close();
		return proveedor;
	}
}
