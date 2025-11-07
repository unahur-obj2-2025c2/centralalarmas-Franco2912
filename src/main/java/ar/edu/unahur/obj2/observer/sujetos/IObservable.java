package ar.edu.unahur.obj2.observer.sujetos;

import ar.edu.unahur.obj2.observer.alarmas.Alerta;
import ar.edu.unahur.obj2.observer.observadores.IObservador;

public interface IObservable {
    void agregarObservador(IObservador observador);
    void sacarObservador(IObservador observador);
    void notificar(Alerta alerta);
}
