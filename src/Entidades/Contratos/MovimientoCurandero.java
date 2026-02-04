package Entidades.Contratos;
import java.util.ArrayList;

import Entidades.Entidad;

public interface MovimientoCurandero {
    public Entidad BuscaAliadoMasHerido(ArrayList<Entidad> aliados);
    public void Cura(Entidad aliado);
}