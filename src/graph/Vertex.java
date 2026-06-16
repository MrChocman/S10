package graph; 

public class Vertex<E extends Comparable<E>> implements Comparable<Vertex<E>> { 

    private E data; 

    public Vertex(E data) { 
        this.data = data; 
    } 

    public E getData() { 
        return data; 
    } 

    public void setData(E data) { 
        this.data = data; 
    } 

    // 2. Implementamos el método obligatorio compareTo
    @Override
    public int compareTo(Vertex<E> other) {
        // Comparamos este vértice con otro basándonos en su dato interno
        return this.data.compareTo(other.data);
    }

    @Override 
    public String toString() { 
        return data.toString(); 
    } 
}