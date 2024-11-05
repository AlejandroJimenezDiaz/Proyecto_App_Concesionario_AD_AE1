package app;

import controller.GestionFlujo;
import dao.CocheDao;
import model.Coche;

import java.util.List;
import java.util.Scanner;

public class App {
    private Scanner scanner;
    private CocheDao cocheDao;
    private GestionFlujo gestionFlujo;

    /*Nuestro constructor, cargamos coches al inicio*/
    public App() {
        this.scanner = new Scanner(System.in);
        this.cocheDao = new CocheDao();
        this.gestionFlujo = new GestionFlujo();

        List<Coche> coches = gestionFlujo.lecturaCoches();
        cocheDao.setListaCoches(coches);
    }
    /*Es el unico metodo el cual vamos a poder acceder desde fuera, cuando creemos el objeto app podremos llamarlo
    y este pintara el menu para poder acceder al programa*/
    public void pintarMenu() {
        int opcion;
        do {
            System.out.println("<-------------------Menú--------------->");
            System.out.println("1---> Añadir nuevo coche");
            System.out.println("2---> Borrar coche por ID");
            System.out.println("3---> Consultar coche por ID");
            System.out.println("4---> Listar coches");
            System.out.println("5---> Exportar csv");
            System.out.println("6---> Salir del programa");
            System.out.println("<-----------Selecciona una opción--------->");
            System.out.println("Opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            manejarOpcion(opcion);

        } while (opcion != 6);

        // Guardar coches al salir
        gestionFlujo.escrituraCoches(cocheDao.listarCoches());
        System.out.println("Programa terminado. Coches guardados en coches.obj.");
        scanner.close();
    }
    /*Este metodo es llamado desde pintar menu, el cual tiene todos los metodos declarados previamente en nuestro
    programa, aqui se aplica la lógica para el usuario final*/
    private void manejarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                añadirCoche();
                break;
            case 2:
                borrarCoche();
                break;
            case 3:
                consultarCoche();
                break;
            case 4:
                listarCoches();
                break;
            case 5:
                exportarCochesACSV();
                break;
            case 6 :
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
    /*Este metodo hace una comprobacion por si la matrícula es igual, si es asi ya no deja meter más datos del coche
    he creado otro metodo para que genere el id de forma unica, aunque también es tratado al igual que la matrícula
    En el caso de introducir nueva matricula pedimos todos los parametros , se lo pasamos al contructor y creamos el nuevo objeto*/
    private void añadirCoche() {
        int id = generarId();

        System.out.print("Introduce la matrícula: ");
        String matricula = scanner.nextLine();
        if (cocheDao.consultarCochePorMatricula(matricula) != null) {
                System.out.println("Error: Ya existe un coche con la matrícula " + matricula);
                return;
            }

        System.out.print("Introduce la marca: ");
        String marca = scanner.nextLine();

        System.out.print("Introduce el modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Introduce el color: ");
        String color = scanner.nextLine();

        System.out.print("Introduce el precio: ");
        int precio = scanner.nextInt();
        scanner.nextLine();

            Coche coche = new Coche(id, matricula, marca, modelo, color, precio);
            if (cocheDao.añadirCoche(coche)) {
                System.out.println("Coche añadido con éxito.");
            } else {
                System.out.println("Error al añadir el coche.");
            }

    }
    /*Este metodo declara coge el id y almacena el id mayor encontrado , una vez que alzanza la mayor posicion id este
    se iguala al id obtenido y en la salida se suma 1*/
    private int generarId() {
        List<Coche> coches = cocheDao.listarCoches();
        int Id = 0;
        for (Coche coche : coches) {
            if (coche.getId() > Id) {
                Id = coche.getId();

            }
        }
        return Id+1;
    }
    /*Aqui pedimos por consola que introduza un id , simplemente llamamos a nuestro metodo y que haga la comprobacion
    y en caso de existir ya lo puede borrar*/
    private void borrarCoche() {
        System.out.print("Introduce el ID del coche a borrar: ");
        int id = scanner.nextInt();
        if (cocheDao.borrarCochePorId(id)) {
            System.out.println("Coche borrado con éxito.");
        } else {
            System.out.println("Coche no encontrado.");
        }
    }
    /*Este metodo pide el id por consola y llama al metodo para consultar el coche en la lista*/
    private void consultarCoche() {
        System.out.print("Introduce el ID del coche a consultar: ");
        int id = scanner.nextInt();
        Coche coche = cocheDao.consultarCochePorId(id);
        if (coche != null) {
            System.out.println(coche);
        } else {
            System.out.println("Coche no encontrado.");
        }
    }
    /*Este metodo nos muestra todos los coches por consola , cargamos la lista y le preguntamos si esta vacia ,
    si no es asi recorremos todos los elementos de la lista imprimiendo cada coche*/
    private void listarCoches() {
        List<Coche> coches = cocheDao.listarCoches();
        if (coches.isEmpty()) {
            System.out.println("No hay coches registrados.");
        } else {
            for (Coche coche : coches) {
                System.out.println(coche);
            }
        }
    }

    private void exportarCochesACSV() {
        gestionFlujo.exportarCSV(cocheDao.listarCoches());
    }
}
