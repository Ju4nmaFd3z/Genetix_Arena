package Entidades.Contratos;

import java.util.ArrayList;

import Entidades.Aliado;
import Entidades.Curandero;
import Entidades.Enemigo;
import Entidades.Obstaculo;

/**
 * Interfaz que define el contrato para el comportamiento de curación de aliados.
 * Las clases que implementen esta interfaz deben proporcionar el método Cura
 * para definir cómo el curandero cura a los aliados más heridos.
 * 
 * @author Juanma Fdez
 * @version 3.0
 */
public interface InterfazCurandero {
    
    /**
     * Define el comportamiento de curación del curandero.
     * El curandero debe localizar al aliado más herido dentro de un rango de 10 casillas,
     * desplazarse hacia él utilizando un sistema inteligente de movimiento que evalúa
     * las 8 direcciones posibles, y curarlo cuando esté a una distancia de 1 casilla,
     * restaurando 50 puntos de vida.
     * 
     * @param listaAliados Lista de aliados a curar
     * @param ALTO Alto del mapa (límite de movimiento)
     * @param ANCHO Ancho del mapa (límite de movimiento)
     * @param listaEnemigos Lista de enemigos en el juego (para evitar colisiones)
     * @param listaObstaculos Lista de obstáculos (para evitar colisiones)
     * @param listaCuranderos Lista de curanderos (para evitar colisiones)
     */
    public void Cura(ArrayList<Aliado> listaAliados, int ALTO, int ANCHO,
                     ArrayList<Enemigo> listaEnemigos, ArrayList<Obstaculo> listaObstaculos, 
                     ArrayList<Curandero> listaCuranderos);
}