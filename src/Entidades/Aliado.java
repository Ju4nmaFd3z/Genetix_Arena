package Entidades;
import Entidades.Contratos.MovimientoAliado;
import Funciones.MisFunciones;

public class Aliado extends Entidad implements MovimientoAliado {
    public Aliado(String[][] matriz){
        super(matriz);
    }

    @Override
    public void Escapa() {
        MisFunciones.escapar();
    }
}
