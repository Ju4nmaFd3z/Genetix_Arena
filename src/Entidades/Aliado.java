package Entidades;
import Entidades.Contratos.MovimientoAliado;

public class Aliado extends Entidad implements MovimientoAliado {
    public Aliado(String[][] matriz){
        super(matriz);
    }

    @Override
    public void Escapa() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Escapa'");
    }
}
