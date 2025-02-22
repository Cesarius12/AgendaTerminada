package agenda;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Agenda implements Serializable {

    private ArrayList<Contactos> misContactos = new ArrayList();
    private transient Scanner sc = new Scanner(System.in);

    public void generaAgenda(String nombre) {
        try {
            ObjectOutputStream miFichero = new ObjectOutputStream(new FileOutputStream("agenda" + nombre + ".dat"));
            miFichero.writeObject(misContactos);
            miFichero.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<Contactos> cargarAgenda(String nombre) {
        ArrayList<Contactos> misContCargados = new ArrayList<>();
        try {
            ObjectInputStream miCarga = new ObjectInputStream(new FileInputStream("agenda" + nombre + ".dat"));
            misContCargados = (ArrayList<Contactos>) miCarga.readObject();
            miCarga.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return misContCargados;
    }
    public void registrarContacto() {
        boolean val = true;
        System.out.println("Introduce el nombre del contacto que deseas agregar");
        String nombre = sc.next();
        System.out.println("Ahora introduce los apellidos del contacto");
        String apellidos = sc.next();
        for (Contactos actual : misContactos) {
            if (nombre.equalsIgnoreCase(actual.getNombre()) && apellidos.equalsIgnoreCase(actual.getApellidos())) {
                System.out.println("Lo sentimos ya existe un contacto con ese nombre y apellidos por lo cual es imposible crear el contacto");
                val = false;
            }
        }
        if (val) {
            System.out.println("A continuacion introduce el telefono");
            int telefono = sc.nextInt();
            System.out.println("Ahora registra el email del contacto");
            String email = sc.next();
            System.out.println("por ultimo introduce la edad del contacto");
            int edad = sc.nextInt();
            Contactos c1 = new Contactos(nombre, apellidos, telefono, email, edad);
            misContactos.add(c1);
        }
    }

    public void verContactos() {
        for (Contactos actual : misContactos) {
            actual.mostrarInfo();
            System.out.println("---------------");
        }
    }

    public boolean buscarContacto() {
        String nombre, apel;
        System.out.println("Debe coincidir exactamente con el contacto para poder encontrarlo");
        System.out.println("Introduce el nombre que deseas buscar: ");
        nombre = sc.next();
        System.out.println("Introduce el apellido o apellidos que deseas buscar");
        apel = sc.next();
        for (Contactos actual : misContactos) {
            if (actual.getNombre().equalsIgnoreCase(nombre) && actual.getApellidos().equalsIgnoreCase(apel)) {
                actual.mostrarInfo();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean eliminarContacto() {
        System.out.println("Para eliminar el contacto los datos deben de ser iguales a los de la agenda de contactos");
        String nombre, apel;
        System.out.println("Introduce el nombre que deseas buscar: ");
        nombre = sc.next();
        System.out.println("Introduce el apellido o apellidos que deseas buscar");
        apel = sc.next();
        for (Contactos actual : misContactos) {
            if (actual.getNombre().contentEquals(nombre) && actual.getApellidos().contentEquals(apel)) {
                misContactos.remove(actual);
                return true;
            }
        }
        return false;
    }

    public void setMisContactos(ArrayList<Contactos> misContactos) {
        this.misContactos = misContactos;
    }

}
