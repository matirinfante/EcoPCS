package Clases;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Colectivo implements Runnable {

    int espacio;
    BufferColectivo buffer;
    public final static int CANT_PASAJEROS_TOTAL = 25;

    private int cantPasajerosActual, numeroColectivo;

    public Colectivo(BufferColectivo buffer, int numeroColectivo) {
        this.buffer = buffer;
        this.cantPasajerosActual = 0;
        this.numeroColectivo = numeroColectivo;
    }

    public void setBuffer(BufferColectivo buffer) {
        this.buffer = buffer;
    }
    public int getNumeroColectivo(){
        return this.numeroColectivo;
    }

    public int getCantPasajerosActual() {
        return this.cantPasajerosActual;
    }

    public void setCantPasajerosActual(int cant) {
        this.cantPasajerosActual = cant;
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.buffer.esperandoArrancar(this);
                Thread.sleep(2000);
                this.buffer.esperandoVolver(this);
                Thread.sleep(2000);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Colectivo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
