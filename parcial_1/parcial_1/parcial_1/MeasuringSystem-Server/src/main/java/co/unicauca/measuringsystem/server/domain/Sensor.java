/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.measuringsystem.server.domain;

import java.util.ArrayList;

/**
 *
 * @author ANDRE
 */
public class Sensor  {
   
	private Shooter disparador;
	
	
	
	
	
	public Sensor( Shooter d){
		this.disparador = d;
    }
    
    /**
     * Métodos
     */

	public Boolean medirDatos(String dato) {
	   if(!disparador.detectarEvento(dato))
		   return true;
	   return false;
	   
	}

	
}