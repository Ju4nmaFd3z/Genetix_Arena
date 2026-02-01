package Funciones;

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
}
