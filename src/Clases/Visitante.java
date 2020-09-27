package Clases;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * VISITANTE Define las características de un visitante del parque acuatico. Es
 * sumamente necesario el uso del reloj global, el parque que visita y el
 * colectivo si lo usa.
 *
 * @author Matias Infante y Martin Lillo
 */
public class Visitante implements Runnable {

    private String nombreVisitante;
    private Reloj reloj;
    private ParqueAcuatico parqueAcuatico;
    private BufferColectivo colectivo;
    private boolean vaColectivo;

    /**
     * Constructor
     *
     * @param nombreVisitante define el nombre identificador del visitante
     * @param reloj representa el reloj global de la simulacion
     * @param parqueAcuatico define el parque acuatico EcoPCS
     * @param colectivo no nulo si el visitante usa colectivo
     * @param vaColectivo define si el visitante realiza el viaje en colectivo o
     * por su cuenta (auto)
     */
    public Visitante(String nombreVisitante, Reloj reloj, ParqueAcuatico parqueAcuatico, BufferColectivo colectivo, boolean vaColectivo) {
        this.nombreVisitante = nombreVisitante;
        this.reloj = reloj;
        this.parqueAcuatico = parqueAcuatico;
        this.colectivo = colectivo;
        this.vaColectivo = vaColectivo;
    }

    /**
     * En su ejecución el visitante verifica si el parque está abierto para
     * poder visitarlo. Luego, de viajar en colectivo debe subirse al mismo y
     * luego bajarse. Si va al parque por su cuenta, se simula arbitrariamente
     * el viaje en 2 segundos. Una vez que el visitante arriba al parque, debe
     * pasar por los molinetes y seleccionar aleatoriamente qué es lo que hará
     * dentro.
     */
    @Override
    public void run() {
        while (true) {

            while (!parqueAcuatico.abierto()) {
                System.out.println(this.getNombreVisitante() + " // Parque ECO-PCS cerrado *triste*");
            }

            System.out.println(this.getNombreVisitante() + " // ¡Vamos al parque!");

            if (colectivo != null && vaColectivo) {
                try {
                    //colectivo.esperandoSubir(this);
//System.out.println("ENTRO ACA");
                    colectivo.esperandoSubir(this);
                    colectivo.bajarVisitante(this);
                } catch (InterruptedException ex) {
                    System.out.println("Error en el colectivo: " + ex.getMessage());
                }
            } else {
                System.out.println(this.getNombreVisitante() + " // ¡Vamos al parque! (pero en auto)");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("ERROR EN LA VISITA " + e);
                }
            }

            System.out.println(this.getNombreVisitante() + " // ¡Estoy en el parque!");

            parqueAcuatico.entrarAlParque(this);
            parqueAcuatico.seleccionActividad(this);
        }
    }

    /**
     * @return el nombre del visitante
     */
    public String getNombreVisitante() {
        return nombreVisitante;
    }
}
