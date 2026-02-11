package Entidades;
import java.util.ArrayList;

import Entidades.Contratos.InterfazEnemigo;
import Funciones.MisFunciones;

/**
 * Clase que representa un enemigo en el juego Genetix Arena.
 * Los enemigos persiguen a los aliados intentando eliminarlos.
 * Implementa la interfaz InterfazEnemigo que define el comportamiento de persecución.
 * 
 * @author Juanma Fdez
 * @version 1.0
 */
public class Enemigo extends Entidad implements InterfazEnemigo{
    
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
     * El enemigo identifica al aliado más cercano y se mueve hacia él utilizando
     * una estrategia de movimiento por ejes (primero intenta X, luego Y, y finalmente diagonal).
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
        Aliado objetivo = null;
        double distanciaMinima = Integer.MAX_VALUE;
        
        // Encontrar el aliado más cercano
        for (Aliado aliado : listaAliados) {
            double diferenciaX = this.posX - aliado.posX;
            double diferenciaY = this.posY - aliado.posY;
            double distancia = Math.sqrt(Math.pow(diferenciaX, 2) + Math.pow(diferenciaY, 2));

            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                objetivo = aliado;
            }
        }
        
        if (objetivo == null) return;
        
        int nuevaX = this.posX;
        int nuevaY = this.posY;
        
        // Intento moverme primero en X
        if (objetivo.posX > this.posX) {
            nuevaX = this.posX + 1;
        } else if (objetivo.posX < this.posX) {
            nuevaX = this.posX - 1;
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
        if (objetivo.posY > this.posY) {
            nuevaY = this.posY + 1;
        } else if (objetivo.posY < this.posY) {
            nuevaY = this.posY - 1;
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
        if ((objetivo.posX > this.posX)&&(objetivo.posY > this.posY)) {
            nuevaX = this.posX + 1;
            nuevaY = this.posY + 1;
            if (MisFunciones.posicionValida(nuevaX, nuevaY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
                this.posX = nuevaX;
                this.posY = nuevaY;
            }
        } else if ((objetivo.posX < this.posX)&&(objetivo.posY < this.posY)) {
            nuevaX = this.posX - 1;
            nuevaY = this.posY - 1;
            if (MisFunciones.posicionValida(nuevaX, nuevaY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
                this.posX = nuevaX;
                this.posY = nuevaY;
            }
        }
    }
}