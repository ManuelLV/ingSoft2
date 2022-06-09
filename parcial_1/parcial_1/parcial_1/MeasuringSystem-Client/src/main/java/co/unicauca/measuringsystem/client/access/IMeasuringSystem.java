package co.unicauca.measuringsystem.client.access;
import co.unicauca.measuringsystem.commons.domain.Product;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ANDRE
 */
public interface IMeasuringSystem {
     public String enviarAviso(String aviso) throws Exception;
     public Product item(String referencia);
    
}
