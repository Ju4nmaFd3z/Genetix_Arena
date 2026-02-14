package Entidades;
import Funciones.MisFunciones;

/**
 * Clase base que representa una entidad en el juego Genetix Arena.
 * Define las propiedades comunes de todas las entidades (posición y vida)
 * e inicializa su ubicación aleatoria en el mapa.
 * 
 * Utiliza encapsulación con variables privadas y métodos de acceso (getters/setters).
 * 
 * @author Juanma Fdez
 * @version 3.0
 */
public class Entidad {
    
    /**
     * Coordenada Y (fila) de la entidad en el mapa (PRIVADA).
     */
    private int posY;
    
    /**
     * Coordenada X (columna) de la entidad en el mapa (PRIVADA).
     */
    private int posX;
    
    /**
     * Valor de vida de la entidad. Rango típico: 0-100 (PRIVADA).
     */
    private int vida;
    
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
            this.posY = (int)(Math.random() * alto);
            this.posX = (int)(Math.random() * ancho);
        } while (!MisFunciones.casillaVacia(this.posY, this.posX, mapa));
        this.vida = 100;
    }
    
    /**
     * Constructor alternativo para crear una entidad sin validación de mapa.
     * Utilizado internamente para cálculos de distancia.
     * 
     * @param x Coordenada X inicial
     * @param y Coordenada Y inicial
     */
    public Entidad(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.vida = 100;
    }
    
    /**
     * Obtiene la posición X (columna) de la entidad.
     * 
     * @return Coordenada X de la entidad
     */
    public int getPosX() {
        return posX;
    }
    
    /**
     * Establece la posición X (columna) de la entidad.
     * 
     * @param posX Nueva coordenada X
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }
    
    /**
     * Obtiene la posición Y (fila) de la entidad.
     * 
     * @return Coordenada Y de la entidad
     */
    public int getPosY() {
        return posY;
    }
    
    /**
     * Establece la posición Y (fila) de la entidad.
     * 
     * @param posY Nueva coordenada Y
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    /**
     * Obtiene el valor de vida de la entidad.
     * 
     * @return Vida actual de la entidad
     */
    public int getVida() {
        return vida;
    }
    
    /**
     * Establece el valor de vida de la entidad.
     * Con validación: no permite valores menores a 0 ni mayores a 100.
     * 
     * @param vida Nuevo valor de vida (será ajustado al rango 0-100)
     */
    public void setVida(int vida) {
        if (vida < 0) {
            this.vida = 0;
        } else if (vida > 100) {
            this.vida = 100;
        } else {
            this.vida = vida;
        }
    }
    
    /**
     * Calcula la distancia euclidiana entre esta entidad y otra.
     * Utiliza la fórmula: distancia = sqrt((x2-x1)² + (y2-y1)²)
     * 
     * @param otra La otra entidad a la cual calcular la distancia
     * @return Distancia euclidiana entre las dos entidades
     */
    public double getDistancia(Entidad otra) {
        double dx = this.posX - otra.getPosX();
        double dy = this.posY - otra.getPosY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    /**
     * Suma el valor especificado a la vida actual.
     * Útil para curación y daño.
     * 
     * @param cantidad Cantidad a sumar (puede ser negativa para daño)
     */
    public void modificarVida(int cantidad) {
        setVida(this.vida + cantidad);
    }
}