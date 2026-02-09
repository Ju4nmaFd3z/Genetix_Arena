package Entidades;
import java.util.ArrayList;

import Funciones.MisFunciones;

public class Curandero extends Entidad {
    public Curandero(String[][] matriz){
        super(matriz);
    }

    public void Cura(ArrayList<Aliado> listaAliados, int ALTO, int ANCHO,
                    ArrayList<Enemigo> listaEnemigos, ArrayList<Obstaculo> listaObstaculos, 
                    ArrayList<Curandero> listaCuranderos) {
        Aliado aliadoMasHerido = null;
        int menorVida = 100;
        double distanciaMinima = Integer.MAX_VALUE;

        for (Aliado aliado : listaAliados) {
            double diferenciaX = this.posX - aliado.posX;
            double diferenciaY = this.posY - aliado.posY;
            double distancia = Math.sqrt(Math.pow(diferenciaX, 2) + Math.pow(diferenciaY, 2));

            if (distancia <= 10 && aliado.vida < menorVida) {
                menorVida = aliado.vida;
                aliadoMasHerido = aliado;
                distanciaMinima = distancia;
            }
        }

        if (aliadoMasHerido != null) {
            // Intentar moverse en X
            if (aliadoMasHerido.posX > this.posX) {
                if (MisFunciones.posicionValida(this.posX + 1, this.posY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
                    this.posX++;
                    return;
                }
            } else if (aliadoMasHerido.posX < this.posX) {
                if (MisFunciones.posicionValida(this.posX - 1, this.posY, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
                    this.posX--;
                    return;
                }
            }
            // Intentar moverse en Y
            if (aliadoMasHerido.posY > this.posY) {
                if (MisFunciones.posicionValida(this.posX, this.posY + 1, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
                    this.posY++;
                    return;
                }
            } else if (aliadoMasHerido.posY < this.posY) {
                if (MisFunciones.posicionValida(this.posX, this.posY - 1, ALTO, ANCHO, listaAliados, listaEnemigos, listaObstaculos, listaCuranderos)) {
                    this.posY--;
                    return;
                }
            }
        }

        if (aliadoMasHerido != null && distanciaMinima <= 1) {
            aliadoMasHerido.vida = Math.min(aliadoMasHerido.vida + 50, 100); // +50 vida si lo permite, si sobrepasa, 100
        }
    }
}