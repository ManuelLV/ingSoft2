/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.measuringsystem.server.domain.services;


//librerias del lenguaje
import java.util.ArrayList;
import java.util.List;

import co.unicauca.measuringsystem.commons.domain.Product;
import co.unicauca.measuringsystem.server.domain.ProductItemMeasuring;
import co.unicauca.measuringsystem.server.access.ICustomerRepository;

/**
 *
 * @author ANDRE
 */
public class CustomerService {
    ICustomerRepository objCustomerR;

    public CustomerService(ICustomerRepository objCustomerR) {
        this.objCustomerR = objCustomerR;
    }
    
    public Product item(String referencia){
        return objCustomerR.obtenerItem(referencia);
    }
    public String enviarAviso(String aviso){
        return objCustomerR.enviarAviso(aviso);
    }
    
    
}
