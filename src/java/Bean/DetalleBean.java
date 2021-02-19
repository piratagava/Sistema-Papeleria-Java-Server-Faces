/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;
import Dao.DetalleFacturaDao;
import Dao.detalleFacturaDaoImp;
import Modelo.Detallefactura;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Hernan
 */

@ManagedBean
@ViewScoped
public class DetalleBean {
  /*dEtalle*/
  private List<Detallefactura> listadetalle;
  private Detallefactura detallefactura;
  
  public DetalleBean() {
   detallefactura= new Detallefactura();
  }
  
  public Detallefactura getDetallefactura() {
    return detallefactura;
  }

  public void setDetallefactura(Detallefactura detallefactura) {
    this.detallefactura = detallefactura;
  }
  
  public List<Detallefactura> getListadetalle() {
    DetalleFacturaDao dDao= new detalleFacturaDaoImp();
    listadetalle=dDao.listarDetalle();
    return listadetalle;
  }

  public void setListadetalle(List<Detallefactura> listadetalle) {
    this.listadetalle = listadetalle;
  }
  
  public void eliminardetalle() {
    DetalleFacturaDao dDao= new detalleFacturaDaoImp();
    dDao.eliminarDetalle(detallefactura);
    detallefactura= new Detallefactura();
  } 
}
