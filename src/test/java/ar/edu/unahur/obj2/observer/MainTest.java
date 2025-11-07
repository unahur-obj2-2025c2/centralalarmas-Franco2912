package ar.edu.unahur.obj2.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.observer.excepciones.NivelDeAlertaIncorrectoException;
import ar.edu.unahur.obj2.observer.observadores.Entidad;
import ar.edu.unahur.obj2.observer.riesgos.RiesgoPromedio;
import ar.edu.unahur.obj2.observer.sujetos.CentralDeMonitoreo;

class MainTest {
    private CentralDeMonitoreo central;
    private Entidad e1;
    private Entidad e2;
    
    @BeforeEach
    void setUp(){
        central = new CentralDeMonitoreo();
        e1 = new Entidad("Hospital");
        e2 = new Entidad("Bomberos");

        central.agregarObservador(e1);
        central.agregarObservador(e2);
    }
    
    @Test
    void test1() {
        central.emitirAlerta("calor", 6);
        central.emitirAlerta("Lluvia", 8);

        assertEquals("calor", e1.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(6, e1.getAlertasRecibidas().getFirst().getNivel());
        assertEquals("calor", e2.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(6, e2.getAlertasRecibidas().getFirst().getNivel());

        assertEquals("Lluvia", e1.getAlertasRecibidas().getLast().getTipo());
        assertEquals(8, e1.getAlertasRecibidas().getLast().getNivel());
        assertEquals("Lluvia", e2.getAlertasRecibidas().getLast().getTipo());
        assertEquals(8, e2.getAlertasRecibidas().getLast().getNivel());

        assertEquals(10, e1.riesgo());
        assertEquals(10, e2.riesgo());
    }

    @Test
    void test2() {
        e1.setCriterio(new RiesgoPromedio());

        central.emitirAlerta("calor", 6);
        central.emitirAlerta("Lluvia", 8);

        assertEquals("calor", e1.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(6, e1.getAlertasRecibidas().getFirst().getNivel());
        assertEquals("calor", e2.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(6, e2.getAlertasRecibidas().getFirst().getNivel());

        assertEquals("Lluvia", e1.getAlertasRecibidas().getLast().getTipo());
        assertEquals(8, e1.getAlertasRecibidas().getLast().getNivel());
        assertEquals("Lluvia", e2.getAlertasRecibidas().getLast().getTipo());
        assertEquals(8, e2.getAlertasRecibidas().getLast().getNivel());

        assertEquals(7, e1.riesgo());
        assertEquals(10, e2.riesgo());
    }

    @Test
    void test3() {
        central.emitirAlerta("calor", 6);
        central.emitirAlerta("Lluvia", 8);

        central.sacarObservador(e1);

        central.emitirAlerta("granizo", 7);

        assertEquals("calor", e1.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(6, e2.getAlertasRecibidas().getFirst().getNivel());
        assertEquals("calor", e2.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(6, e2.getAlertasRecibidas().getFirst().getNivel());

        assertEquals("Lluvia", e1.getAlertasRecibidas().getLast().getTipo());
        assertEquals(8, e1.getAlertasRecibidas().getLast().getNivel());
        
        assertEquals("granizo", e2.getAlertasRecibidas().getLast().getTipo());
        assertEquals(7, e2.getAlertasRecibidas().getLast().getNivel());

        assertEquals(10, e1.riesgo());
        assertEquals(7, e2.riesgo());

        assertEquals(5, central.cantidadNotificacionesEnviadas());
    }

    @Test
    void test4(){

        Exception exception = assertThrows(NivelDeAlertaIncorrectoException.class, () -> central.emitirAlerta("Tornado", 11));

        assertEquals("Nivel de alerta incorrecto", exception.getMessage());
    }
}
