package Bean;
import Dao.ProductoDao;
import Dao.productoDaoImp;
import Modelo.Producto;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
/**
 *
 * @author Hernan
 */
@ManagedBean
@ViewScoped
public class productoBean {

  private List<Producto> listaProducto;
  private Producto producto;

  public productoBean() {
    producto = new Producto();
  }

  /**
   * @param listaProducto the listaProducto to set
   */
  public void setListaProducto(List<Producto> listaProducto) {
    this.listaProducto = listaProducto;
  }

  /**
   * @return the producto
   */
  public Producto getProducto() {
    return producto;
  }

  /**
   * @param producto the producto to set
   */
  public void setProducto(Producto producto) {
    this.producto = producto;
  }

  /**
   * @return the listaProducto
   */
  public List<Producto> getListaProducto() {
    ProductoDao pDao = new productoDaoImp();
    listaProducto = pDao.listarProductos();
    return listaProducto;
  }

  public void PreparaNuevoProducto() {
    producto = new Producto();
  }

  public void insertar() {
    ProductoDao pDao = new productoDaoImp();
    pDao.crearProducto(producto);
    producto = new Producto();
  }

  public void actualizar() {
    ProductoDao pDao = new productoDaoImp();
    pDao.actualizarProducto(producto);
    producto = new Producto();
  }

  public void borrar() {
    ProductoDao pDao = new productoDaoImp();
    pDao.borrarProducto(producto);
    producto = new Producto();
  }
}
