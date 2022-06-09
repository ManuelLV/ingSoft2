/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.measuringsystem.server.app;
import co.unicauca.measuringsystem.server.infra.MeasuringSystemServerSocket;

/**
 *
 * @author ANDRE
 */
public class MeasuringSystemApplication {
    public static void main(String[] args) {
        // TODO code application logic here
        MeasuringSystemServerSocket  server= new   MeasuringSystemServerSocket();
        server.start();
        
    }
}
