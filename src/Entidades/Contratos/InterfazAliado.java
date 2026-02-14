package Entidades.Contratos;

import java.util.ArrayList;

import Entidades.Aliado;
import Entidades.Curandero;
import Entidades.Enemigo;
import Entidades.Obstaculo;

/**
 * Interfaz que define el contrato para el comportamiento de escape de aliados.
 * Las clases que implementen esta interfaz deben proporcionar el método Escapa
 * para definir cómo el aliado huye de los enemigos.
 * 
 * @author Juanma Fdez
 * @version 3.0
 */
public interface InterfazAliado {
    
    /**
     * Define el comportamiento de escape del aliado.
     * El aliado debe localizar al enemigo más cercano y huir en dirección opuesta
     * cuando se encuentre a una distancia de riesgo.
     * 
     * @param listaEnemigos Lista de enemigos de los que huir
     * @param ALTO Alto del mapa (límite de movimiento)
     * @param ANCHO Ancho del mapa (límite de movimiento)
     * @param listaAliados Lista de aliados en el juego (para evitar colisiones)
     * @param listaObstaculos Lista de obstáculos (para evitar colisiones)
     * @param listaCuranderos Lista de curanderos (para evitar colisiones)
     */
    public void Escapa(ArrayList<Enemigo> listaEnemigos, int ALTO, int ANCHO,
                       ArrayList<Aliado> listaAliados, ArrayList<Obstaculo> listaObstaculos, 
                       ArrayList<Curandero> listaCuranderos);
}