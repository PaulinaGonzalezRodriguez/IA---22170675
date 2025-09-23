package src.puzle8;

public class App {
    public static void main(String a[]){
        String estadoInicial ="12345 678";
        String estadoFinal =" 12345678";

        Arbol arbol=new Arbol(new Nodo(estadoInicial, null));

        System.out.println("Búsqueda en Anchura (BFS)");
        Nodo resultadoBFS = arbol.realizarBuquedaEnAnchura(estadoFinal);
        arbol.mostrarCamino(resultadoBFS);

        System.out.println("Búsqueda en Profundidad (DFS)");
        Nodo resultadoDFS = arbol.realizarBusquedaEnProfundidad(estadoFinal);
        arbol.mostrarCamino(resultadoDFS);

        System.out.println("Búsqueda de Costo Uniforme (UCS)");
        Nodo resultadoUCS = arbol.realizarBusquedaCostoUniforme(estadoFinal);
        arbol.mostrarCamino(resultadoUCS);

        System.out.println("Búsqueda en Profundidad Iterativa (IDDFS)");
        Nodo resultadoIDDFS = arbol.realizarBusquedaIterativa(estadoFinal, 50); // límite máximo de profundidad
        arbol.mostrarCamino(resultadoIDDFS);

        System.out.println("Búsqueda A* (heurística)");
        Nodo resultado = arbol.busquedaAEstrella(estadoFinal);
        arbol.mostrarCamino(resultado);

        if (resultado != null) {
            System.out.println("Solución encontrada en " + resultado.costo + " movimientos.");
        } else {
            System.out.println("No se encontró solución.");
        }

    }

    
}
