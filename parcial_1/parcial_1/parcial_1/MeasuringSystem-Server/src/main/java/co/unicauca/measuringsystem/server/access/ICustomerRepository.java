/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.measuringsystem.server.access;
import co.unicauca.measuringsystem.commons.domain.Product;
/**
 *
 * @author ANDRE
 */
public interface ICustomerRepository {
    
    public Product obtenerItem(String referencia);
    public String enviarAviso(String aviso);
    
}
