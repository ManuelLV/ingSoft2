/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.measuringsystem.commons.domain;

/**
 *
 * @author ANDRE
 */
public class Product {
      /**
     * Atributos 
     */
    private String nombre; 
    private String estado; 
    private String referencia;
        /**
     * Constructor 
     */
     public Product(){ }
    public Product(String nombre,String estado,String referencia){
        this.nombre =nombre;
        this.estado=estado;
        this.referencia =referencia;        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
