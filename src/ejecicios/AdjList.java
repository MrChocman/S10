package ejecicios;

import listlinked.ListLinked;

// La lista de adyacencia junta a un Vértice Principal con su Lista de Aristas
public class AdjList<V extends Comparable<V>, E> implements Comparable<AdjList<V, E>> {
    
    private Vertex<V> vertex;
    private ListLinked<Edge<V, E>> edges;

    public AdjList(Vertex<V> vertex) {
        this.vertex = vertex;
        this.edges = new ListLinked<>();
    }

    public Vertex<V> getVertex() {
        return vertex;
    }

    public ListLinked<Edge<V, E>> getEdges() {
        return edges;
    }

    @Override
    public int compareTo(AdjList<V, E> other) {
        return this.vertex.compareTo(other.getVertex());
    }

    // Fundamental para el método removeVertex() del Grafo
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj instanceof AdjList) {
            AdjList<?, ?> other = (AdjList<?, ?>) obj;
            return this.vertex.equals(other.vertex);
        }
        return false;
    }

    @Override
    public String toString() {
        return vertex.toString();
    }
}
