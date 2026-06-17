package ejecicios;

import listlinked.ListLinked;

public class TestEjercicio3 {
    public static void main(String[] args) {
        
        // Creamos nuestro grafo. V = String (Ciudad), E = Integer (Distancia)
        // Fíjate cómo llamamos a la interfaz en la izquierda y a la implementación en la derecha
        Graph<String, Integer> g = new GraphLink<>();

        // --- 1. PROBAR insertVertex() ---
        g.insertVertex("Arequipa");
        g.insertVertex("Cusco");
        g.insertVertex("Tacna");
        g.insertVertex("Puno");

        // --- 2. PROBAR insertEdge() ---
        g.insertEdge("Arequipa", "Cusco", 510);
        g.insertEdge("Arequipa", "Tacna", 370);
        g.insertEdge("Cusco", "Puno", 390);

        System.out.println("--- GRAFO INICIAL ---");
        System.out.println(g);

        // --- 3. PROBAR searchVertex() y searchEdge() ---
        System.out.println("¿Existe Cusco?: " + g.searchVertex("Cusco"));
        System.out.println("¿Existe Lima?: " + g.searchVertex("Lima"));
        System.out.println("¿Existe arista Arequipa-Tacna?: " + g.searchEdge("Arequipa", "Tacna"));
        System.out.println("¿Existe arista Cusco-Tacna?: " + g.searchEdge("Cusco", "Tacna"));

        // --- 4. PROBAR adjacentVertices() ---
        System.out.println("\n--- ADYACENTES A AREQUIPA ---");
        ListLinked<String> adyacentes = g.adjacentVertices("Arequipa");
        adyacentes.print(); // Usamos el print de tu propia ListLinked

        // --- 5. PROBAR removeEdge() ---
        System.out.println("--- REMOVIENDO ARISTA Arequipa-Tacna ---");
        g.removeEdge("Arequipa", "Tacna");
        System.out.println(g);
    }
}

