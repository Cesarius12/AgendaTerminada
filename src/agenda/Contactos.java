package agenda;

import java.io.Serializable;
import java.util.Scanner;

public class Contactos implements Serializable{

    private String nombre;
    private String apellidos;
    private int telefono;
    private String email;
    private int edad;
    private transient Scanner sc=new Scanner(System.in);

    public Contactos(String nombre, String apellidos, int telefono, String email, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.edad = edad;
    }
    
    public void mostrarInfo(){
        System.out.println("El nombre y apellidos del contacto son: "+nombre+" "+apellidos
                +"\nSu telefono es: "+telefono+"\nSu email es: "+email+"\nY su edad es: "+edad);
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
    
}
