package Entidades;
import Entidades.Contratos.MovimientoCurandero;

public class Curandero extends Entidad implements MovimientoCurandero{
    public Curandero(String[][] matriz){
        super(matriz);
    }

    @Override
    public void BuscaHerido() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'BuscaHerido'");
    }

    @Override
    public void Cura() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Cura'");
    }
}
