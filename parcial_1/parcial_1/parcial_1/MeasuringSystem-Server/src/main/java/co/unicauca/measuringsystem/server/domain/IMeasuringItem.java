/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.measuringsystem.server.domain;

/**
 *
 * @author ANDRE
 */
public interface IMeasuringItem {
            
    /**
     * Métodos 
     */
    public void procesarMedida();
    public void leerSensor()     ; 
   
    public void almacenarResultados()    ;   
    
    public void verificarValor();      
    
    public void ejecutaEvento();
    
}
