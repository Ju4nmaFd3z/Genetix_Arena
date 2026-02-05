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
        int distanciaMinima = Integer.MAX_VALUE;
        for (Enemigo enemigoDeLaLista : listaEnemigos) {
            int distancia = Math.abs(this.posY - enemigoDeLaLista.posY) + Math.abs(this.posX - enemigoDeLaLista.posX);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                enemigo = enemigoDeLaLista;
            }
        }
        if (enemigo == null) return;
        int nuevoX = this.posX;
        int nuevoY = this.posY;
        // Al igual el enemigo al perseguir, intento moverme primero en X y si falla, pruebo en Y
        if (enemigo.posX > this.posX) {
            nuevoX = this.posX - 1;
        } else if (enemigo.posX < this.posX) {
            nuevoX = this.posX + 1;
        }
        if (MisFunciones.posicionValida(nuevoX, this.posY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
            this.posX = nuevoX;
            return;
        }
        if (enemigo.posY > this.posY) {
            nuevoY = this.posY - 1;
        } else if (enemigo.posY < this.posY) {
            nuevoY = this.posY + 1;
        }
        if (MisFunciones.posicionValida(this.posX, nuevoY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
            this.posY = nuevoY;
        }
    }
}