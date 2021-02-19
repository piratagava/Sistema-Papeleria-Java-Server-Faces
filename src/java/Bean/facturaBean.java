package Bean;

import Dao.DetalleFacturaDao;
import Dao.FacturaDao;
import Dao.ProductoDao;
import Dao.detalleFacturaDaoImp;
import Dao.facturaDaoImp;
import Dao.productoDaoImp;
import Modelo.Detallefactura;
import Modelo.Factura;
import Modelo.Producto;
import Modelo.Vendedor;
import Persistencia.NewHibernateUtil;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Hernan
 */
@ManagedBean
@ViewScoped
public class facturaBean {

  Session session = null;
  Transaction transaction = null;

  @ManagedProperty("#{loginBean}")
  private loginBean lBean;

  private Producto producto;
  private String codigoBarra;

  private List<Detallefactura> listaDetalleFactura;

  private String cantidaProducto;
  private String productoSeleccionado;
  private Factura factura;

  private String cantidadProducto2;

  private Long numFactura;

  private BigDecimal totalventaFactura;

  private Vendedor vendedor;

  private List<Factura> listarfactura;

  public facturaBean() {
    this.factura = new Factura();
    this.listaDetalleFactura = new ArrayList<>();
    this.vendedor = new Vendedor();
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
   * @return the codigoBarra
   */
  public String getCodigoBarra() {
    return codigoBarra;
  }

  /**
   * @param codigoBarra the codigoBarra to set
   */
  public void setCodigoBarra(String codigoBarra) {
    this.codigoBarra = codigoBarra;
  }

  /**
   * @return the listaDetalleFactura
   */
  public List<Detallefactura> getListaDetalleFactura() {
    return listaDetalleFactura;
  }

  /**
   * @param listaDetalleFactura the listaDetalleFactura to set
   */
  public void setListaDetalleFactura(List<Detallefactura> listaDetalleFactura) {
    this.listaDetalleFactura = listaDetalleFactura;
  }

  /**
   * @return the cantidaProducto
   */
  public String getCantidaProducto() {
    return cantidaProducto;
  }

  /**
   * @param cantidaProducto the cantidaProducto to set
   */
  public void setCantidaProducto(String cantidaProducto) {
    this.cantidaProducto = cantidaProducto;
  }

  /**
   * @return the productoSeleccionado
   */
  public String getProductoSeleccionado() {
    return productoSeleccionado;
  }

  /**
   * @param productoSeleccionado the productoSeleccionado to set
   */
  public void setProductoSeleccionado(String productoSeleccionado) {
    this.productoSeleccionado = productoSeleccionado;
  }

  /**
   * @return the factura
   */
  public Factura getFactura() {
    return factura;
  }

  /**
   * @param factura the factura to set
   */
  public void setFactura(Factura factura) {
    this.factura = factura;
  }

  /**
   * @return the cantidadProducto2
   */
  public String getCantidadProducto2() {
    return cantidadProducto2;
  }

  /**
   * @param cantidadProducto2 the cantidadProducto2 to set
   */
  public void setCantidadProducto2(String cantidadProducto2) {
    this.cantidadProducto2 = cantidadProducto2;
  }

  /**
   * @return the numFactura
   */
  public Long getNumFactura() {
    return numFactura;
  }

  /**
   * @param numFactura the numFactura to set
   */
  public void setNumFactura(Long numFactura) {
    this.numFactura = numFactura;
  }

  /**
   * @return the totalventaFactura
   */
  public BigDecimal getTotalventaFactura() {
    return totalventaFactura;
  }

  /**
   * @param totalventaFactura the totalventaFactura to set
   */
  public void setTotalventaFactura(BigDecimal totalventaFactura) {
    this.totalventaFactura = totalventaFactura;
  }

  /**
   * @return the vendedor
   */
  public Vendedor getVendedor() {
    return vendedor;
  }

  /**
   * @param vendedor the vendedor to set
   */
  public void setVendedor(Vendedor vendedor) {
    this.vendedor = vendedor;
  }

  public loginBean getlBean() {
    return lBean;
  }

  public void setlBean(loginBean lBean) {
    this.lBean = lBean;
  }

  public List<Factura> getListarfactura() {
    FacturaDao fDao = new facturaDaoImp();
    listarfactura = fDao.listarFactura();
    return listarfactura;
  }

  public void setListarfactura(List<Factura> listarfactura) {
    this.listarfactura = listarfactura;
  }

  /*
  //Metodo para agregar los datos de los Clientes por dialogClientes
  public void agregarDatosCliente(Integer codCliente) {
    this.session = null;
    this.transaction = null;
    try {
      this.session = HibernateUtil.getSessionFactory().openSession();
      clienteDao cDao = new clienteDaoImp();
      this.transaction = this.session.beginTransaction();

      //Obtener los datos de cliente en la variable objeto client, segun el codigo cliente            
      this.cliente = cDao.obtenerClientePorCodigo(this.session, codCliente);
      this.transaction.commit();

      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del Cliente agregado"));
    } catch (Exception e) {
      if (this.transaction != null) {
        System.out.println(e.getMessage());
        transaction.rollback();
      }
    } finally {
      if (this.session != null) {
        this.session.close();
      }
    }
  }

  //Metodo para agregar los datos de los Clientes Buscados por CodCliente Cajita de Texto
  public void agregarDatosCliente2() {
    this.session = null;
    this.transaction = null;
    try {
      if (this.codigoCliente == null) {
        return;
      }

      this.session = HibernateUtil.getSessionFactory().openSession();
      clienteDao cDao = new clienteDaoImp();
      this.transaction = this.session.beginTransaction();

      //Obtener los datos de cliente en la variable objeto client, segun el codigo cliente            
      this.cliente = cDao.obtenerClientePorCodigo(this.session, this.codigoCliente);

      if (this.cliente != null) {
        this.codigoCliente = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del Cliente agregado"));
      } else {
        this.codigoCliente = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cliente No Existe"));
      }

      this.transaction.commit();
    } catch (Exception e) {
      if (this.transaction != null) {
        System.out.println(e.getMessage());
        transaction.rollback();
      }
    } finally {
      if (this.session != null) {
        this.session.close();
      }
    }
  }
   */
  //Metodo Para Solicitar la Cantidad del producto a Vender
  public void pedirCantidadProducto(String codBarra) {
    this.productoSeleccionado = codBarra;
  }

  //Metodo para agregar los datos del Producto por dialogProductos
  public void agregarDatosProducto() {
    this.session = null;
    this.transaction = null;
    try {

      if (!(this.cantidaProducto.matches("[0-9]*")) || this.cantidaProducto.equals("0") || this.cantidaProducto.equals("")) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Datos Incorrectos Verifique Nuevamente"));
        this.cantidaProducto = "";//limpiar Valores de cajita emergente
      } else {
        this.session = NewHibernateUtil.getSessionFactory().openSession();
        ProductoDao pDao = new productoDaoImp();
        this.transaction = this.session.beginTransaction();

        //Obtener los datos del Producto en la variable objeto producto, segun el codigo Barra
        this.producto = pDao.obtenerProductoCodBarra(this.session, this.productoSeleccionado);

        if (producto.getStockActual() <= 0) {
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                  "Error", "Sin Existencia Actualize StockMaximo del Producto"));
        } else {
          //Paso productos con Array list los atributos que deseo ver en la tabla view ventas
          this.getListaDetalleFactura().add(new Detallefactura(null, null, this.producto.getCodBarra(),
                  this.producto.getNombreProducto(), Integer.parseInt(this.cantidaProducto), this.producto.getPrecioVenta(),
                  BigDecimal.valueOf(Integer.parseInt(this.cantidaProducto) * this.producto.getPrecioVenta().floatValue())));
          this.transaction.commit();

          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto agregado al Detalle"));

          this.cantidaProducto = "";

          //llamo metodo del Calcular total Factura Venta
          this.totalFacturaVenta();
        }
      }
    } catch (Exception e) {
      if (this.transaction != null) {
        System.out.println(e.getMessage());
        transaction.rollback();
      }
    } finally {
      if (this.session != null) {
        this.session.close();
      }
    }
  }

  //Metodo mostrar dialog producto2
  public void mostrarCantidadProducto2() {
    this.session = null;
    this.transaction = null;
    try {
      if (this.codigoBarra.equals("")) {
        return;
      }

      this.session = NewHibernateUtil.getSessionFactory().openSession();
      ProductoDao pDao = new productoDaoImp();
      this.transaction = this.session.beginTransaction();

      //Obtener los datos del Producto en la variable objeto producto, segun el codigo Barra
      this.producto = pDao.obtenerProductoCodBarra(this.session, codigoBarra);

      if (this.producto != null) {
        //Solicitar mostrar el dialog CantidadProducto2
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialogoCantidadProducto2').show();");

        this.codigoBarra = null;

      } else {
        this.codigoBarra = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Producto No Existe"));
      }

      this.transaction.commit();
    } catch (Exception e) {
      if (this.transaction != null) {
        System.out.println(e.getMessage());
        transaction.rollback();
      }
    } finally {
      if (this.session != null) {
        this.session.close();
      }
    }
  }

  //Metodo para agregar los datos de los Clientes Buscados por CodBarra Cajita de Texto
  public void agregarDatosProductos2() {
    if (!(this.cantidadProducto2.matches("[0-9]*")) || this.cantidadProducto2.equals("0") || this.cantidadProducto2.equals("")) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Datos Incorrectos Verifique Nuevamente"));
      this.cantidadProducto2 = "";//limpiar Valores de cajita emergente
    } else {
      //Paso productos con Array list los atributos que deseo ver en la tabla view ventas
      this.getListaDetalleFactura().add(new Detallefactura(null, null, this.producto.getCodBarra(), this.producto.getNombreProducto(),
              Integer.parseInt(this.cantidadProducto2), this.producto.getPrecioVenta(),
              BigDecimal.valueOf(Integer.parseInt(this.cantidadProducto2) * this.producto.getPrecioVenta().floatValue())));
      this.cantidadProducto2 = "";
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto Agregado al Detalle"));
      //LLamando al metodo  totalFacturaVenta

      this.totalFacturaVenta();
    }

  }

  //Metodo Para Calcular el total a Vender en la factura
  public void totalFacturaVenta() {
    this.totalventaFactura = new BigDecimal("0");
    try {
      for (Detallefactura item : listaDetalleFactura) {
        BigDecimal totalVentaPorProducto = item.getPrecioVenta().multiply(new BigDecimal(item.getCantidad()));
        item.setTotal(totalVentaPorProducto);
        totalventaFactura = totalventaFactura.add(totalVentaPorProducto);
      }
      this.factura.setTotalVenta(totalventaFactura);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  //Metodo para Quitar producto de la factura
  public void quitarProductoDetalleFactura(String codBarra, Integer filaSeleccionada) {
    try {
      int i = 0;
      for (Detallefactura item : this.listaDetalleFactura) {
        if (item.getCodBarra().equals(codBarra) && filaSeleccionada.equals(i)) {
          this.listaDetalleFactura.remove(i);
          break;
        }
        i++;
      }
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informacion", "Se Retiro el producto de la factura"));
      //invocamos metodo para actualizar el total a vender despues de haber quitado un producto del detalleFactura
      this.totalFacturaVenta();
    } catch (Exception e) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se Pudo Retirar el Producto"));
    }
  }

  //Metodo Para Editar Cantidad Producto en la tablaFactura
  public void onRowEdit(RowEditEvent event) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Se Modifico la Cantidad"));
    //llamo el metodo para refrescar totales cuando realize el editrow
    this.totalFacturaVenta();
  }

  public void onRowCancel(RowEditEvent event) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "No se Modifico la Cantidad"));
    //llamo el metodo para refrescar totales cuando realize el onRowCancel 
    this.totalFacturaVenta();
  }

  //Metodo para generar el numero de factura
  public void numeracionFactura() {
    this.session = null;
    this.transaction = null;
    try {
      this.session = NewHibernateUtil.getSessionFactory().openSession();
      this.transaction = this.session.beginTransaction();
      FacturaDao fDao = new facturaDaoImp();

      //Verificar si hay registros en la tabla factura de la bd
      this.numFactura = fDao.obtenerTotalRegistrosEnFactura(this.session);

      if (this.numFactura <= 0 || this.numFactura == null) {
        this.numFactura = Long.valueOf("1");
      } else {
        //recuperamos en ultimo registro en la tabla factura en la bd
        this.factura = fDao.obtenerUltimoRegistro(this.session);
        this.numFactura = Long.valueOf(this.factura.getNumeroFactura() + 1);

        //Limpiar variabl total venta factura
        this.totalventaFactura = new BigDecimal("0");
      }

      this.transaction.commit();
    } catch (Exception e) {
      if (this.transaction != null) {
        this.transaction.rollback();
      }
      System.out.println(e.getMessage());
    } finally {
      if (this.session != null) {
        this.session.close();
      }
    }
  }

  //Limpiar Factura
  public void limpiarFactura() {
    this.factura = new Factura();
    this.listaDetalleFactura = new ArrayList<>();
    this.numFactura = null;
    this.totalventaFactura = null;

    //invocar metodo para volver a desabiltar los botones e input
    this.disableButton();
  }

  //Guardar Venta
  public void guardarVenta() {
    this.session = null;
    this.transaction = null;

    this.vendedor.setCodVendedor(lBean.getVendedor().getCodVendedor());
    try {
      this.session = NewHibernateUtil.getSessionFactory().openSession();
      ProductoDao pDao = new productoDaoImp();
      FacturaDao fDao = new facturaDaoImp();
      DetalleFacturaDao dFDao = new detalleFacturaDaoImp();
      this.transaction = this.session.beginTransaction();

      //datos para Guardar en la tabla factura en la base de datos
      this.factura.setNumeroFactura(this.numFactura.intValue());
      this.factura.setVendedor(this.vendedor);

      //Hacemos el insert en la tabla factura
      fDao.guardarVentaFactura(this.session, this.factura);
      //Recuperar el ultimo registro de la tabla factura
      this.factura = fDao.obtenerUltimoRegistro(this.session);

      //Recorremos el arraylist para guardar cada registro en la bd
      for (Detallefactura item : listaDetalleFactura) {
        this.producto = pDao.obtenerProductoCodBarra(this.session, item.getCodBarra());
        item.setFactura(this.factura);
        item.setProducto(this.producto);

        //Hacemos el insert en la tabla detallefactura de la bd
        dFDao.guardarVentaDetalleFactura(this.session, item);
      }

      if (producto.getStockActual() <= producto.getStockMinimo()) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                "AVISO", " ¡¡ Verifique Su Producto Estan por Agotarse ¡¡"));
        this.limpiarFactura();
      }

      this.transaction.commit();
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto",
              "Venta Registrada"));
      this.limpiarFactura();

    } catch (Exception e) {
      System.out.println(e.getMessage());
      if (this.transaction != null) {
        this.transaction.rollback();
      }
    } finally {
      if (this.session != null) {
        this.session.close();
      }
    }
  }

  //Metodo para activar y desactivar
  private boolean enabled;

  public boolean isEnabled() {
    return enabled;
  }

  public void enableButton() {
    enabled = true;
  }

  public void disableButton() {
    enabled = false;
  }

  //Recuperar fchas del sistema
  private String fechaSistema;

  public String getFechaSistema() {
    Calendar fecha = new GregorianCalendar();
    int anio = fecha.get(Calendar.YEAR);
    int mes = fecha.get(Calendar.MONTH);
    int dia = fecha.get(Calendar.DAY_OF_MONTH);
    
    int hora= fecha.get(Calendar.HOUR_OF_DAY);
    int min= fecha.get(Calendar.MINUTE);
    int segundo= fecha.get(Calendar.SECOND);
    
    this.fechaSistema = (dia + "/" + (mes + 1) + "/" + anio);    
            
    return fechaSistema;
  }

  public void eliminarFactura() {
    FacturaDao fDao = new facturaDaoImp();
    fDao.eliminarFactura(factura);
    factura = new Factura();
  }

}
