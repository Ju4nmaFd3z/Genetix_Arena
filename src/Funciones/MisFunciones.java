package Funciones;

import java.util.ArrayList;

import Entidades.Aliado;
import Entidades.Curandero;
import Entidades.Enemigo;
import Entidades.Obstaculo;

/**
 * Clase con funciones utilitarias para el juego Genetix Arena.
 * Proporciona métodos para la gestión del mapa, validación de posiciones,
 * detección de colisiones y limpieza de entidades muertas.
 * 
 * @author Juanma Fdez
 * @version 3.0
 */
public class MisFunciones {
    
    /**
     * Pinta el mapa en la consola con bordes decorativos.
     * Construye el mapa completo en un StringBuilder antes de imprimirlo.
     * Dibuja un marco de caracteres especiales alrededor de la matriz
     * y muestra el contenido de cada casilla con separación espacial.
     * 
     * @param matriz La matriz bidimensional que representa el mapa
     */
    public static void pintarMapa(String[][] matriz) {
        StringBuilder mapa = new StringBuilder();
        int filas = matriz.length;
        int columnas = matriz[0].length;
        mapa.append("╔═");
        for (int j = 0; j < columnas - 1; j++) {
            mapa.append("══");
        }
        mapa.append("══╗\n");
        for (int i = 0; i < filas; i++) {
            mapa.append("║ ");
            for (int j = 0; j < columnas; j++) {
                mapa.append(matriz[i][j]+" ");
            }
            mapa.append("║\n");
        }
        mapa.append("╚═");
        for (int j = 0; j < columnas - 1; j++) {
            mapa.append("══");
        }
        mapa.append("══╝\n");
        System.out.print(mapa.toString());
    }

    /**
     * Verifica si una casilla específica del mapa está vacía.
     * 
     * @param fila Índice de la fila a verificar
     * @param columna Índice de la columna a verificar
     * @param mapa La matriz del mapa del juego
     * @return {@code true} si la casilla contiene un espacio en blanco, {@code false} en caso contrario
     */
    public static boolean casillaVacia(int fila, int columna, String[][] mapa){
        return mapa[fila][columna].equals(" ");
    }

    /**
     * Valida si una posición es accesible en el mapa.
     * Comprueba que la posición esté dentro de los límites del mapa
     * y que no haya obstáculos, aliados, enemigos ni curanderos en esa ubicación.
     * 
     * @param xDestino Coordenada X de la posición a validar
     * @param yDestino Coordenada Y de la posición a validar
     * @param ALTO Alto del mapa
     * @param ANCHO Ancho del mapa
     * @param listaAliados Lista de entidades aliadas en el juego
     * @param listaEnemigos Lista de entidades enemigas en el juego
     * @param listaObstaculos Lista de obstáculos en el mapa
     * @param listaCuranderos Lista de curanderos en el juego
     * @return {@code true} si la posición es válida y accesible, {@code false} si está fuera de límites o hay colisión
     */
    public static boolean posicionValida(int xDestino, int yDestino, int ALTO, int ANCHO, 
                                         ArrayList<Aliado> listaAliados, ArrayList<Enemigo> listaEnemigos, 
                                         ArrayList<Obstaculo> listaObstaculos, ArrayList<Curandero> listaCuranderos){
        // Verificar límites del mapa
        if ((xDestino < 0 || xDestino >= ANCHO) || (yDestino < 0 || yDestino >= ALTO)) {
            return false;
        }
        
        // Verificar colisión con obstáculos
        for (Obstaculo obstaculo : listaObstaculos) {
            if (obstaculo.getPosX() == xDestino && obstaculo.getPosY() == yDestino)
                return false;
        }
        
        // Verificar colisión con aliados
        for (Aliado aliado : listaAliados) {
            if (aliado.getPosX() == xDestino && aliado.getPosY() == yDestino)
                return false;
        }
        
        // Verificar colisión con enemigos
        for (Enemigo enemigo : listaEnemigos) {
            if (enemigo.getPosX() == xDestino && enemigo.getPosY() == yDestino)
                return false;
        }
        
        // Verificar colisión con curanderos
        for (Curandero curandero : listaCuranderos) {
            if (curandero.getPosX() == xDestino && curandero.getPosY() == yDestino)
                return false;
        }
        
        return true;
    }

    /**
     * Detecta y resuelve colisiones entre enemigos y aliados.
     * Si un enemigo y un aliado están en la misma posición o en posiciones adyacentes,
     * se reduce la vida de ambas entidades.
     * 
     * @param listaEnemigos Lista de enemigos en el juego
     * @param listaAliados Lista de aliados en el juego
     * @return Mensaje descriptivo del evento ocurrido
     */
    public static String detectarYResolverColisiones(ArrayList<Enemigo> listaEnemigos, ArrayList<Aliado> listaAliados) {
        String evento = "Por ahora nada...";
        for (int i = 0; i < listaEnemigos.size(); i++) {
            Enemigo enemigo = listaEnemigos.get(i);
            for (int j = 0; j < listaAliados.size(); j++) {
                Aliado aliado = listaAliados.get(j);
                
                // Calcular diferencias usando getters
                int diferenciaX = Math.abs(enemigo.getPosX() - aliado.getPosX());
                int diferenciaY = Math.abs(enemigo.getPosY() - aliado.getPosY());
                
                // Detectar colisión si están en la misma posición o adyacentes
                if ((diferenciaX == 0 && diferenciaY == 0) || (diferenciaX <= 1 && diferenciaY <= 1 && (diferenciaX + diferenciaY) <= 2)) {
                    enemigo.modificarVida(-25);
                    aliado.modificarVida(-35);
                    evento = "¡Enfrentamiento! Se están matando...";
                    return evento;
                }
            }
        }
        return evento;
    }

    /**
     * Limpia las entidades muertas del juego.
     * Elimina de las listas a todos los enemigos y aliados cuya vida sea menor o igual a cero.
     * 
     * @param listaEnemigos Lista de enemigos a limpiar
     * @param listaAliados Lista de aliados a limpiar
     */
    public static void limpiarMuertos(ArrayList<Enemigo> listaEnemigos, ArrayList<Aliado> listaAliados) {
        // Limpiar enemigos muertos
        for (int i = 0; i < listaEnemigos.size(); i++) {
            if (listaEnemigos.get(i).getVida() <= 0) {
                listaEnemigos.remove(i);
                i--; // Irme para atrás para no skippear la posición que se adelanta
            }
        }
        
        // Limpiar aliados muertos
        for (int i = 0; i < listaAliados.size(); i++) {
            if (listaAliados.get(i).getVida() <= 0) {
                listaAliados.remove(i);
                i--; // Irme para atrás para no skippear la posición que se adelanta
            }
        }
    }
}