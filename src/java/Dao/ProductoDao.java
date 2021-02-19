package Dao;

import Modelo.Producto;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hernan
 */
public interface ProductoDao {
       public List<Producto> listarProductos();
    public void crearProducto(Producto producto);
    public void actualizarProducto(Producto producto);
    public void borrarProducto(Producto producto); 
    
    //MEtodo utilizado en la Factura
    public Producto obtenerProductoCodBarra(Session session, String codBarra )throws Exception;
}
