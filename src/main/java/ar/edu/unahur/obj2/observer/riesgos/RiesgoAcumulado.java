package ar.edu.unahur.obj2.observer.riesgos;

import java.util.List;

import ar.edu.unahur.obj2.observer.alarmas.Alerta;

public class RiesgoAcumulado implements Criterio{

    @Override
    public Double riesgoActual(List<Alerta> alertas) {
        return alertas.stream().filter(Alerta::esCritica).mapToDouble(Alerta::getNivel).sum();
    }
}
