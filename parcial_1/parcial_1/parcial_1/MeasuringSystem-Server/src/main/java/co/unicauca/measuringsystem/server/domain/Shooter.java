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
public class Shooter {
    
	
	Lector lector;
	
	
	
	
	public Shooter(Lector pLector){
        this.lector =  pLector;
    }
    /**
     * Métodos 
     */
    public Boolean detectarEvento(String evento){
        if(!lector.getEvento().contains(evento))
        	return false;
        return true;
    }	
}
