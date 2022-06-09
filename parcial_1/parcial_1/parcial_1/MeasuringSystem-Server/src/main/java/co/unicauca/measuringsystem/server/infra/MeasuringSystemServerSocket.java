/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.measuringsystem.server.infra;
import co.unicauca.measuringsystem.commons.infra.JsonError;
import co.unicauca.measuringsystem.commons.infra.Utilidades;
import co.unicauca.measuringsystem.commons.infra.Protocolo;
import co.unicauca.measuringsystem.server.domain.FactoryMeasuringItem;
import co.unicauca.measuringsystem.server.access.ICustomerRepository;
import co.unicauca.measuringsystem.server.domain.services.CustomerService;
import co.unicauca.measuringsystem.commons.domain.Product;

//import  del lenguaje
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
/**
 * Servidor Socket que está escuchando permanentemente solicitudes de los
 * clientes. Cada solicitud la atiende en un hilo de ejecución
 *
 * @author Libardo, Julio
 */

public class MeasuringSystemServerSocket implements Runnable {
    //Servicios
    private final CustomerService servicio ;
    private static Socket socket;    
    private static ServerSocket peticionSocket;
    private Scanner entradaDatos;
    private PrintStream salidaDatos;
    private  static final int PORT= Integer.parseInt(Utilidades.loadProperty("server.port"));
    


    
    
    public MeasuringSystemServerSocket(){
        ICustomerRepository iCustomerR = FactoryMeasuringItem.getInstance().repositorio();
        servicio = new CustomerService(iCustomerR);
    }
    
    
      public void start() {
        abrirPuerto();
        while (true) {
            esperarPeticion();
            tThread();
        }
    }
    private static void tThread() {
        new Thread(new MeasuringSystemServerSocket()).start();
    }
    private static void abrirPuerto() {
        try {
            peticionSocket = new ServerSocket(PORT);
            Logger.getLogger("Server").log(Level.INFO, "Servidor iniciado, escuchando por el puerto {0}", PORT);
        } catch (IOException ex) {
            Logger.getLogger(MeasuringSystemServerSocket.class.getName()).log(Level.SEVERE, "Error del server socket al abrir el puerto", ex);
        }
    }
    private static void esperarPeticion() {
        try {
            socket = peticionSocket.accept();
            Logger.getLogger("Socket").log(Level.INFO, "Socket conectado");
        } catch (IOException ex) {
            Logger.getLogger(MeasuringSystemServerSocket.class.getName()).log(Level.SEVERE, "Eror al abrir un socket", ex);
        }
    }
    @Override
    public void run() {
        try {
            crearCadena();
            leerCadena();
            cerrarCadena();
        } catch (IOException ex) {
            Logger.getLogger(MeasuringSystemServerSocket.class.getName()).log(Level.SEVERE, "Eror al leer el flujo", ex);
        }
    }
    private void crearCadena() throws IOException {
        salidaDatos = new PrintStream(socket.getOutputStream());
        entradaDatos = new Scanner(socket.getInputStream());
    }
    private void leerCadena() {
        if (entradaDatos.hasNextLine()) {
            String request = entradaDatos.nextLine();
            procesoPeticion(request);

        } else {
            salidaDatos.flush();
            String errorJson = generarErrorJson();
            salidaDatos.println(errorJson);
        }
    }
    private void procesoPeticion(String peticionJson) { 
        Gson gson = new Gson();
        Protocolo protocolPeticion= gson.fromJson(peticionJson, Protocolo.class);

        switch (protocolPeticion.getResource()) {
            case "peticion":
                if (protocolPeticion.getAction().equals("alerta")) {
                    // Activar acciones de los dispositivos del sistema 
                    procesoEnviarAviso(protocolPeticion);
                }
                if (protocolPeticion.getAction().equals("get")) {
                    // Obtener un elemento    
                    procesoObtenerItem(protocolPeticion);
                }
                break;
        }

    }
    private void procesoEnviarAviso(Protocolo protocoloPeticion){
        String aviso = protocoloPeticion.getAction();
        String respuesta = servicio.enviarAviso(aviso);
        if (respuesta.equals("correcto")) {
            salidaDatos.println(respuesta);
        }
        
    }
    private void procesoObtenerItem(Protocolo protocoloPeticion) {
        String ref = protocoloPeticion.getParameters().get(0).getValue();
        Product producto = servicio.item(ref);
        if (producto == null) {
            String errorJson = generarNotFoundErrorJson();
            salidaDatos.println(errorJson);
        } else {
            salidaDatos.println(objetoJSON(producto));
        }
    }
    private String generarNotFoundErrorJson() {
        List<JsonError> errors = new ArrayList<>();
        JsonError error = new JsonError();
        error.setCode("404");
        error.setError("NOT_FOUND");
        error.setMessage("Error, elemento inexistente");
        errors.add(error);
        Gson gson = new Gson();
        String errorsJson = gson.toJson(errors);
        return errorsJson;
    }
    private String generarErrorJson() {
        List<JsonError> errors = new ArrayList<>();
        JsonError error = new JsonError();
        error.setCode("400");
        error.setError("BAD_REQUEST");
        error.setMessage("Error en la solicitud");
        errors.add(error);

        Gson gson = new Gson();
        String errorJson = gson.toJson(errors);

        return errorJson;
    }
    private void cerrarCadena() throws IOException {
        salidaDatos.close();
        entradaDatos.close();
        socket.close();
    }
    private String objetoJSON(Product producto) {
        Gson gson = new Gson();
        String strObjeto = gson.toJson(producto);
        return strObjeto;
    }

  
}
