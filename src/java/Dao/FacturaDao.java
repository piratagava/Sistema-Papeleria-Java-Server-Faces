/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import org.hibernate.Session;
import Modelo.Factura;
import java.util.List;
/**
 *
 * @author Hernan
 */
public interface FacturaDao {
  public Factura obtenerUltimoRegistro(Session session) throws  Exception;
    //Averiguar si la tabla factura tiene registros
    public Long obtenerTotalRegistrosEnFactura(Session session);
    
    //Guardar registro de la factura campos
    public boolean guardarVentaFactura(Session session,Factura factura) throws Exception;
    
    
    public List<Factura> listarFactura();
    
    public void eliminarFactura(Factura factura);
}
