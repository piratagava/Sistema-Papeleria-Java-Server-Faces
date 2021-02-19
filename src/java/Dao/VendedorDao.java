/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Vendedor;
import java.util.List;

/**
 *
 * @author Hernan
 */
public interface VendedorDao {
 public List<Vendedor> listarVendedor();
    public void crearVendedor(Vendedor vendedor);
    public void actualizarVendedor(Vendedor vendedor);
    public void borrarVendedor(Vendedor vendedor);        
    //public Cliente obtenerClientePorCodigo(Session session, Integer codCliente) throws Exception;
    public Vendedor obtenerDatosPorUsuario(Vendedor vendedor);
    public Vendedor login(Vendedor vendedor); 
}
