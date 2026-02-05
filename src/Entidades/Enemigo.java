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
        int distanciaMinima = Integer.MAX_VALUE;
        for (Aliado aliado : listaAliados) {
            int distancia = Math.abs(this.posY - aliado.posY) + Math.abs(this.posX - aliado.posX);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                objetivo = aliado;
            }
        }
        if (objetivo == null) return;
        int nuevaX = this.posX;
        int nuevaY = this.posY;
        // Intento moverme primero en X, y si falla, pruebo en Y
        if (objetivo.posX > this.posX) {
            nuevaX = this.posX + 1;
        } else if (objetivo.posX < this.posX) {
            nuevaX = this.posX - 1;
        }
        if (MisFunciones.posicionValida(nuevaX, this.posY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
            this.posX = nuevaX;
            return;
        }
        if (objetivo.posY > this.posY) {
            nuevaY = this.posY + 1;
        } else if (objetivo.posY < this.posY) {
            nuevaY = this.posY - 1;
        }
        if (MisFunciones.posicionValida(this.posX, nuevaY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
            this.posY = nuevaY;
        }
    }
}