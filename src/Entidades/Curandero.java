package Entidades;
import java.util.ArrayList;

import Entidades.Contratos.MovimientoCurandero;

public class Curandero extends Entidad implements MovimientoCurandero{
    public Curandero(String[][] matriz){
        super(matriz);
    }

    @Override
    public Entidad BuscaAliadoMasHerido(ArrayList<Entidad> aliados) {
        Entidad aliadoMasHerido = null;
        int menorVida = Integer.MAX_VALUE;
        for (Entidad aliado : aliados) {
            if (aliado.vida < menorVida) {
                menorVida = aliado.vida;
                aliadoMasHerido = aliado;
            }
        }
        return aliadoMasHerido;
    }

    @Override
    public void Cura(Entidad aliado) {
        //IR HASTA ALIADO
        aliado.vida = 100;
    }
}
