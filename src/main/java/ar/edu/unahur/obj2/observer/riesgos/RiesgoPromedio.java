package ar.edu.unahur.obj2.observer.riesgos;

import java.util.List;

import ar.edu.unahur.obj2.observer.alarmas.Alerta;

public class RiesgoPromedio implements Criterio{

    @Override
    public Double riesgoActual(List<Alerta> alertas) {
        return alertas.stream().mapToInt(Alerta::getNivel).average().orElse(0.0);
    }

}
