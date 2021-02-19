package Dao;

import Modelo.Detallefactura;
import Persistencia.NewHibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Anonymous
 */
public class detalleFacturaDaoImp implements DetalleFacturaDao{

    @Override
    public boolean guardarVentaDetalleFactura(Session session, Detallefactura detallefactura) throws Exception {
        session.save(detallefactura);
        return true;
    }

  @Override
  public List<Detallefactura> listarDetalle() {
      Session session = null;
    List<Detallefactura> lista = null;
    try {
      session = NewHibernateUtil.getSessionFactory().openSession();
      Query query = session.createQuery("From Detallefactura");
      lista = (List<Detallefactura>) query.list();
      for (Detallefactura data : lista) {
        //Para mostrar en la lista Carreraygrupo tengo que recorrer con un for
        System.out.println(data.getCantidad());
        //Para mostrar en la lista Carreraygrupo tengo que recorrer con un for
        System.out.println(data.getProducto().getNombreProducto());
        //Para mostrar en la lista Carreraygrupo tengo que recorrer con un for
        System.out.println(data.getProducto().getDescripcion());
        //Para mostrar en la lista Carreraygrupo tengo que recorrer con un for
        System.out.println(data.getCodBarra());
        System.out.println(data.getNombreProducto());
        System.out.println(data.getPrecioVenta());
        System.out.println(data.getTotal());
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
  public void eliminarDetalle(Detallefactura detallefactura) {
    Session session = null;
    try {
      session = NewHibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.delete(detallefactura);
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
