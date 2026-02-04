import Funciones.*;
import Entidades.*;
import java.util.ArrayList;

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
    public static void redibujarMapa(String[][] mapa, ArrayList<Obstaculo> listaObstaculos,
                                    ArrayList<Enemigo> listaEnemigos, ArrayList<Aliado> listaAliados,
                                    ArrayList<Curandero> listaCuranderos) {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                mapa[i][j] = " ";
            }
        }
        for (Obstaculo obstaculo : listaObstaculos) {
            mapa[obstaculo.posY][obstaculo.posX] = YELLOW+"#"+RESET;
        }
        for (Enemigo enemigo : listaEnemigos) {
            mapa[enemigo.posY][enemigo.posX] = RED+"X"+RESET;
        }
        for (Aliado aliado : listaAliados) {
            mapa[aliado.posY][aliado.posX] = GREEN+"O"+RESET;
        }
        for (Curandero curandero : listaCuranderos) {
            mapa[curandero.posY][curandero.posX] = BLUE+"&"+RESET;
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
        ArrayList<Obstaculo> listaObstaculos = new ArrayList<>();
        ArrayList<Enemigo> listaEnemigos = new ArrayList<>();
        ArrayList<Aliado> listaAliados = new ArrayList<>();
        ArrayList<Curandero> listaCuranderos = new ArrayList<>();
        for (int i = 0; i < ALTO; i++) {
            for (int j = 0; j < ANCHO; j++) {
                mapa[i][j] = VACIO;
            }
        }
        for (int i = 0; i < 5; i++) {
            Obstaculo obstaculo = new Obstaculo(mapa);
            listaObstaculos.add(obstaculo);
            mapa[obstaculo.posY][obstaculo.posX] = OBSTACULO;
        }
        for (int i = 0; i < 10; i++) {
            Enemigo enemigo = new Enemigo(mapa);
            listaEnemigos.add(enemigo);
            mapa[enemigo.posY][enemigo.posX] = ENEMIGO;
        }
        for (int i = 0; i < 10; i++) {
            Aliado aliado = new Aliado(mapa);
            listaAliados.add(aliado);
            mapa[aliado.posY][aliado.posX] = ALIADO;
        }
        for (int i = 0; i < 2; i++) {
            Curandero curandero = new Curandero(mapa);
            listaCuranderos.add(curandero);
            mapa[curandero.posY][curandero.posX] = CURANDERO;
        }
        boolean juegoTerminado = false;
        do {
            limpiarPantalla();
            System.out.println("Genetix Arena - Juanma Fdez");
            redibujarMapa(mapa, listaObstaculos, listaEnemigos, listaAliados, listaCuranderos);
            MisFunciones.pintarMapa(mapa);
            System.out.println("\nRecuento de entidades:");
            System.out.println("------------------------");
            System.out.printf("Obstáculos (%s): %d%n", OBSTACULO, listaObstaculos.size());
            System.out.printf("Enemigos (%s): %d%n", ENEMIGO, listaEnemigos.size());
            System.out.printf("Aliados (%s): %d%n", ALIADO, listaAliados.size());
            System.out.printf("Curanderos (%s) [%s]: %d%n", CURANDERO, "SIN IMPLEMENTAR", listaCuranderos.size());
            Thread.sleep(250);
        } while (!juegoTerminado);
    }
}