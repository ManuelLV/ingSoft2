package co.unicauca.measuringsystem.client.infra;

import co.unicauca.measuringsystem.commons.infra.Utilidades;
// librerias del lenguaje
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANDRE
 */
public class MeasuringSystemSocket {
    
     private java.net.Socket socket = null;
     private Scanner entrada;  
    private PrintStream salida;  
    private final String IP_SERVER = Utilidades.loadProperty("server.ip");
    
    private final int PORT = Integer.parseInt(Utilidades.loadProperty("server.port"));

    public String enviandoCadena(String rJson) throws IOException {
        String resultado = "";
        entrada = new Scanner(socket.getInputStream());
        salida = new PrintStream(socket.getOutputStream());
        salida.flush();

        // Solicitud
        salida.println(rJson);

        // Procesa 
        if (entrada.hasNextLine()) {
            resultado = entrada.nextLine();
        }
        System.out.println("respuesta response: " + resultado);
        return resultado;
    }
 
    public void cerrarCadena() {
        salida.close();
        entrada.close();
    }

    /**
     * Desconectar el socket
     */
    public void desconectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(MeasuringSystemSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connect() throws IOException {
        socket = new java.net.Socket(IP_SERVER, PORT);
        Logger.getLogger("SocketClient").log(Level.INFO, "Socket establecido");
    }
}
