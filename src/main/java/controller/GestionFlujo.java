package controller;

import model.Coche;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionFlujo {
    /*Aqui tenemos los dos archivos en los cuales nos vamos a basar, en coches.obj haremos lectura y escritura
    y en coches.csv haremos la exportación*/
    private static final String cochesObj = "src/main/java/database/coches.obj";
    private static final String cochesCsv = "src/main/java/database/coches.csv";


    /*Este metodo de leer coches esta apuntando al archivo coches.obj, he creado una lista para poder almacenar los
    objetos ledios con la clase ObjectInputStream , vamos moviendo el puntero con el while hasta que este sea false ,
    cuando este es false manejamos ese error con EOFEXception*/
    public List<Coche> lecturaCoches() {
        ObjectInputStream objectInputStream = null;
        List<Coche> listaCoches = new ArrayList<>();
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(cochesObj));
            Coche coche;
            while (true) {
                coche = (Coche) objectInputStream.readObject();
                listaCoches.add(coche);
            }
        } catch (EOFException e) {
            /*Tras buscar información, encontré esta excepcion para controlar el final del archivo*/
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en lectura del archivo");
        } finally {
            try {
                objectInputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado del archivo");
            }
        }return listaCoches;
    }


    /*Declaramos el ObjectOutPutStream apuntando al archivo coches.obj, preparando asi el archivo para la escritura
    recorre la lista coche y escribe cada objeto coche en el archivo coches.obj y finalmente se tratan los posibles errores */
    public void escrituraCoches(List<Coche> listaCoches) {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(cochesObj));
            for (Coche coche : listaCoches) {
                objectOutputStream.writeObject(coche);
            }
        } catch (IOException e) {
            System.out.println("Error en escritura del archivo");
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }

    }

    /*Para exportar necesitamos recibir la lista de coches obtener sus atributos y formatearlo para escribirlo en formato
    csv, el bucle for es para recorrer todos los elementos de la lista*/
    public void exportarCSV(List<Coche> listaCoches) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(cochesCsv));
            bufferedWriter.write("ID;MATRICULA;MARCA;MODELO;COLOR;PRECIO");
            bufferedWriter.newLine();
            for (Coche coche : listaCoches) {
                bufferedWriter.write(String.format("%d;%s;%s;%s;%s;%d",
                        coche.getId(),
                        coche.getMatricula(),
                        coche.getMarca(),
                        coche.getModelo(),
                        coche.getColor(),
                        coche.getPrecio()));
                bufferedWriter.newLine();
                System.out.println("Coches exportados correctamente a : " + cochesCsv);
            }
        } catch (IOException e) {
            System.out.println("Error en el escritura del archivo");
        }finally {
            try {
                bufferedWriter.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }

    }

}
