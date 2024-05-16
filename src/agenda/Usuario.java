package agenda;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Usuario {

    private String nombre;
    private String pass;
    private transient Scanner sc = new Scanner(System.in);
    private int contLog = 0;
    private int contDel = 0;
    private int numVal;

    public Usuario() {
    }

    private void borradoEnTxt(String s, String p) {
        String datos = "";
        String nuevaLinea;
        try {
            FileReader miLector = new FileReader(s);
            int caracter = miLector.read();
            datos = datos + (char) caracter;
            while (caracter != -1) {
                caracter = miLector.read();
                if (caracter != -1) {
                    datos = datos + (char) caracter;
                }
                datos = datos.replace(nombre + " " + p, "");
            }
            miLector.close();
            FileWriter miEscritor = new FileWriter(s);
            escribir(datos, miEscritor);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void escribir(String frase, FileWriter escritor) throws IOException {
        for (int i = 0; i < frase.length(); i++) {
            escritor.write(frase.charAt(i));
        }
        escritor.close();
    }

    private String leer(String archivo) {
        sc = new Scanner(System.in);
        String datos = "";
        try {
            FileReader miLector = new FileReader(archivo);
            int caracter = miLector.read();
            datos = datos + (char) caracter;
            while (caracter != -1) {
                caracter = miLector.read();
                if (caracter != -1) {
                    datos = datos + (char) caracter;
                }
            }
            miLector.close();
        } catch (Exception e) {
            e.getMessage();
        }
        return datos;
    }

    private boolean comprobacionLogin(String[] datos) {
        sc = new Scanner(System.in);
        boolean val = false;
        boolean enc = false;
        do {
            System.out.println("Introduce tu nombre");
            String nom = sc.next();
            System.out.println("Introduce tu contraseña");
            String pass = sc.next();
            for (int i = 0; i < datos.length; i += 2) {
                if (nom.contentEquals(datos[i])) {
                    nombre = nom;
                    numVal = i;
                    if (pass.contentEquals(datos[i + 1])) {
                        System.out.println("Contraseña correcta");
                        this.pass = pass;
                        enc = true;
                        val = true;
                        return true;
                    } else {
                        contDel++;
                    }
                }
            }
            if (enc == false) {
                System.out.println("nombre de usuario o contraseña incorrectas ");
                contLog++;
                System.out.println("Le quedan " + (3 - contLog) + " intentos de inicio de sesion");
            }
            if (contLog >= 3 && contDel >= 3) {
                System.out.println("Su usuario sera borrado de nuestra aplicacion al igual que su agenda lo sentimos");
                File archivoABorrar = new File("agenda" + nombre + ".dat");
                archivoABorrar.delete();
                String passBor = datos[numVal + 1];
                borradoEnTxt("Usuarios.txt", passBor);

            } else if (contLog >= 3) {
                System.out.println("Intentos de inicio de sesion superados, intentelo mas tarde");
                return false;
            }

        } while (contLog < 3 && !val);
        return false;
    }

    public boolean creaUsuario() {
        System.out.println("Introduce el nombre del usuario");
        nombre = sc.nextLine();
        boolean val = true;
        if (nombre.contains(" ")) {
            System.out.println("Nombre invalido, no introduzcas espacios");
            return false;
        }
        String datosOr = leer("Usuarios.txt");
        String[] partes = datosOr.split("\\ ");
        for (int i = 0; i < partes.length; i += 2) {
            if (nombre.equalsIgnoreCase(partes[i])) {
                System.out.println("Nombre de usuario ya registrado, por favor pruebe otro nombre ");
                return false;
            }
        }
        System.out.println("Ahora introduce una contraseña");
        pass = sc.nextLine();
        if (pass.contains(" ")) {
            System.out.println("Contraseña invalida, no introduzcas espacios");
            return false;
        }
        if (val == true) {
            File miArchivo = new File("Usuarios.txt");
            String usuario = nombre + " " + pass;
            try {
                FileWriter miEscritor = new FileWriter(miArchivo, true);
                if (miArchivo.length() != 0) {
                    Agenda a1 = new Agenda();
                    a1.generaAgenda(nombre);
                    usuario = usuario +" ";
                    escribir(usuario, miEscritor);
                    System.out.println("Usuario creado con exito");
                    return true;

                } else {
                    escribir(usuario+" ", miEscritor);
                    Agenda a1 = new Agenda();
                    a1.generaAgenda(nombre);
                    System.out.println("Usuario creado con exito");
                    return true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("No se ha podido crear su usuario");
        return false;
    }

    public boolean logeaUsuario() {
        String datosOr = leer("Usuarios.txt");
        String[] partes = datosOr.split("\\ ");
        if (comprobacionLogin(partes)) {
            return true;
        }
        return false;
    }

    public String getNombre() {
        return nombre;
    }

}
