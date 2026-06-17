package ejecicios;

public class Vertex<V extends Comparable<V>> implements Comparable<Vertex<V>> { 

    private V data; 

    public Vertex(V data) { 
        this.data = data; 
    } 

    public V getData() { 
        return data; 
    } 

    public void setData(V data) { 
        this.data = data; 
    } 

    @Override
    public int compareTo(Vertex<V> other) {
        return this.data.compareTo(other.data);
    }

    // Sobrescribimos equals() para que la lista enlazada pueda buscar y eliminar vértices fácilmente
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj instanceof Vertex) {
            Vertex<?> other = (Vertex<?>) obj;
            return this.data.equals(other.data);
        }
        return false;
    }

    @Override 
    public String toString() { 
        return data.toString(); 
    } 
}
