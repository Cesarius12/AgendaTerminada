package agenda;

import java.util.ArrayList;
import java.util.Scanner;

public class Interfaz {

    private transient static Scanner sc = new Scanner(System.in);

    public static char menu() {
        sc = new Scanner(System.in);
        char opcion = ' ';
        boolean val = false;
        System.out.println("Bienvenido");
        System.out.println("Que opcion desea realizar:");
        do {
            System.out.println("1- Crear un nuevo usuario");
            System.out.println("2- Logarse como usuario");
            System.out.println("------------------------------");

            String resp = sc.next();
            if (resp.length() > 1 || !resp.equalsIgnoreCase("1") && !resp.equalsIgnoreCase("2")) {
                System.out.println("Introduce una opcion valida");

            } else {
                opcion = resp.charAt(0);
                val = true;
            }
        } while (val == false);
        return opcion;
    }

    public static char menuUsuario() {
        sc = new Scanner(System.in);
        char opcion = ' ';
        boolean val = false;
        do {
            System.out.println("");
            System.out.println("Que opcion desea realizar: ");
            System.out.println("1-Registrar contacto");
            System.out.println("2-Ver contactos");
            System.out.println("3-Buscar contacto");
            System.out.println("4-Eliminar contacto");
            System.out.println("5-Salir");
            System.out.println("------------------------------");
            String resp = sc.next();
            if (resp.length() > 1 || !resp.equalsIgnoreCase("1")
                    && !resp.equalsIgnoreCase("2") && !resp.equalsIgnoreCase("3")
                    && !resp.equalsIgnoreCase("4") && !resp.equalsIgnoreCase("5")) {
                System.out.println("Introduce una opcion valida");
            } else {
                opcion = resp.charAt(0);
                val = true;
            }
        } while (!val);
        return opcion;
    }

    public static void usuario() {
        char opcion = menu();
        Usuario u1 = new Usuario();
        switch (opcion) {
            case '1' -> {
                String resp = "";
                u1.creaUsuario();
                do {
                    System.out.println("Desea realizar otra operacion? (S/N)");
                    resp = sc.next();
                    if (resp.equalsIgnoreCase("N")) {
                        System.out.println("Muchas gracias por usar nuestro programa");
                    } else if (resp.equalsIgnoreCase("S")) {
                        usuario();
                    } else {
                        System.out.println("Introduce una respuesta valida por favor S/N");
                    }
                } while (!resp.equalsIgnoreCase("s") && !resp.equalsIgnoreCase("n"));
            }
            case '2' -> {
                if (u1.logeaUsuario()) {
                    char opcionCont;
                    Agenda a1 = new Agenda();
                    ArrayList<Contactos> misContactos = a1.cargarAgenda(u1.getNombre());
                    a1.setMisContactos(misContactos);
                    do {
                        opcionCont = menuUsuario();
                        switch (opcionCont) {
                            case '1' -> {
                                a1.registrarContacto();
                                a1.generaAgenda(u1.getNombre());
                            }
                            case '2' ->
                                a1.verContactos();
                            case '3' -> {
                                if (a1.buscarContacto()) {
                                    System.out.println("Hemos encontrado el contacto con exito");
                                } else {
                                    System.out.println("El contacto que buscas no existe en la agenda");
                                }
                            }
                            case '4' -> {
                                if (a1.eliminarContacto()) {
                                    System.out.println("Contacto eliminado con exito");
                                    a1.generaAgenda(u1.getNombre());
                                } else {
                                    System.out.println("No ha sido posible eliminar el contacto");
                                }
                            }
                            case '5' -> {
                                a1.generaAgenda(u1.getNombre());
                                System.out.println("Esperemos que hayas tenido una gran experiencia con nuestra agenda");
                            }
                            default ->
                                throw new AssertionError();
                        }
                    } while (opcionCont != '5');
                }
            }
        }
    }

    public Interfaz() {
        usuario();
    }

}
