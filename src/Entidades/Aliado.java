package Entidades;
import java.util.ArrayList;

import Entidades.Contratos.InterfazAliado;
import Funciones.MisFunciones;

/**
 * Clase que representa un aliado en el juego Genetix Arena.
 * Los aliados huyen de los enemigos cuando se encuentran a una distancia cercana.
 * Implementa la interfaz InterfazAliado que define el comportamiento de escape.
 * 
 * @author Juanma Fdez
 * @version 1.0
 */
public class Aliado extends Entidad implements InterfazAliado{
    
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
     * El aliado identifica al enemigo más cercano y se mueve en dirección opuesta si está
     * dentro de un rango de 5 casillas. Utiliza una estrategia de movimiento por ejes
     * (primero intenta X, luego Y, y finalmente diagonal).
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
        Enemigo enemigo = null;
        double distanciaMinima = Integer.MAX_VALUE;
        
        // Encontrar el enemigo más cercano
        for (Enemigo enemigoDeLaLista : listaEnemigos) {
            double diferenciaX = this.posX - enemigoDeLaLista.posX;
            double diferenciaY = this.posY - enemigoDeLaLista.posY;
            double distancia = Math.sqrt(Math.pow(diferenciaX, 2) + Math.pow(diferenciaY, 2));
            
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                enemigo = enemigoDeLaLista;
            }
        }
        
        // Solo huye si el enemigo está dentro de un rango de 5 casillas
        if (distanciaMinima > 3) return;
        if (enemigo == null) return;
        
        int nuevaX = this.posX;
        int nuevaY = this.posY;
        
        // Intento moverme primero en X (en dirección opuesta al enemigo)
        if (enemigo.posX > this.posX) {
            nuevaX = this.posX - 1;
        } else if (enemigo.posX < this.posX) {
            nuevaX = this.posX + 1;
        }
        
        if (MisFunciones.posicionValida(nuevaX, this.posY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
            this.posX = nuevaX;
            return;
        } else {
            nuevaX = -nuevaX;
            if (MisFunciones.posicionValida(nuevaX, this.posY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
                this.posX = nuevaX;
                return;
            }
        }
        
        // Si X falla, intento en Y
        if (enemigo.posY > this.posY) {
            nuevaY = this.posY - 1;
        } else if (enemigo.posY < this.posY) {
            nuevaY = this.posY + 1;
        }
        
        if (MisFunciones.posicionValida(this.posX, nuevaY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
            this.posY = nuevaY;
            return;
        } else {
            nuevaY = -nuevaY;
            if (MisFunciones.posicionValida(this.posX, nuevaY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
                this.posY = nuevaY;
                return;
            }
        }
        
        // Si ambas fallan, intento con la diagonal
        if ((enemigo.posX > this.posX)&&(enemigo.posY > this.posY)) {
            nuevaX = this.posX - 1;
            nuevaY = this.posY - 1;
            if (MisFunciones.posicionValida(nuevaX, nuevaY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
                this.posX = nuevaX;
                this.posY = nuevaY;
            }
        } else if ((enemigo.posX < this.posX)&&(enemigo.posY < this.posY)) {
            nuevaX = this.posX + 1;
            nuevaY = this.posY + 1;
            if (MisFunciones.posicionValida(nuevaX, nuevaY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
                this.posX = nuevaX;
                this.posY = nuevaY;
            }
        }
    }
}