package co.unicauca.measuringsystem.server.domain;

public class Lector {

	public String Evento;
	Sensor[] sensores;
	int numSensores = 0;
	
	
	public Lector(String pEvento) {
		this.Evento = pEvento;
	}
	
	public void addSensor(Shooter Disparador) {
		sensores[numSensores] = new Sensor(Disparador);
		numSensores++;
	}
	public String getEvento() {
		return this.Evento;
	}
	public Sensor getSensor(int id) {
		return sensores[id];
	}
}
