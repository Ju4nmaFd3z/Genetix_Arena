import Funciones.*;
import Entidades.*;
import java.util.ArrayList;

/**
 * Clase principal que ejecuta el juego Genetix Arena.
 * Gestiona la inicialización del mapa, creación de entidades, loop principal del juego
 * y la lógica de actualización de estados (movimiento, combate, curación).
 * 
 * El juego es una simulación de una arena donde enemigos y aliados luchan,
 * mientras que los curanderos intentan mantener con vida a los aliados.
 * El juego termina cuando uno de los bandos es completamente eliminado.
 * 
 * @author Juanma Fdez
 * @version 2.0
 */
public class App {
    
    // Reset de colores ANSI
    /**
     * Código ANSI para resetear los colores de la consola.
     */
    public static final String RESET = "\033[0m";
    
    // Colores ANSI para la consola
    /**
     * Código ANSI para mostrar texto en color rojo.
     */
    public static final String RED = "\033[0;31m";
    
    /**
     * Código ANSI para mostrar texto en color verde.
     */
    public static final String GREEN = "\033[0;32m";
    
    /**
     * Código ANSI para mostrar texto en color amarillo.
     */
    public static final String YELLOW = "\033[0;33m";
    
    /**
     * Código ANSI para mostrar texto en color azul.
     */
    public static final String BLUE = "\033[0;34m";
    
    /**
     * Limpia la pantalla de la consola usando códigos ANSI.
     * Utiliza el código de escape ANSI H para posicionar el cursor en la esquina superior izquierda,
     * proporcionando una limpieza más rápida sin depender del sistema operativo.
     * 
     * @throws Exception Si ocurre un error durante la ejecución
     */
    public static void limpiarPantalla() throws Exception {
        System.out.print("\033[H");
        System.out.flush();
    }
    
    /**
     * Redibuja el mapa limpiándolo y actualizando las posiciones de todas las entidades.
     * Primero vacía todas las casillas y luego coloca cada entidad en su posición actual
     * con su símbolo y color correspondiente.
     * 
     * @param mapa La matriz que representa el mapa del juego
     * @param listaObstaculos Lista de obstáculos a dibujar
     * @param listaEnemigos Lista de enemigos a dibujar
     * @param listaAliados Lista de aliados a dibujar
     * @param listaCuranderos Lista de curanderos a dibujar
     */
    public static void redibujarMapa(String[][] mapa, ArrayList<Obstaculo> listaObstaculos,
                                     ArrayList<Enemigo> listaEnemigos, ArrayList<Aliado> listaAliados,
                                     ArrayList<Curandero> listaCuranderos) {
        // Limpiar mapa
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                mapa[i][j] = " ";
            }
        }
        
        // Dibujar obstáculos
        for (Obstaculo obstaculo : listaObstaculos) {
            mapa[obstaculo.posY][obstaculo.posX] = YELLOW+"#"+RESET;
        }
        
        // Dibujar enemigos
        for (Enemigo enemigo : listaEnemigos) {
            mapa[enemigo.posY][enemigo.posX] = RED+"X"+RESET;
        }
        
        // Dibujar aliados
        for (Aliado aliado : listaAliados) {
            mapa[aliado.posY][aliado.posX] = GREEN+"O"+RESET;
        }
        
        // Dibujar curanderos
        for (Curandero curandero : listaCuranderos) {
            mapa[curandero.posY][curandero.posX] = BLUE+"&"+RESET;
        }
    }
    
    /**
     * Método principal que inicia y controla el flujo del juego.
     * 
     * Inicialización:
     * - Crea un mapa de 20x50 casillas
     * - Genera 30 obstáculos
     * - Genera 25 enemigos
     * - Genera 25 aliados
     * - Genera 5 curanderos
     * 
     * Loop principal:
     * - Actualiza movimientos de enemigos (persecución)
     * - Actualiza movimientos de aliados (escape)
     * - Actualiza movimientos y curaciones de curanderos
     * - Detecta y resuelve colisiones
     * - Limpia entidades muertas
     * - Verifica condiciones de victoria/derrota
     * - Redibuja el mapa y muestra información
     * 
     * Condiciones de finalización:
     * - Todos los aliados mueren: ganan los enemigos
     * - Todos los enemigos mueren: ganan los aliados
     * - No quedan ni enemigos ni aliados: empate
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     * @throws Exception Si ocurre un error durante la ejecución
     */
    public static void main(String[] args) throws Exception {
        // Constantes de símbolos
        final String VACIO = " ";
        final String OBSTACULO = YELLOW+"#"+RESET;
        final String ENEMIGO = RED+"X"+RESET;
        final String ALIADO = GREEN+"O"+RESET;
        final String CURANDERO = BLUE+"&"+RESET;
        
        // Dimensiones del mapa
        final int ALTO = 25;
        final int ANCHO = 75;
        
        // Inicializar matriz del mapa
        String[][] mapa = new String[ALTO][ANCHO];
        
        // Inicializar listas de entidades
        ArrayList<Obstaculo> listaObstaculos = new ArrayList<>();
        ArrayList<Enemigo> listaEnemigos = new ArrayList<>();
        ArrayList<Aliado> listaAliados = new ArrayList<>();
        ArrayList<Curandero> listaCuranderos = new ArrayList<>();
        
        // Llenar el mapa de espacios vacíos
        for (int i = 0; i < ALTO; i++) {
            for (int j = 0; j < ANCHO; j++) {
                mapa[i][j] = VACIO;
            }
        }
        
        // Crear obstáculos
        for (int i = 0; i < 50; i++) {
            Obstaculo obstaculo = new Obstaculo(mapa);
            listaObstaculos.add(obstaculo);
            mapa[obstaculo.posY][obstaculo.posX] = OBSTACULO;
        }
        
        // Crear enemigos
        for (int i = 0; i < 75; i++) {
            Enemigo enemigo = new Enemigo(mapa);
            listaEnemigos.add(enemigo);
            mapa[enemigo.posY][enemigo.posX] = ENEMIGO;
        }
        
        // Crear aliados
        for (int i = 0; i < 75; i++) {
            Aliado aliado = new Aliado(mapa);
            listaAliados.add(aliado);
            mapa[aliado.posY][aliado.posX] = ALIADO;
        }
        
        // Crear curanderos
        for (int i = 0; i < 5; i++) {
            Curandero curandero = new Curandero(mapa);
            listaCuranderos.add(curandero);
            mapa[curandero.posY][curandero.posX] = CURANDERO;
        }
        
        // Variables de control del juego
        String evento = "Se ha iniciado el juego";
        boolean juegoTerminado = false;
        
        // Loop principal del juego
        do {
            // Limpiar pantalla
            limpiarPantalla();
            System.out.println("Genetix Arena - Juanma Fdez");
            
            // Enemigos persiguen a los aliados
            for (Enemigo enemigo : listaEnemigos) {
                enemigo.Persigue(listaAliados, ALTO, ANCHO, listaEnemigos, listaObstaculos, listaCuranderos);
            }
            
            // Aliados huyen de los enemigos
            for (Aliado aliado : listaAliados) {
                aliado.Escapa(listaEnemigos, ALTO, ANCHO, listaAliados, listaObstaculos, listaCuranderos);
            }
            
            // Curanderos se mueven y curan
            for (Curandero curandero : listaCuranderos) {
                curandero.Cura(listaAliados, ALTO, ANCHO, listaEnemigos, listaObstaculos, listaCuranderos);
            }
            
            // Detectar y resolver colisiones
            evento = MisFunciones.detectarYResolverColisiones(listaEnemigos, listaAliados);
            
            // Limpiar entidades muertas
            MisFunciones.limpiarMuertos(listaEnemigos, listaAliados);
            
            // Verificar condiciones de victoria/derrota
            if (listaAliados.isEmpty() && listaEnemigos.isEmpty()) {
                evento = "¡No queda nadie en pie! Empate";
                juegoTerminado = true;
            } else if (listaAliados.isEmpty()) {
                evento = "¡Ganan los enemigos!";
                juegoTerminado = true;
            } else if (listaEnemigos.isEmpty()) {
                evento = "¡Ganan los aliados, a tope con la COPE!";
                juegoTerminado = true;
            }
            
            // Redibujar mapa y mostrar estadísticas
            redibujarMapa(mapa, listaObstaculos, listaEnemigos, listaAliados, listaCuranderos);
            MisFunciones.pintarMapa(mapa);
            StringBuilder stats = new StringBuilder();
            stats.append(String.format("\nEventos Recientes: %s\n", evento));
            stats.append(String.format("\nRecuento de entidades:\n"));
            stats.append(String.format("-----------------------------------\n"));
            stats.append(String.format("Obstáculos (%s): %d%n", OBSTACULO, listaObstaculos.size()));
            stats.append(String.format("Curanderos (%s): %d%n", CURANDERO, listaCuranderos.size()));
            stats.append(String.format("-----------------------------------\n"));
            stats.append(String.format("Enemigos (%s): %d%n", ENEMIGO, listaEnemigos.size()));
            stats.append(String.format("Aliados (%s): %d%n", ALIADO, listaAliados.size()));
            // Imprimo el String Builder
            System.out.println(stats.toString());
            // Pausa para visualizar el juego
            Thread.sleep(200);
            
        } while (!juegoTerminado);
    }
}