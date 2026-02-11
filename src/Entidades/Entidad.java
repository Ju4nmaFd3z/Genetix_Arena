package Entidades;
import Funciones.MisFunciones;

/**
 * Clase base que representa una entidad en el juego Genetix Arena.
 * Define las propiedades comunes de todas las entidades (posición y vida)
 * e inicializa su ubicación aleatoria en el mapa.
 * 
 * @author Juanma Fdez
 * @version 1.0
 */
public class Entidad {
    
    /**
     * Coordenada Y (fila) de la entidad en el mapa.
     */
    public int posY;
    
    /**
     * Coordenada X (columna) de la entidad en el mapa.
     */
    public int posX;
    
    /**
     * Valor de vida de la entidad. Rango típico: 0-100.
     */
    public int vida;
    
    /**
     * Constructor que inicializa una entidad en una posición aleatoria válida del mapa.
     * Genera coordenadas aleatorias hasta encontrar una casilla vacía donde colocar la entidad.
     * Inicializa la vida con un valor de 100 puntos.
     * 
     * @param mapa La matriz bidimensional que representa el mapa del juego.
     *             Se utiliza para determinar los límites y encontrar casillas vacías.
     */
    public Entidad(String[][] mapa){
        int alto = mapa.length;
        int ancho = mapa[0].length;
        do {
            this.posY = (int)(Math.random()*(alto-1));
            this.posX = (int)(Math.random()*(ancho-1));
        } while (!MisFunciones.casillaVacia(this.posY, this.posX, mapa));
        this.vida = 100;
    }
}