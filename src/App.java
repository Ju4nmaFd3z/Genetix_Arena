import Funciones.*;
import Entidades.*;

public class App {
    // Reset
    public static final String RESET = "\033[0m";
    // Colores
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    //Limpiar Pantalla
    public static void limpiarPantalla() throws Exception {
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
    }
    public static void main(String[] args) throws Exception {
        final String VACIO = " ";
        final String OBSTACULO = YELLOW+"#"+RESET;
        final String ENEMIGO = RED+"X"+RESET;
        final String ALIADO = GREEN+"O"+RESET;
        final String CURANDERO = BLUE+"&"+RESET;
        final int ALTO = 10;
        final int ANCHO = 30;
        String[][] mapa = new String[ALTO][ANCHO];
        for (int i = 0; i < ALTO; i++) {
            for (int j = 0; j < ANCHO; j++) {
                mapa[i][j] = VACIO;
            }
        }
        for (int i = 0; i < 5; i++) {
            Obstaculo obstaculo = new Obstaculo(mapa);
            mapa[obstaculo.posY][obstaculo.posX] = OBSTACULO;
        }
        for (int i = 0; i < 10; i++) {
            Enemigo enemigo = new Enemigo(mapa);
            mapa[enemigo.posY][enemigo.posX] = ENEMIGO;
        }
        for (int i = 0; i < 10; i++) {
            Aliado aliado = new Aliado(mapa);
            mapa[aliado.posY][aliado.posX] = ALIADO;
        }
        for (int i = 0; i < 2; i++) {
            Curandero curandero = new Curandero(mapa);
            mapa[curandero.posY][curandero.posX] = CURANDERO;
        }
        int obstaculos = 5;
        int enemigos = 10;
        int aliados = 10;
        int curanderos = 2;
        boolean juegoTerminado = false;
        do {
            limpiarPantalla();
            System.out.println("Genetix Arena - Juanma Fdez");
            MisFunciones.pintarMapa(mapa);
            System.out.println("\nRecuento de entidades:");
            System.out.println("------------------------");
            System.out.printf("Obstáculos (%s): %d%n", OBSTACULO, obstaculos);
            System.out.printf("Enemigos (%s): %d%n", ENEMIGO, enemigos);
            System.out.printf("Aliados (%s): %d%n", ALIADO, aliados);
            System.out.printf("Curanderos (%s): %d%n", CURANDERO, curanderos);
            Thread.sleep(250);
        } while (!juegoTerminado);
    }
}