package Entidades;
import java.util.ArrayList;

import Funciones.MisFunciones;

/**
 * Clase que representa un curandero en el juego Genetix Arena.
 * Los curanderos se mueven hacia los aliados más heridos dentro de un rango de 10 casillas
 * y los curan cuando están a una distancia de 1 casilla.
 * 
 * Utiliza un sistema inteligente de movimiento que evalúa las 8 casillas adyacentes
 * y elige la mejor dirección basada en acercarse al aliado más herido.
 * 
 * @author Juanma Fdez
 * @version 3.0
 */
public class Curandero extends Entidad {
    
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
     * Constructor que crea un curandero en una posición aleatoria válida del mapa.
     * Delega la inicialización a la clase padre Entidad.
     * 
     * @param matriz La matriz bidimensional que representa el mapa del juego.
     *               Se utiliza para encontrar una posición válida para el curandero.
     */
    public Curandero(String[][] matriz){
        super(matriz);
    }

    /**
     * Implementa el comportamiento de curación del curandero.
     * El curandero busca aliados dentro de un rango de 10 casillas que tengan baja vida.
     * Se mueve hacia el aliado más herido evaluando las 8 casillas adyacentes y eligiendo
     * la dirección que lo acerca más al objetivo. Lo cura cuando está a una distancia de 1 casilla,
     * restaurando 50 puntos de vida con un máximo de 100.
     * 
     * @param listaAliados Lista de aliados a curar
     * @param ALTO Alto del mapa (límite de movimiento)
     * @param ANCHO Ancho del mapa (límite de movimiento)
     * @param listaEnemigos Lista de enemigos (para verificar colisiones)
     * @param listaObstaculos Lista de obstáculos (para verificar colisiones)
     * @param listaCuranderos Lista de curanderos (para verificar colisiones)
     */
    public void Cura(ArrayList<Aliado> listaAliados, int ALTO, int ANCHO,
                    ArrayList<Enemigo> listaEnemigos, ArrayList<Obstaculo> listaObstaculos, 
                    ArrayList<Curandero> listaCuranderos) {
        
        // Encontrar el aliado más herido dentro de rango
        Aliado aliadoMasHerido = null;
        int menorVida = Integer.MAX_VALUE;
        double distanciaAliadoMasHerido = Double.MAX_VALUE;

        for (Aliado aliado : listaAliados) {
            double distancia = this.getDistancia(aliado);
            
            // Si está dentro del rango y tiene menos vida que los anteriores
            if (distancia <= 10 && aliado.getVida() < menorVida) {
                menorVida = aliado.getVida();
                aliadoMasHerido = aliado;
                distanciaAliadoMasHerido = distancia;
            }
        }

        // Si no hay aliados dentro de rango, no hacer nada
        if (aliadoMasHerido == null) {
            return;
        }

        // Si el aliado está lo suficientemente cerca, curarlo
        if (distanciaAliadoMasHerido <= 1) {
            aliadoMasHerido.modificarVida(50);
            return; // Priorizar curación sobre movimiento
        }

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
                
                // Calcular distancia desde esta nueva posición al aliado más herido
                double distancia = posicionPrueba.getDistancia(aliadoMasHerido);
                
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