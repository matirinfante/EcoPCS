package Clases;

public class ParqueAcuatico implements Runnable {

    Reloj reloj;

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

    }

    public void seleccionActividad(Visitante visitante) {
    }
}
