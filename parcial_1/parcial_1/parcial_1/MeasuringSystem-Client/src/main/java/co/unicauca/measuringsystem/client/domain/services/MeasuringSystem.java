package co.unicauca.measuringsystem.client.domain.services;

import co.unicauca.measuringsystem.client.access.IMeasuringSystem;
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
public class MeasuringSystem {
    private final IMeasuringSystem service;
    
    public String enviarAviso(String aviso) throws Exception{
     return service.enviarAviso(aviso);
    }

    public MeasuringSystem(IMeasuringSystem service) {
        this.service = service;
    }
    
    public Product Item(String referencia)throws Exception {
        return  service.item(referencia);
    }
    
    
    
}
