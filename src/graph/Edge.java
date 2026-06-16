package graph;


public class Edge<E extends Comparable<E>> implements Comparable<Edge<E>> { 

    private Vertex<E> vertex;
    private int weight;

    public Edge(Vertex<E> vertex) {
        this.vertex = vertex;
    }

    public Edge(Vertex<E> vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    public Vertex<E> getVertex() {
        return vertex;
    }

    public int getWeight(){
        return weight;
    }

    
    @Override
    public int compareTo(Edge<E> otro) {
        // Para comparar dos aristas, simplemente comparamos a qué vértice apuntan
        return this.vertex.compareTo(otro.getVertex());
    }

    @Override
    public String toString() {
        return vertex.toString(); 
    }
}