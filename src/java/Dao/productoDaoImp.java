/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Producto;
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
public class productoDaoImp implements ProductoDao{

    @Override
    public List<Producto> listarProductos() {
        List<Producto> lista= null;
        Session session= NewHibernateUtil.getSessionFactory().openSession();
        Transaction t= session.beginTransaction();
        String hql="FROM Producto";
        
        try {
            lista=session.createQuery(hql).list();
            t.commit();
            session.close();
        } catch (Exception e) {
            t.rollback();
        }
        return lista;
    }

    @Override
    public void crearProducto(Producto producto) {
        Session session=null;
        try {
            session=NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(producto);
            session.getTransaction().commit();            
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session !=null){
                session.close();
            }
        }
    }

    @Override
    public void actualizarProducto(Producto producto) {
        Session session=null;
        try {
            session=NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(producto);
            session.getTransaction().commit();            
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session !=null){
                session.close();
            }
        }
    }

    @Override
    public void borrarProducto(Producto producto) {
        Session session=null;
        try {
            session=NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(producto);
            session.getTransaction().commit();            
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session !=null){
                session.close();
            }
        }
    }

    @Override
    public Producto obtenerProductoCodBarra(Session session, String codBarra) throws Exception {
        String hql="FROM Producto WHERE codBarra=:codBarra";
        Query q= session.createQuery(hql);
        q.setParameter("codBarra", codBarra);        
        return (Producto) q.uniqueResult();
    }

}
