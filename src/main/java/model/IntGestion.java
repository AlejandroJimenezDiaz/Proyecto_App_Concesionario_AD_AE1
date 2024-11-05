package model;

import java.util.List;

public interface IntGestion {
    /*He creado la interface que recoge los metodos necesarios para nuestro programa*/

    /*Metodos de modificación*/
    boolean añadirCoche(Coche coche);
    boolean borrarCochePorId(int id);
    /*Metodos de consulta*/
    Coche consultarCochePorId(int id);
    List<Coche> listarCoches();
}
