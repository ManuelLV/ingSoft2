/* Fabrica que se encarga de instanciar CustomerServiceImplSockets o cualquier
 * otro que se cree en el futuro.
 */ 
package co.unicauca.measuringsystem.client.access;
import co.unicauca.measuringsystem.commons.infra.Utilidades;
/**
 *
 * @author ANDRE
 */
public class Factory {
    private static Factory instance;
//constructor
    public Factory() {
    }

    public static Factory getInstance() {
           if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }
     /**
      Método que crea una instancia concreta de la jerarquia ICustomerService
     *
     * @return una clase hija de la abstracción IRepositorioClientes
     */
     public IMeasuringSystem item(){
         
         IMeasuringSystem objResultado = null; 
         String tipo = Utilidades.loadProperty("customer.service");
              
         switch (tipo){
            case "default":
                objResultado = new CustomerMeasuringSystemImplSocket();
                break;
        }
        return objResultado; 
   
     }
    
}
