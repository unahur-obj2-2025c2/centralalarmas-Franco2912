package ar.edu.unahur.obj2.observer.observadores;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.observer.alarmas.Alerta;
import ar.edu.unahur.obj2.observer.riesgos.Criterio;
import ar.edu.unahur.obj2.observer.riesgos.RiesgoCritico;

public class Entidad implements IObservador{

    private final String nombre;
    private final List<Alerta> alertasRecibidas = new ArrayList<>();
    private Criterio criterioRiesgo = new RiesgoCritico();


    public Entidad(String nombre) {
        this.nombre = nombre;
    }

    

    public String getNombre() {
        return nombre;
    }

    public List<Alerta> getRegistros() {
        return alertasRecibidas;
    }

    public Criterio getCriterio() {
        return criterioRiesgo;
    }

    public void setCriterio(Criterio criterio) {
        this.criterioRiesgo = criterio;
    }

    public Double riesgo(){
        return alertasRecibidas.isEmpty() ? 0.0 : criterioRiesgo.riesgoActual(alertasRecibidas);
    }

    @Override
    public void actualizar(Alerta alerta) {
        alertasRecibidas.add(alerta);
    }


}
