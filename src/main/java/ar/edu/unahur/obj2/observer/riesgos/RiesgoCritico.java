package ar.edu.unahur.obj2.observer.riesgos;

import java.util.List;

import ar.edu.unahur.obj2.observer.alarmas.Alerta;

public class RiesgoCritico implements Criterio{

    @Override
    public Double riesgoActual(List<Alerta> alertas) {
        return alertas.getLast().esCritica() ? 10.0 : alertas.getLast().getNivel();
    }

}
