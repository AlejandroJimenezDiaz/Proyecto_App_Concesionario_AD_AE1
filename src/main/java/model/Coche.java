package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor@AllArgsConstructor@Getter@Setter
public class Coche implements Serializable {
    /* Clase Coche, contiene el modelo de datos que queremos almacenar en memoria. Como es un objeto tiene que ser serializable
    hemos utilizado la libreria lombok para generar (getters,setters,contructor con y sin )*/
    private static final long serialVersionUID = 1L;
    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;
    private int precio;
    /*Aqui generamos los metodos equals/hasCode selecionando los atributos id y matricula . No pueden coincidir la matricula,
    tampoco puede coincidir el id*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coche coche)) return false;
        return id == coche.id || Objects.equals(matricula, coche.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matricula);
    }
    /*Generamos el metodo ToString para tener los datos del objeto coche en su conjunto*/
    @Override
    public String toString() {
        return "Coche{" +
                "id=" + id +
                ", matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", precio=" + precio +
                '}';
    }
}
