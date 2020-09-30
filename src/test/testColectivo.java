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
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Martin
 */
public class testColectivo {

    private static final int CANT_PERSONAS = 100;
    private static final int CAPACIDAD_COLECTIVO = 25;
    private static final int CANTIDAD_COLECTIVOS = 3;

    public static void main(String[] args) {
        Visitante[] visitantes = new Visitante[CANT_PERSONAS];
        Reloj reloj = new Reloj();
        ParqueAcuatico parqueAcuatico = new ParqueAcuatico(reloj);
        Colectivo[] colectivos = new Colectivo[CANTIDAD_COLECTIVOS];
        BufferColectivo[] choferes = new BufferColectivo[CANTIDAD_COLECTIVOS];

        for (int i = 0; i < CANTIDAD_COLECTIVOS; i++) {
            choferes[i] = new BufferColectivo();
            colectivos[i] = new Colectivo(choferes[i], i);
        }

        for (int i = 0; i < colectivos.length; i++) {
            Thread hiloColectivo = new Thread(colectivos[i]);
            hiloColectivo.start();
        }

        //Generar aleatoriamente nombres y esas cosas
        generarVisitantes(visitantes, reloj, parqueAcuatico, choferes);
        for (Visitante visitante : visitantes) {
            //System.out.println(visitante.getNombreVisitante());
        }
        //Creo que lo ideal sería crear un arreglo de hilos
        for (int i = 0; i < visitantes.length; i++) {
            Thread hiloVisitante = new Thread(visitantes[i]);
            hiloVisitante.start();
        }
    }

    private static void generarVisitantes(Visitante[] visitantes, Reloj reloj,
            ParqueAcuatico parqueAcuatico, BufferColectivo[] choferes) {
        String[] nombres = {"Abadolia", "Aceli", "Adriol", "Crsitóbal", "Cristolbina",
            "Ebangélica", "Gabrieli", "Humbaldo", "Ibania", "Jacobita", "Jesubina",
            "Lanibal", "Leusebio", "Lesbia", "Lesby", "Libra"};
        String[] apellidos = {"Fernández", "García", "Pérez", "Martínez", "Gómez", "Díaz", "Sánchez"};
        Random r = new Random();

        for (int i = 0; i < visitantes.length; i++) {

            //boolean enQueViaja = nextBoolean(r,80);
            boolean enQueViaja = true;
            int nombreAleatorio = r.nextInt(nombres.length - 1) + 1;  // Entre 0 y 5, más
            int apellidoAleatorio = r.nextInt(apellidos.length - 1) + 1;  // Entre 0 y 5, más 1.
            String nombreVisitante = apellidos[apellidoAleatorio] + ", " + nombres[nombreAleatorio];
            visitantes[i] = new Visitante(nombreVisitante, reloj, parqueAcuatico, choferes[i % CANTIDAD_COLECTIVOS], enQueViaja);
        }
    }

    public static boolean nextBoolean(Random r, int truePercentage) {
        /*
        Ejemplos
        Random r = new Random();
r.NextBool(); // returns true or false with equal probability
r.NextBool(20); // 20% chance to be true;
r.NextBool(100); // always return true
r.NextBool(0); // always return false
         */
        return r.nextDouble() < truePercentage / 100.0;
    }

}
