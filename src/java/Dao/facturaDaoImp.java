package Dao;

import Modelo.Factura;
import Persistencia.NewHibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hernan
 */
public class facturaDaoImp implements FacturaDao {

  @Override
  public Factura obtenerUltimoRegistro(Session session) throws Exception {
    String hql = "FROM Factura ORDER BY codFactura DESC";
    Query q = session.createQuery(hql).setMaxResults(1);

    return (Factura) q.uniqueResult();
  }

  @Override
  public Long obtenerTotalRegistrosEnFactura(Session session) {
    String hql = "SELECT COUNT(*) FROM Factura";
    Query q = session.createQuery(hql);
    return (Long) q.uniqueResult();
  }

  @Override
  public boolean guardarVentaFactura(Session session, Factura factura) throws Exception {
    session.save(factura);
    return true;
  }

  @Override
  public List<Factura> listarFactura() {
    Session session = null;
    List<Factura> lista = null;
    try {
      session = NewHibernateUtil.getSessionFactory().openSession();
      Query query = session.createQuery("From Factura");
      lista = (List<Factura>) query.list();
      for (Factura data : lista) {
        //Para mostrar en la lista Carreraygrupo tengo que recorrer con un for
        System.out.println(data.getVendedor().getNombres());
        //Para mostrar en la lista Carreraygrupo tengo que recorrer con un for
        System.out.println(data.getVendedor().getApellidos());
        //Para mostrar en la lista Carreraygrupo tengo que recorrer con un for
        System.out.println(data.getCodFactura());
        //Para mostrar en la lista Carreraygrupo tengo que recorrer con un for
        System.out.println(data.getNumeroFactura());
        //Para mostrar en la lista Carreraygrupo tengo que recorrer con un for
        System.out.println(data.getTotalVenta());
        //Para mostrar en la lista Carreraygrupo tengo que recorrer con un for
        System.out.println(data.getFechaRegistro());
      }
      session.getTransaction().commit();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return lista;
  }

  @Override
  public void eliminarFactura(Factura factura) {
    Session session = null;
    try {
      session = NewHibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.delete(factura);
      session.getTransaction().commit();
    } catch (HibernateException e) {
      System.out.println(e.getMessage());
      session.getTransaction().rollback();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }
}
