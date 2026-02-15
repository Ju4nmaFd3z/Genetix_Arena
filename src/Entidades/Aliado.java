package Entidades;
import java.util.ArrayList;

import Entidades.Contratos.InterfazAliado;
import Funciones.MisFunciones;

/**
 * Clase que representa un aliado en el juego Genetix Arena.
 * Los aliados huyen de los enemigos cuando se encuentran a una distancia cercana.
 * Implementa la interfaz InterfazAliado que define el comportamiento de escape.
 * 
 * Utiliza un sistema inteligente de movimiento que evalúa las 8 casillas adyacentes
 * y elige la mejor dirección basada en alejarse del enemigo más cercano.
 * 
 * @author Juanma Fdez
 * @version 3.0
 */
public class Aliado extends Entidad implements InterfazAliado {
    
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
     * Constructor que crea un aliado en una posición aleatoria válida del mapa.
     * Delega la inicialización a la clase padre Entidad.
     * 
     * @param matriz La matriz bidimensional que representa el mapa del juego.
     *               Se utiliza para encontrar una posición válida para el aliado.
     */
    public Aliado(String[][] matriz){
        super(matriz);
    }

    /**
     * Implementa el comportamiento de escape del aliado.
     * El aliado identifica al enemigo más cercano y si está dentro de un rango de 3 casillas,
     * evalúa las 8 casillas adyacentes, eligiendo la dirección que lo aleje más del enemigo.
     * Evita obstáculos y otras entidades durante el movimiento.
     * 
     * @param listaEnemigos Lista de todos los enemigos (para identificar amenaza)
     * @param ALTO Alto del mapa (límite de movimiento)
     * @param ANCHO Ancho del mapa (límite de movimiento)
     * @param listaAliados Lista de todos los aliados (para verificar colisiones)
     * @param listaObstaculos Lista de obstáculos (para verificar colisiones)
     * @param listaCuranderos Lista de curanderos (para verificar colisiones)
     */
    public void Escapa(ArrayList<Enemigo> listaEnemigos, int ALTO, int ANCHO,
                       ArrayList<Aliado> listaAliados, ArrayList<Obstaculo> listaObstaculos, 
                       ArrayList<Curandero> listaCuranderos) {
        
        // Encontrar el enemigo más cercano
        Enemigo enemigo = null;
        double distanciaMinima = Double.MAX_VALUE;
        
        for (Enemigo enemigoDeLaLista : listaEnemigos) {
            double distancia = this.getDistancia(enemigoDeLaLista);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                enemigo = enemigoDeLaLista;
            }
        }
        
        // Solo huye si el enemigo está dentro de un rango de 3 casillas
        if (distanciaMinima > 3 || enemigo == null) return;
        
        // Variables para almacenar la mejor dirección encontrada (la que más lo aleja)
        double menorDistancia = 0;
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
                
                // Calcular distancia desde esta nueva posición al enemigo
                double distancia = posicionPrueba.getDistancia(enemigo);
                
                // Si esta dirección nos aleja más del enemigo, la guardamos
                if (distancia > menorDistancia) {
                    menorDistancia = distancia;
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