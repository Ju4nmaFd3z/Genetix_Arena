package Entidades;
import Entidades.Contratos.MovimientoEnemigo;

public class Enemigo extends Entidad implements MovimientoEnemigo {
    public Enemigo(String[][] matriz){
        super(matriz);
    }

    @Override
    public void Persigue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Persigue'");
    }
}
