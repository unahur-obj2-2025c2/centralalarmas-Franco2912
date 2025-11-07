package ar.edu.unahur.obj2.observer.riesgos;

import java.util.List;

import ar.edu.unahur.obj2.observer.alarmas.Alerta;

public interface Criterio {

    Double riesgoActual(List<Alerta> alertas);

}
