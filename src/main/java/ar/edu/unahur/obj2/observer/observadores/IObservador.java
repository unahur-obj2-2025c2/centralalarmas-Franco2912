package ar.edu.unahur.obj2.observer.observadores;

import ar.edu.unahur.obj2.observer.alarmas.Alerta;

public interface IObservador {
    void actualizar(Alerta alerta);
}
