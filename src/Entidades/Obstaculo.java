package Entidades;

/**
 * Clase que representa un obstáculo en el mapa del juego Genetix Arena.
 * Los obstáculos son entidades estáticas que bloquean el movimiento de otras entidades.
 * Se heredan las propiedades de posición y vida de la clase Entidad.
 * 
 * @author Juanma Fdez
 * @version 1.0
 */
public class Obstaculo extends Entidad {
    
    /**
     * Constructor que crea un obstáculo en una posición aleatoria válida del mapa.
     * Delega la inicialización a la clase padre Entidad.
     * 
     * @param matriz La matriz bidimensional que representa el mapa del juego.
     *               Se utiliza para encontrar una posición válida para el obstáculo.
     */
    public Obstaculo(String[][] matriz){
        super(matriz);
    }
}