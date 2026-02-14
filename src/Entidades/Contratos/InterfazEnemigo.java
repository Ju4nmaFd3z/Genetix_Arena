package Entidades.Contratos;

import java.util.ArrayList;

import Entidades.Aliado;
import Entidades.Curandero;
import Entidades.Enemigo;
import Entidades.Obstaculo;

/**
 * Interfaz que define el contrato para el comportamiento de persecución de enemigos.
 * Las clases que implementen esta interfaz deben proporcionar el método Persigue
 * para definir cómo el enemigo persigue a los aliados.
 * 
 * @author Juanma Fdez
 * @version 3.0
 */
public interface InterfazEnemigo {
    
    /**
     * Define el comportamiento de persecución del enemigo.
     * El enemigo debe localizar y perseguir al aliado más cercano.
     * 
     * @param listaAliados Lista de aliados a perseguir
     * @param ALTO Alto del mapa (límite de movimiento)
     * @param ANCHO Ancho del mapa (límite de movimiento)
     * @param listaEnemigos Lista de enemigos en el juego (para evitar colisiones)
     * @param listaObstaculos Lista de obstáculos (para evitar colisiones)
     * @param listaCuranderos Lista de curanderos (para evitar colisiones)
     */
    public void Persigue(ArrayList<Aliado> listaAliados, int ALTO, int ANCHO,
                         ArrayList<Enemigo> listaEnemigos, ArrayList<Obstaculo> listaObstaculos, 
                         ArrayList<Curandero> listaCuranderos);
}