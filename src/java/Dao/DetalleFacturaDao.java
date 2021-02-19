/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Detallefactura;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hernan
 */
public interface DetalleFacturaDao {
    //Guardar registro de la factura campos
    public boolean guardarVentaDetalleFactura(Session session,Detallefactura detallefactura) throws Exception;
    
    public List<Detallefactura> listarDetalle();
    
    public void eliminarDetalle(Detallefactura detallefactura);
    
}
