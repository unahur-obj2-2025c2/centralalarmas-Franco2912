package ar.edu.unahur.obj2.observer.sujetos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import ar.edu.unahur.obj2.observer.alarmas.Alerta;
import ar.edu.unahur.obj2.observer.excepciones.NivelDeAlertaIncorrectoException;
import ar.edu.unahur.obj2.observer.observadores.IObservador;

public class CentralDeMonitoreo implements IObservable{

    private final Set<IObservador> observadores;
    private final HashMap<Alerta,Integer> registro;

    public CentralDeMonitoreo() {
        this.registro = new HashMap<>();
        this.observadores = new HashSet<>();
    }

    public void emitirAlerta(String tipo, Integer nivel){
        if(nivel < 1 || nivel > 10){
            throw new NivelDeAlertaIncorrectoException();
        }
        Alerta alerta = new Alerta(tipo, nivel);
        registro.put(alerta, observadores.size());
        notificar(alerta);
    }

    public Integer cantidadNotificacionesEnviadas(){
        return registro.values().stream().mapToInt(Integer :: intValue).sum();
    }

    @Override
    public void agregarObservador(IObservador observador) {
        observadores.add(observador);
    }

    @Override
    public void sacarObservador(IObservador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificar(Alerta alerta) {
        observadores.forEach(o -> o.actualizar(alerta));
    }
}
