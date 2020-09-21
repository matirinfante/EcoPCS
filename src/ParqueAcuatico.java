public class ParqueAcuatico implements Runnable {

    Reloj reloj;

    @Override
    public void run() {

    }

    public boolean abierto() {
        horaActual = reloj.getHoraActual();
        return horaActual >= 9 && horaActual <= 17;
    }

    public void entrarAlParque(Visitante visitante) {

    }

    public void seleccionActividad(Visitante visitante) {
    }
}
