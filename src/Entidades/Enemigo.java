package Entidades;
import Entidades.Contratos.MovimientoEnemigo;
import Funciones.MisFunciones;

public class Enemigo extends Entidad implements MovimientoEnemigo {
    public Enemigo(String[][] matriz){
        super(matriz);
    }

    @Override
    public void Persigue() {
        MisFunciones.perseguir();
    }
}
