package src;

public class Nodo {
    String nombre;
    Nodo izquierda;
    Nodo derecha;

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.izquierda = null;
        this.derecha = null;
    }
}
