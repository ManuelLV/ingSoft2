/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.measuringsystem.server.domain;
import co.unicauca.measuringsystem.server.access.CustomerRepositoryImplArrays;
import co.unicauca.measuringsystem.server.access.ICustomerRepository;
import co.unicauca.measuringsystem.commons.infra.Utilidades;

/**
 *
 * @author ANDRE
 */
public class FactoryMeasuringItem {

private static FactoryMeasuringItem  instance;

    public FactoryMeasuringItem() {
    }
//si la fabrica no esta creada creela.
    public static FactoryMeasuringItem getInstance() {
           if (instance == null) {
               instance= new FactoryMeasuringItem();
           }
           
        return instance;
    }
  public void createItem(){
        
    }
    public ICustomerRepository repositorio(){
        String type = Utilidades.loadProperty("cliente.repositorio");
        if (type.isEmpty()) {
            type = "default";
        }
         ICustomerRepository objObtener=null; 
         switch (type) {
            case "default":
                objObtener = new CustomerRepositoryImplArrays();
                break;
         }      
          return objObtener;              
        
    }
}
