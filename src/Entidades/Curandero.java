package Entidades;
import java.util.ArrayList;

import Funciones.MisFunciones;

/**
 * Clase que representa un curandero en el juego Genetix Arena.
 * Los curanderos se mueven hacia los aliados más heridos dentro de un rango de 10 casillas
 * y los curan cuando están a una distancia de 1 casilla.
 * 
 * @author Juanma Fdez
 * @version 1.0
 */
public class Curandero extends Entidad {
    
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
     * Se mueve hacia el aliado más herido usando una estrategia de movimiento por ejes
     * (primero intenta X, luego Y) y lo cura cuando está a una distancia de 1 casilla,
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
        Aliado aliadoMasHerido = null;
        int menorVida = 100;
        double distanciaMinima = Integer.MAX_VALUE;

        // Encontrar el aliado más herido dentro de rango
        for (Aliado aliado : listaAliados) {
            double diferenciaX = this.posX - aliado.posX;
            double diferenciaY = this.posY - aliado.posY;
            double distancia = Math.sqrt(Math.pow(diferenciaX, 2) + Math.pow(diferenciaY, 2));

            if (distancia <= 10 && aliado.vida < menorVida) {
                menorVida = aliado.vida;
                aliadoMasHerido = aliado;
                distanciaMinima = distancia;
            }
        }

        if (aliadoMasHerido != null) {
            // Intentar moverse en X hacia el aliado
            if (aliadoMasHerido.posX > this.posX) {
                if (MisFunciones.posicionValida(this.posX + 1, this.posY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
                    this.posX++;
                    return;
                }
            } else if (aliadoMasHerido.posX < this.posX) {
                if (MisFunciones.posicionValida(this.posX - 1, this.posY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
                    this.posX--;
                    return;
                }
            }
            
            // Intentar moverse en Y hacia el aliado
            if (aliadoMasHerido.posY > this.posY) {
                if (MisFunciones.posicionValida(this.posX, this.posY + 1, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
                    this.posY++;
                    return;
                }
            } else if (aliadoMasHerido.posY < this.posY) {
                if (MisFunciones.posicionValida(this.posX, this.posY - 1, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
                    this.posY--;
                    return;
                }
            }
        }

        // Curar si está lo suficientemente cerca (distancia <= 1)
        if (aliadoMasHerido != null && distanciaMinima <= 1) {
            aliadoMasHerido.vida = Math.min(aliadoMasHerido.vida + 50, 100); // +50 vida si lo permite, si sobrepasa, 100
        }
    }
}