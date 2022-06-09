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
public class ProductItemMeasuring implements IMeasuringItem {
    
     /**
     * Atributos 
     */
    private Actuator actuador;
    private Sensor sensor;

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Actuator getActuador() {
        return actuador;
    }

    public void setActuador(Actuator actuador) {
        this.actuador = actuador;
    }
   //Métodos
    @Override
     public void procesarMedida(){
         
     }

    @Override
    public void leerSensor() {
        
    }
   
    @Override
    public void almacenarResultados() {
        
    }
    
    @Override
    public void verificarValor(){
        
        
    }     
    
    @Override
    public void ejecutaEvento(){
        
    }
           
}
