import Funciones.*;
import Entidades.*;

public class App {
    //Clean Screen
    public static final String CLEAN_SCREEN = "\033[H\033[2J";
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset
    // Regular Colors
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static void main(String[] args) throws Exception {
        System.out.println("Genetix Arena - Juanma Fdez");
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
        boolean juegoTerminado = false;
        do {
            MisFunciones.pintarMapa(mapa);
            Thread.sleep(250);
            System.out.println(CLEAN_SCREEN);
        } while (!juegoTerminado);
    }
}