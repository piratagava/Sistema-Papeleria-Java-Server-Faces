package Dao;
import ClasesAuxiliares.EncriptarPassword;
import Modelo.Vendedor;
import Persistencia.NewHibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Hernan
 */
public class vendedorDaoImp implements VendedorDao {

  @Override
  public List<Vendedor> listarVendedor() {
    List<Vendedor> lista = null;
    Session session = NewHibernateUtil.getSessionFactory().openSession();
    Transaction t = session.beginTransaction();
    String hql = "FROM Vendedor";

    try {
      lista = session.createQuery(hql).list();
      t.commit();
      session.close();
    } catch (Exception e) {
      t.rollback();
    }
    return lista;
  }

  @Override
  public void crearVendedor(Vendedor vendedor) {
    Session session = null;
    try {
      session = NewHibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.save(vendedor);
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

  @Override
  public void actualizarVendedor(Vendedor vendedor) {
    Session session = null;
    try {
      session = NewHibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.update(vendedor);
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

  @Override
  public void borrarVendedor(Vendedor vendedor) {
    Session session = null;
    try {
      session = NewHibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.delete(vendedor);
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

  @Override
  public Vendedor obtenerDatosPorUsuario(Vendedor vendedor) {
    Session session = NewHibernateUtil.getSessionFactory().openSession();
    String hql = "FROM Vendedor WHERE nombres=:nombres";
    Query q = session.createQuery(hql);
    q.setParameter("nombres", vendedor.getNombres());
    return (Vendedor) q.uniqueResult();
  }

  @Override
  public Vendedor login(Vendedor vendedor) {
    Vendedor user = this.obtenerDatosPorUsuario(vendedor);
    if (user != null) {      
      if (!user.getPasswd().equals(EncriptarPassword.sha512(vendedor.getPasswd()))) {
        user = null;
      }
    }
    return user;
  }
}
