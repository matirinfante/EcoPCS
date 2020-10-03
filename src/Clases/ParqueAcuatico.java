package Clases;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ParqueAcuatico implements Runnable {

    private Reloj reloj;
    private Semaphore molinete = new Semaphore(2, true);


    public ParqueAcuatico(Reloj reloj) {
        this.reloj = reloj;
    }

    @Override
    public void run() {

    }

    public boolean abierto() {
        //int horaActual = reloj.getHoraActual();
        //return horaActual >= 9 && horaActual <= 17;
        return true;
    }

    public void entrarAlParque(Visitante visitante) {
        System.out.println(visitante.getNombreVisitante() + " // Entrando al parque...");
        try {
            molinete.acquire();
            Thread.sleep(1000);
            molinete.release();
        } catch (InterruptedException e) {
            System.out.println("Error en molinete entrada" + e);
        }

        System.out.println(visitante.getNombreVisitante() + " // Todo en orden, ya puede disfrutar el parque.");

    }

    public void seleccionActividad(Visitante visitante) {
        int actividad;
        while (abierto()) {
            actividad = new Random().nextInt(7);

            switch (actividad) {
                case 0:
                    comprar(visitante);
                    break;
                case 1:
                    comer(visitante);
                    break;
                case 2:
                    nadarDelfines(visitante);
                    break;
                case 3:
                    nadarSnorkel(visitante);
                    break;
                case 4:
                    aventurearMundo(visitante);
                    break;
                case 5:
                    mirarFaro(visitante);
                    break;
                case 6:
                    correrCarreraGomones(visitante);
                    break;
                default:
                    break;
            }
        }
    }
}
