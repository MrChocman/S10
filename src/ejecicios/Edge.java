package ejecicios;

public class Edge<V extends Comparable<V>, E> implements Comparable<Edge<V, E>> { 

    private Vertex<V> vertex; // Vértice destino
    private E weight;         // Peso o distancia de la conexión

    public Edge(Vertex<V> vertex, E weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    public Vertex<V> getVertex() {
        return vertex;
    }

    public E getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge<V, E> otro) {
        return this.vertex.compareTo(otro.getVertex());
    }

    @Override
    public String toString() {
        // Muestra el destino y la distancia entre paréntesis
        return vertex.toString() + "(" + weight + ")"; 
    }
}