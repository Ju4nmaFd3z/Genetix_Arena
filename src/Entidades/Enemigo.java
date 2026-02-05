package Entidades;
import java.util.ArrayList;

import Entidades.Contratos.InterfazEnemigo;
import Funciones.MisFunciones;

public class Enemigo extends Entidad implements InterfazEnemigo{
    public Enemigo(String[][] matriz){
        super(matriz);
    }

    public void Persigue(ArrayList<Aliado> listaAliados, int ALTO, int ANCHO,
                         ArrayList<Enemigo> listaEnemigos, ArrayList<Obstaculo> listaObstaculos, 
                         ArrayList<Curandero> listaCuranderos) {
        Aliado objetivo = null;
        double distanciaMinima = Integer.MAX_VALUE;
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
        }
        // Si X falla, intento en Y
        nuevaX = this.posX;
        if (objetivo.posY > this.posY) {
            nuevaY = this.posY + 1;
        } else if (objetivo.posY < this.posY) {
            nuevaY = this.posY - 1;
        }
        if (MisFunciones.posicionValida(this.posX, nuevaY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
            this.posY = nuevaY;
            return;
        }
        // Si ambas fallan, intento diagonal como último recurso
        if (objetivo.posX > this.posX) {
            nuevaX = this.posX + 1;
        } else if (objetivo.posX < this.posX) {
            nuevaX = this.posX - 1;
        }
        if (objetivo.posY > this.posY) {
            nuevaY = this.posY + 1;
        } else if (objetivo.posY < this.posY) {
            nuevaY = this.posY - 1;
        }
        if (MisFunciones.posicionValida(nuevaX, nuevaY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
            this.posX = nuevaX;
            this.posY = nuevaY;
        }
    }
}