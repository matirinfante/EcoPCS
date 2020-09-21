public class EcoPCSMain {
    public static void main(String[] args) {
        ParqueAcuatico parqueAcuatico = new ParqueAcuatico();
        Reloj reloj = new Reloj();
        Visitante[] visitantes = new Visitante[25];
        Colectivo[] colectivos = new Colectivo[5];

        for (int i = 0; i < 10; i++) {
            visitantes[i] = new Visitante("Visitante " + i, reloj, parqueAcuatico, colectivo, true);
        }

    }
}
