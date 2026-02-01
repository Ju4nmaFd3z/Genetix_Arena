package Entidades;

public class Entidad {
    public int posY;
    public int posX;
    public Entidad(String[][] mapa){
        int alto = mapa.length;
        int ancho = mapa[0].length;
        this.posY = (int)(Math.random()*(alto-1));
        this.posX = (int)(Math.random()*(ancho-1));
    }
}
