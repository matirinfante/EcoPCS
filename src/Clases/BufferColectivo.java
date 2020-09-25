package Clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Martin
 */
public class BufferColectivo {

    
    private final static int CANT_PASAJEROS_TOTAL = 25;

    Lock aLock = new ReentrantLock();

    boolean llegoDestino,
            estaViajando,
            volver,
            viajar;
    Condition colectivo = aLock.newCondition();
    Condition enEspera = aLock.newCondition();
    Condition bajando = aLock.newCondition();
    private int cantActualPasajeros;
    

    public BufferColectivo(int cantTotal) {
        llegoDestino = false;
        estaViajando = false;
        volver = false;
        viajar = false;
        cantActualPasajeros = 0;
    }

    public void esperandoArrancar() {
        try {
            aLock.lock();
            volver = false;
            
            System.out.println("Esperando para arrancar el colectivo " + Thread.currentThread().getName());
            if (!viajar || cantActualPasajeros < CANT_PASAJEROS_TOTAL) {
                colectivo.await();
            }
            estaViajando = true;
            System.out.println("Llendo al parque");
        } catch (InterruptedException e) {

        } finally {
            aLock.unlock();
        }
    }

    public void esperandoVolver() {
        try {
            aLock.lock();
            llegoDestino = true;
            bajando.signalAll();
            if (!volver) {
                colectivo.await();
            }
            
            System.out.println("Volviendo: " + Thread.currentThread().getName());
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
            if ((cantActualPasajeros == CANT_PASAJEROS_TOTAL) || estaViajando) {
                enEspera.await();
            }

            System.out.println("Ingreso la Persona " + visitante.getNombreVisitante());
            cantActualPasajeros++;
            if (cantActualPasajeros == CANT_PASAJEROS_TOTAL) {
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
            if(!llegoDestino){
                bajando.await();
            }
            System.out.println("Visitante " + visitante.getNombreVisitante() + " descendió");
            cantActualPasajeros--;
            System.out.println("Cantidad de pasajeros restantes: " + cantActualPasajeros);
            if (cantActualPasajeros == 0) {
                volver = true;
                colectivo.signal();
            }
        } catch (InterruptedException e) {

        } finally {
            aLock.unlock();
        }
    }

}
