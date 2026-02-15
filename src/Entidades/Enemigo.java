package Entidades;
import java.util.ArrayList;

import Entidades.Contratos.InterfazEnemigo;
import Funciones.MisFunciones;

/**
 * Clase que representa un enemigo en el juego Genetix Arena.
 * Los enemigos persiguen a los aliados intentando eliminarlos.
 * Implementa la interfaz InterfazEnemigo que define el comportamiento de persecución.
 * 
 * Utiliza un sistema inteligente de movimiento que evalúa las 8 casillas adyacentes
 * y elige la mejor dirección basada en la distancia al objetivo.
 * 
 * @author Juanma Fdez
 * @version 3.0
 */
public class Enemigo extends Entidad implements InterfazEnemigo {
    
    /**
     * Array con las direcciones posibles en el eje Y.
     * Ordenadas para evaluación de todas las 8 direcciones posibles.
     */
    private static final int[] DIRECCION_Y = {-1, -1, -1, 0, 0, 1, 1, 1};
    
    /**
     * Array con las direcciones posibles en el eje X.
     * Ordenadas para evaluación de todas las 8 direcciones posibles.
     */
    private static final int[] DIRECCION_X = {-1, 0, 1, -1, 1, -1, 0, 1};
    
    /**
     * Constructor que crea un enemigo en una posición aleatoria válida del mapa.
     * Delega la inicialización a la clase padre Entidad.
     * 
     * @param matriz La matriz bidimensional que representa el mapa del juego.
     *               Se utiliza para encontrar una posición válida para el enemigo.
     */
    public Enemigo(String[][] matriz){
        super(matriz);
    }

    /**
     * Implementa el comportamiento de persecución del enemigo.
     * El enemigo identifica al aliado más cercano y evalúa las 8 casillas adyacentes,
     * eligiendo la dirección que lo acerque más al objetivo.
     * Evita obstáculos y otras entidades durante el movimiento.
     * 
     * @param listaAliados Lista de todos los aliados en el juego (para identificar objetivo)
     * @param ALTO Alto del mapa (límite de movimiento)
     * @param ANCHO Ancho del mapa (límite de movimiento)
     * @param listaEnemigos Lista de todos los enemigos (para verificar colisiones)
     * @param listaObstaculos Lista de obstáculos (para verificar colisiones)
     * @param listaCuranderos Lista de curanderos (para verificar colisiones)
     */
    public void Persigue(ArrayList<Aliado> listaAliados, int ALTO, int ANCHO,
                         ArrayList<Enemigo> listaEnemigos, ArrayList<Obstaculo> listaObstaculos, 
                         ArrayList<Curandero> listaCuranderos) {
        
        // Encontrar el aliado más cercano
        Aliado objetivo = null;
        double distanciaMinima = Double.MAX_VALUE;
        
        for (Aliado aliado : listaAliados) {
            double distancia = this.getDistancia(aliado);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                objetivo = aliado;
            }
        }
        
        if (objetivo == null) return;
        
        // Variables para almacenar la mejor dirección encontrada
        double mayorDistancia = Double.MAX_VALUE;
        int mejorVx = 0;
        int mejorVy = 0;
        
        // Evaluar las 8 direcciones posibles
        for (int i = 0; i < DIRECCION_Y.length; i++) {
            int nuevaX = this.getPosX() + DIRECCION_X[i];
            int nuevaY = this.getPosY() + DIRECCION_Y[i];
            
            // Verificar si la posición es válida
            if (MisFunciones.posicionValida(nuevaX, nuevaY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
                // Crear posición temporal para calcular distancia
                Entidad posicionPrueba = new Entidad(nuevaX, nuevaY);
                
                // Calcular distancia desde esta nueva posición al objetivo
                double distancia = posicionPrueba.getDistancia(objetivo);
                
                // Si esta dirección nos acerca más al objetivo, la guardamos
                if (distancia < mayorDistancia) {
                    mayorDistancia = distancia;
                    mejorVx = DIRECCION_X[i];
                    mejorVy = DIRECCION_Y[i];
                }
            }
        }
        
        // Aplicar el movimiento en la mejor dirección encontrada
        if (mejorVx != 0 || mejorVy != 0) {
            this.setPosX(this.getPosX() + mejorVx);
            this.setPosY(this.getPosY() + mejorVy);
        }
    }
}