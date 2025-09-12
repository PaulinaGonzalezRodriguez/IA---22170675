package src;
public class Arbol {
    private Nodo raiz;

      public Arbol() {
          this.raiz = null;
      }

      public boolean vacio() {
          return raiz == null;
      }

      public void insertar(String nombre) {
          raiz = insertarRec(raiz, nombre);
      }

      // Insertar nodos
      private Nodo insertarRec(Nodo actual, String nombre) {
          if (actual == null) {
              return new Nodo(nombre);
          }

          if (nombre.compareTo(actual.nombre) < 0) {
              actual.izquierda = insertarRec(actual.izquierda, nombre);
          } else if (nombre.compareTo(actual.nombre) > 0) {
              actual.derecha = insertarRec(actual.derecha, nombre);
          }

          return actual;
      }

      // Buscar nodo 
      public Nodo buscarNodo(String nombre) {
          return buscarRec(raiz, nombre);
      }
      private Nodo buscarRec(Nodo actual, String nombre) {
          if (actual == null || actual.nombre.equals(nombre)) {
              return actual;
          }

          if (nombre.compareTo(actual.nombre) < 0) {
              return buscarRec(actual.izquierda, nombre);
          } else {
              return buscarRec(actual.derecha, nombre);
          }
      }

      // Imprimir preorden
      public void preorden() {
          System.out.println("Raíz: " + (raiz != null ? raiz.nombre : "Arbol vacío"));
          preordenRec(raiz);
          System.out.println();
      }

      private void preordenRec(Nodo actual) {
          if (actual != null) {
              System.out.print(actual.nombre + " ");
              preordenRec(actual.izquierda);
              preordenRec(actual.derecha);
          }
      }

      // Imprimir inorden
      public void inorden() {
          System.out.println("Raíz: " + (raiz != null ? raiz.nombre : "Arbol vacío"));
          inordenRec(raiz);
          System.out.println();
      }

      private void inordenRec(Nodo actual) {
          if (actual != null) {
              inordenRec(actual.izquierda);
              System.out.print(actual.nombre + " ");
              inordenRec(actual.derecha);
          }
      }

      // Imprimir postorden
      public void postorden() {
          System.out.println("Raíz: " + (raiz != null ? raiz.nombre : "Arbol vacío"));
          postordenRec(raiz);
          System.out.println();
      }

      private void postordenRec(Nodo actual) {
          if (actual != null) {
              postordenRec(actual.izquierda);
              postordenRec(actual.derecha);
              System.out.print(actual.nombre + " ");
          }
      }
}
