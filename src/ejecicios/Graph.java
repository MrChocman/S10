package ejecicios;

import listlinked.ListLinked;


public interface Graph<V, E> {
    
    // 1. Inserta un vértice al grafo
    void insertVertex(V vertex);
    
    // 2. Inserta una arista (conexión) entre dos vértices con su peso
    void insertEdge(V origin, V destination, E weight);
    
    // 3. Elimina un vértice y todas las aristas conectadas a él
    void removeVertex(V vertex);
    
    // 4. Elimina la conexión directa entre dos vértices
    void removeEdge(V origin, V destination);
    
    // 5. Busca si un vértice existe en el grafo
    boolean searchVertex(V vertex);
    
    // 6. Busca si existe una conexión directa entre dos vértices
    boolean searchEdge(V origin, V destination);
    
    // 7. Devuelve una lista con todos los vértices vecinos de uno dado
    ListLinked<V> adjacentVertices(V vertex);

    // 8. Devuelve todos los vertices
    ListLinked<V> getAllVertices();
}