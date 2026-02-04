package Funciones;

import java.util.ArrayList;

import Entidades.Aliado;
import Entidades.Curandero;
import Entidades.Enemigo;
import Entidades.Obstaculo;

public class MisFunciones {
    public static void pintarMapa(String[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;
        System.out.print("╔═══");
        for (int j = 0; j < columnas - 1; j++) {
            System.out.print("╦═══");
        }
        System.out.println("╗");
        for (int i = 0; i < filas; i++) {
            System.out.print("║ ");
            for (int j = 0; j < columnas; j++) {
                System.out.printf("%s ", matriz[i][j]);
                if (j < columnas - 1) {
                    System.out.print("║ ");
                }
            }
            System.out.println("║");
            if (i < filas - 1) {
                System.out.print("╠═══");
                for (int j = 0; j < columnas - 1; j++) {
                    System.out.print("╬═══");
                }
                System.out.println("╣");
            }
        }
        System.out.print("╚═══");
        for (int j = 0; j < columnas - 1; j++) {
            System.out.print("╩═══");
        }
        System.out.println("╝");
    }

    public static boolean casillaVacia(int fila, int columna, String[][] mapa){
        return mapa[fila][columna].equals(" ");
    }

    public static boolean posicionValida(int xDestino, int yDestino, int ALTO, int ANCHO, 
                                         ArrayList<Aliado> listaAliados, ArrayList<Enemigo> listaEnemigos, 
                                         ArrayList<Obstaculo> listaObstaculos, ArrayList<Curandero> listaCuranderos){
        if ((xDestino>=0&&xDestino<ANCHO)&&(yDestino>=0&&yDestino<ALTO)){
            for (Obstaculo obstaculo : listaObstaculos) {
                if (obstaculo.posX == xDestino && obstaculo.posY == yDestino)
                    return false;
            }
            for (Aliado aliado : listaAliados) {
                if (aliado.posX == xDestino && aliado.posY == yDestino)
                    return false;
            }
            for (Enemigo enemigo : listaEnemigos) {
                if (enemigo.posX == xDestino && enemigo.posY == yDestino)
                    return false;
            }
            for (Curandero curandero : listaCuranderos) {
                if (curandero.posX == xDestino && curandero.posY == yDestino)
                    return false;
            }
            return true;
        }
        return false;
    }
}