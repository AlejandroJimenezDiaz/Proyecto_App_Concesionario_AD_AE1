package dao;

import model.Coche;
import model.IntGestion;

import java.util.ArrayList;
import java.util.List;

public class CocheDao implements IntGestion {
    /*Implementamos los metodos de nuestra interface IntGestio*/
    /*Estos metodos trabajan la parte logica en cuanto a la base de datos, no tenemos una base de datos como tal asi
    que tendremos que cargar los archivos en una lista*/

    private List<Coche> ListaCoches;

    /*Este es nuestro contructor de la clase*/
    public CocheDao() {
        ListaCoches = new ArrayList<>();
    }
    /*Este metodo me permite cargar posteriormente la lista de coches*/
    public void setListaCoches(List<Coche> listaCoches) {
        this.ListaCoches = listaCoches;
    }
    /*Este metodo busca dentro de la lista coche obteniendo la matrícula de cada objeto coche y comprandola con la
    la matrícula del objeto coche que se le pasa como parametro de entrada*/
    @Override
    public boolean añadirCoche(Coche coche) {
        for (Coche buscar : ListaCoches) {
            if (buscar.getMatricula().equals(coche.getMatricula())) {
                System.out.println("Ya esiste el coche con la matricula : " + coche.getMatricula());
                return false;
            }else {
                ListaCoches.add(buscar);
            }

        }
        return false;
    }
    /*La metodologia es la misma que en el metodo anterior, recorremos la lista y sacamos el parametro que queremos
    comparar y lo comparamos con el parametro de entrada*/
    @Override
    public boolean borrarCochePorId(int id) {
        for (Coche borrar : ListaCoches) {
            if(borrar.getId()==id){
                ListaCoches.remove(borrar);
                return true;
            }
        }
        return false;
    }
    /*Igual que el metodo anterior pero nos devuelve un objeto de la clase coche en vez de un boolean*/
    @Override
    public Coche consultarCochePorId(int id) {
        for (Coche consultar : ListaCoches) {
            if(consultar.getId()==id){
                return consultar;
            }
        }
        return null;
    }
    /*Retornamos la lista de coches*/
    @Override
    public List<Coche> listarCoches() {
        return ListaCoches;
    }
    /*Este metodo se ha creado para poder detectar la matricula directamente recibiendo un string, asi en la clase App
    podemos llamarlo y agilizar la deteccion de repeticion de matriculas evitando tener que introducir todos los atributos
    de la clase coche*/
    public Coche consultarCochePorMatricula(String matricula) {
        for (Coche consultar : ListaCoches) {
            if(consultar.getMatricula().equals(matricula)){
                return consultar;
            }
        }return null;
    }

}
