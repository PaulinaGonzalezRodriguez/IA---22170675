package src.puzle8;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Arbol {
    Nodo raiz;

    public Arbol(Nodo raiz){
        this.raiz= raiz;


    }
    public Nodo realizarBuquedaEnAnchura(String objetivo){
        //usara las variables de clase
        HashSet <String> visitados = new HashSet<String>();
        Queue<Nodo> cola = new LinkedList<Nodo>();
        cola.add(raiz);
        visitados.add(raiz.estado);
        boolean encontrado = false;
        Nodo actual;
        while (!encontrado &&  cola.size()>0 ) {
            actual= cola.poll();
            //System.out.println("Procesando => "+ actual.estado);
            //funcion objetivo
            if (actual.estado.equals(objetivo) ) {
                return actual;
            }else{
                List<String> sucesores = Nodo.obtenerSucesores(actual.estado);
                for (String sucesor : sucesores) {
                    //si ya fue procesado ignorar 
                    if (visitados.contains(sucesor)) {
                        continue;
                    }
                    //System.err.println("Agregando a cola => "+ sucesor);
                    cola.add(new Nodo(sucesor, actual));
                    visitados.add(sucesor);   
                }
            }

        }
        return null;
    }

    public Nodo realizarBusquedaEnProfundidad(String objetivo) {
        HashSet<String> visitados = new HashSet<>();
        Stack<Nodo> pila = new Stack<>();
        pila.push(raiz);
        visitados.add(raiz.estado);

        while (!pila.isEmpty()) {
            Nodo actual = pila.pop();

            if (actual.estado.equals(objetivo)) {
                return actual;
            }

            List<String> sucesores = Nodo.obtenerSucesores(actual.estado);
            for (String sucesor : sucesores) {
                if (!visitados.contains(sucesor)) {
                    pila.push(new Nodo(sucesor, actual));
                    visitados.add(sucesor);
                }
            }
        }
        return null;
    }

    public Nodo realizarBusquedaCostoUniforme(String objetivo) {
        HashSet<String> visitados = new HashSet<>();
        PriorityQueue<Nodo> cola = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.costo, b.costo)
        );
    
        raiz.costo = 0;
        cola.add(raiz);
    
        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
    
            if (actual.estado.equals(objetivo)) {
                return actual;
            }
    
            if (!visitados.contains(actual.estado)) {
                visitados.add(actual.estado);
    
                List<String> sucesores = Nodo.obtenerSucesores(actual.estado);
                for (String sucesor : sucesores) {
                    if (!visitados.contains(sucesor)) {
                        Nodo hijo = new Nodo(sucesor, actual);
                        hijo.costo = actual.costo + 1; // cada movimiento cuesta 1
                        cola.add(hijo);
                    }
                }
            }
        }
        return null;
    }
    
    
    public Nodo realizarBusquedaIterativa(String objetivo, int limiteMaximo) {
        for (int limite = 0; limite <= limiteMaximo; limite++) {
            HashSet<String> visitados = new HashSet<>();
            Nodo resultado = dfsLimitado(raiz, objetivo, limite, visitados);
            if (resultado != null) {
                return resultado;
            }
        }
        return null;
    }

    private Nodo dfsLimitado(Nodo nodo, String objetivo, int limite, HashSet<String> visitados) {
        if (nodo.estado.equals(objetivo)) {
            return nodo;
        }
        if (limite == 0) {
            return null;
        }
        visitados.add(nodo.estado);

        List<String> sucesores = Nodo.obtenerSucesores(nodo.estado);
        for (String sucesor : sucesores) {
            if (!visitados.contains(sucesor)) {
                Nodo hijo = new Nodo(sucesor, nodo);
                Nodo resultado = dfsLimitado(hijo, objetivo, limite - 1, visitados);
                if (resultado != null) {
                    return resultado;
                }
            }
        }
        return null;
    }



    private int heuristica(String estado, String objetivo) {
        int h = 0;
        for (int i = 0; i < estado.length(); i++) {
            char c = estado.charAt(i);
            if (c != ' ' && c != objetivo.charAt(i)) {
                h++;
            }
        }
        return h;
    }
    
    public Nodo busquedaAEstrella(String objetivo) {
    PriorityQueue<Nodo> cola = new PriorityQueue<>(
        Comparator.comparingInt(n -> (n.costo + heuristica(n.estado, objetivo)))
    );

    HashSet<String> visitados = new HashSet<>();
    cola.add(raiz);

    while (!cola.isEmpty()) {
        Nodo actual = cola.poll();

        if (actual.estado.equals(objetivo)) {
            return actual; 
        }

        visitados.add(actual.estado);

        for (String sucesor : Nodo.obtenerSucesores(actual.estado)) {
            if (!visitados.contains(sucesor)) {
                Nodo hijo = new Nodo(sucesor, actual);
                hijo.costo = actual.costo + 1; 
                cola.add(hijo);
            }
        }
    }
    return null;
}



    public void mostrarCamino(Nodo solucion) {
        if (solucion == null) {
            System.out.println("No hay camino porque no se encontró solución.");
            return;
        }

        List<String> camino = new ArrayList<>();
        Nodo actual = solucion;
        while (actual != null) {
            camino.add(actual.estado);
            actual = actual.padre;
        }
        Collections.reverse(camino);
        System.out.println();
        System.out.println("Movimientos:");
        for (String estado : camino) {
            imprimirTablero(estado);
            System.out.println("________");
        }
    }
    private void imprimirTablero(String estado) {
        for (int i = 0; i < estado.length(); i++) {
            char c = estado.charAt(i);
            System.out.print((c == ' ' ? "x" : c) + " ");
            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
        }
    }
}
