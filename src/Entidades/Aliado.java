package Entidades;
import java.util.ArrayList;

import Entidades.Contratos.InterfazAliado;
import Funciones.MisFunciones;

public class Aliado extends Entidad implements InterfazAliado{
    public Aliado(String[][] matriz){
        super(matriz);
    }

    public void Escapa(ArrayList<Enemigo> listaEnemigos, int ALTO, int ANCHO,
                       ArrayList<Aliado> listaAliados, ArrayList<Obstaculo> listaObstaculos, 
                       ArrayList<Curandero> listaCuranderos) {
        Enemigo enemigo = null;
        double distanciaMinima = Integer.MAX_VALUE;
        for (Enemigo enemigoDeLaLista : listaEnemigos) {
            double diferenciaX = this.posX - enemigoDeLaLista.posX;
            double diferenciaY = this.posY - enemigoDeLaLista.posY;
            double distancia = Math.sqrt(Math.pow(diferenciaX, 2) + Math.pow(diferenciaY, 2));
            
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                enemigo = enemigoDeLaLista;
            }
        }
        if (enemigo == null) return;
        int nuevaX = this.posX;
        int nuevaY = this.posY;
        // Intento moverme primero en X
        if (enemigo.posX > this.posX) {
            nuevaX = this.posX - 1;
        } else if (enemigo.posX < this.posX) {
            nuevaX = this.posX + 1;
        }
        if (MisFunciones.posicionValida(nuevaX, this.posY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
            this.posX = nuevaX;
            return;
        }
        // Si X falla, intento en Y
        nuevaX = this.posX;
        if (enemigo.posY > this.posY) {
            nuevaY = this.posY - 1;
        } else if (enemigo.posY < this.posY) {
            nuevaY = this.posY + 1;
        }
        if (MisFunciones.posicionValida(this.posX, nuevaY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
            this.posY = nuevaY;
            return;
        }
        // Si ambas fallan, intento diagonal como último recurso
        nuevaX = this.posX;
        nuevaY = this.posY;
        if (enemigo.posX > this.posX) {
            nuevaX = this.posX - 1;
        } else if (enemigo.posX < this.posX) {
            nuevaX = this.posX + 1;
        }
        if (enemigo.posY > this.posY) {
            nuevaY = this.posY - 1;
        } else if (enemigo.posY < this.posY) {
            nuevaY = this.posY + 1;
        }
        if (MisFunciones.posicionValida(nuevaX, nuevaY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
            this.posX = nuevaX;
            this.posY = nuevaY;
        }
    }
}