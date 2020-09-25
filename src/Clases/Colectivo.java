package Clases;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Colectivo implements Runnable {

    int espacio;
    BufferColectivo buffer;

    public Colectivo(BufferColectivo buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("LLEGUE A RECOGER GENTE");
                this.buffer.esperandoArrancar();
                Thread.sleep(2000);
                this.buffer.esperandoVolver();
                Thread.sleep(2000);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Colectivo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
