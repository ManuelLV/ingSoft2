
package co.unicauca.measuringsystem.server.access;
import co.unicauca.measuringsystem.commons.domain.Product;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ANDRE
 */
public class CustomerRepositoryImplArrays implements ICustomerRepository{
        private static List<Product>  producto;

    public CustomerRepositoryImplArrays() {
        if (producto == null){
            producto = new ArrayList();
        }
        
        if (producto.isEmpty()){
            inicializar();
        }
    }
    public void inicializar() {
        producto.add(new Product("Temperatura","Aprobado",""));
        producto.add(new Product("Peso","Aprobado",""));
        producto.add(new Product("Color","Aprobado",""));
        producto.add(new Product("Textura","Aprobado",""));
          }

    @Override
    public String enviarAviso(String aviso) {
        if(aviso.equals("aviso")){
            String mensaje ="Aceptado";
            return mensaje ;
        }
        return "No es posible validar";
    }

    @Override
    public Product obtenerItem(String referencia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
            
}
