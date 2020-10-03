package Clases;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Martin
 */
public class BufferColectivo {

    Lock aLock = new ReentrantLock();
    boolean llegoDestino,
            estaViajando,
            volver,
            viajar;
    private Condition colectivo = aLock.newCondition();

    private Condition enEspera = aLock.newCondition();
    private Condition bajando = aLock.newCondition();
    private int cantidadActualPasajeros;

    public BufferColectivo() {
        llegoDestino = false;
        estaViajando = false;
        volver = false;
        viajar = false;
        cantidadActualPasajeros = 0;
    }

    public void esperandoArrancar(Colectivo objColectivo) {
        try {
            aLock.lock();
            volver = false;

            System.out.println("Esperando para arrancar el colectivo nro: " + objColectivo.getNumeroColectivo());
            if (!viajar || objColectivo.getCantPasajerosActual() < Colectivo.CANT_PASAJEROS_TOTAL) {
                colectivo.await();
            }

            estaViajando = true;
            System.out.println("Llendo al parque");
        } catch (InterruptedException e) {

        } finally {
            aLock.unlock();
        }
    }

    public void esperandoVolver(Colectivo objColectivo) {
        try {
            aLock.lock();
            llegoDestino = true;
            bajando.signalAll();
            if (!volver) {
                colectivo.await();
            }
            System.out.println("Volviendo colectivo: " + objColectivo.getNumeroColectivo());
        } catch (InterruptedException e) {
            System.out.println("ERROR ESPERANDO PARA VOLVER(? : " + e.getMessage());
        } finally {
            aLock.unlock();
        }

    }

    //Visitantes
    public void esperandoSubir(Visitante visitante) throws InterruptedException {
        try {
            aLock.lock();

            if ((cantidadActualPasajeros == Colectivo.CANT_PASAJEROS_TOTAL) || estaViajando) {
                enEspera.await();
            }

            System.out.println("Ingreso la Persona " + visitante.getNombreVisitante());
            cantidadActualPasajeros++;
            if (cantidadActualPasajeros == Colectivo.CANT_PASAJEROS_TOTAL) {
                viajar = true;
                llegoDestino = false;
                colectivo.signal();
            }
        } catch (InterruptedException e) {
            System.out.println("ERROR ESPERANDO SUBIR: " + e.getMessage());
        } finally {
            aLock.unlock();
        }

    }

    public void bajarVisitante(Visitante visitante) {
        try {
            aLock.lock();
            if (!llegoDestino) {
                bajando.await();
            }
            System.out.println("Visitante " + visitante.getNombreVisitante() + " descendió");

            cantidadActualPasajeros--;

            System.out.println("Cantidad de pasajeros restantes: " + cantidadActualPasajeros);
            if (cantidadActualPasajeros == 0) {
                volver = true;
                colectivo.signal();
            }
        } catch (InterruptedException e) {

        } finally {
            aLock.unlock();
        }
    }

}
