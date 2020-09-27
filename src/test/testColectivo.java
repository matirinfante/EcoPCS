/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Clases.Visitante;
import Clases.BufferColectivo;
import Clases.Colectivo;
import Clases.ParqueAcuatico;
import Clases.Reloj;
import java.util.Random;

/**
 *
 * @author Martin
 */
public class testColectivo {

    private static final int CANT_PERSONAS = 25;
    private static final int CAPACIDAD_COLECTIVO = 25;

    public static void main(String[] args) {
        Visitante[] visitantes = new Visitante[CANT_PERSONAS];
        Reloj reloj = new Reloj();
        ParqueAcuatico parqueAcuatico = new ParqueAcuatico(reloj);
        BufferColectivo buffer = new BufferColectivo(CAPACIDAD_COLECTIVO);
        Colectivo colectivo = new Colectivo(buffer);
        Thread hiloColectivo = new Thread(colectivo);

        //Generar aleatoriamente nombres y esas cosas
        generarVisitantes(visitantes, reloj, parqueAcuatico, buffer);
        for (Visitante visitante : visitantes) {
            //System.out.println(visitante.getNombreVisitante());
        }
        //Creo que lo ideal sería crear un arreglo de hilos
        for(int i= 0;i < visitantes.length;i++){
            Thread hiloVisitante = new Thread(visitantes[i]);
            hiloVisitante.start();
        }
         hiloColectivo.start();

    }

    private static void generarVisitantes(Visitante[] visitantes, Reloj reloj,
            ParqueAcuatico parqueAcuatico, BufferColectivo colectivo) {
        String[] nombres = {"Abadolia", "Aceli", "Adriol", "Crsitóbal", "Cristolbina",
            "Ebangélica", "Gabrieli", "Humbaldo", "Ibania", "Jacobita", "Jesubina",
            "Lanibal", "Leusebio", "Lesbia", "Lesby", "Libra"};
        String[] apellidos = {"Fernández", "García", "Pérez", "Martínez", "Gómez", "Díaz", "Sánchez"};
        Random r = new Random();

        for (int i = 0; i < visitantes.length; i++) {
            int nombreAleatorio = r.nextInt(nombres.length - 1) + 1;  // Entre 0 y 5, más 1.
            int apellidoAleatorio = r.nextInt(apellidos.length - 1) + 1;  // Entre 0 y 5, más 1.
            String nombreVisitante = apellidos[apellidoAleatorio] + ", " + nombres[nombreAleatorio];
            visitantes[i] = new Visitante(nombreVisitante, reloj, parqueAcuatico, colectivo, true);
        }
    }
}
