package co.unicauca.measuringsystem.client.access;

import co.unicauca.measuringsystem.client.infra.MeasuringSystemSocket;
import co.unicauca.measuringsystem.commons.infra.JsonError;
import co.unicauca.measuringsystem.commons.infra.Protocolo;
import co.unicauca.measuringsystem.commons.domain.Product;
//implementadono lib del lenguaje 
import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public  class CustomerMeasuringSystemImplSocket implements IMeasuringSystem{
    
    private MeasuringSystemSocket mySocket;

    public CustomerMeasuringSystemImplSocket() {
        mySocket = new MeasuringSystemSocket ();
    }
    
     @Override   
    public String enviarAviso(String aviso) throws Exception {
        
        String jsonResultado= null;
        String peticionJson = avisoPeticionJson(aviso);
        
        try {
            mySocket.connect();
            jsonResultado = mySocket.enviandoCadena(peticionJson);
            mySocket.cerrarCadena();
            mySocket.desconectar();

        } catch (IOException ex) {
            Logger.getLogger(CustomerMeasuringSystemImplSocket.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResultado == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {

            if (jsonResultado.contains("error")) {
                  
                Logger.getLogger(MeasuringSystemSocket.class.getName()).log(Level.INFO, jsonResultado);
                throw new Exception(extractMessages(jsonResultado));
            } else {
             
                return jsonResultado;
            }
        }
        
    }
    

    private String avisoPeticionJson(String aviso) {
        //{"recource":"customer", "action":"get", "parametrers":"[{"name": "id", "value": 9800001"},{}]"}
        Protocolo protocolo = new Protocolo();
        protocolo.setResource("peticion");
        protocolo.setAction(aviso);

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocolo);
        System.out.println("requestJson: " + requestJson);

        return requestJson;
    }

    private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msjs = "";
        for (JsonError error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }
    //lo volvemos un arreglo
    private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }

    @Override
    public Product item(String referencia) {
          return null; 
    }

 
}
