package graph; 

import java.util.*;
import listlinked.ListLinked; 

public class GraphLink<E extends Comparable<E>> { 

    private ListLinked<AdjList<E>> vertices; 

    public GraphLink() { 
        vertices = new ListLinked<>(); 
    } 

    public void insertVertex(E data) { 
        Vertex<E> vertex = new Vertex<>(data); 
        vertices.insertLast(new AdjList<>(vertex)); 
    }
    
    private AdjList<E> findVertex(E data) { 
        for(int i = 0; i < vertices.length(); i++) { 
            AdjList<E> adj = vertices.get(i); 
            if(adj.getVertex().getData().equals(data)) 
            return adj; 
        } 
        return null; 
    } 

    public void insertEdge(E origin, E destination) { 

        AdjList<E> v1 = findVertex(origin); 
        AdjList<E> v2 = findVertex(destination); 

        if(v1 == null || v2 == null) 
            return; 

        v1.getEdges().insertLast( 
            new Edge<>(v2.getVertex()) 
        ); 

        v2.getEdges().insertLast( 
            new Edge<>(v1.getVertex()) 
        ); 
    }

    // 1. INSERTAR ARISTA CON PESO (Grafo No Dirigido)
    public void insertEdgeWeight(E v, E z, int weight) {
        AdjList<E> v1 = findVertex(v);
        AdjList<E> v2 = findVertex(z);

        if(v1 == null || v2 == null) 
            return;

        // Como es no dirigido, la arista va de V a Z, y también de Z a V
        v1.getEdges().insertLast(new Edge<>(v2.getVertex(), weight));
        v2.getEdges().insertLast(new Edge<>(v1.getVertex(), weight));
    }

    // 2. SABER SI EL GRAFO ES CONEXO (Usando recorrido BFS)
    public boolean isConexo() {
        if (vertices.length() <= 1) return true; // Un grafo vacío o de 1 nodo es conexo

        HashSet<E> visitados = new HashSet<>();
        Queue<E> cola = new LinkedList<>();

        // Empezamos desde el primer vértice que tengamos en la lista
        E nodoInicial = vertices.get(0).getVertex().getData();
        cola.add(nodoInicial);
        visitados.add(nodoInicial);

        // Exploramos todos los nodos alcanzables
        while (!cola.isEmpty()) {
            E actual = cola.poll();
            AdjList<E> adj = findVertex(actual);

            for (int i = 0; i < adj.getEdges().length(); i++) {
                E vecino = adj.getEdges().get(i).getVertex().getData();
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }
        // Es conexo si la cantidad de nodos visitados es igual al total de vértices
        return visitados.size() == vertices.length();
    }

    // 3. ALGORITMO DE DIJKSTRA 
    public Stack<E> Dijsktra(E v, E w) {
        Stack<E> ruta = new Stack<>();
        if (findVertex(v) == null || findVertex(w) == null) return ruta;

        // Estructuras de apoyo para el algoritmo
        HashMap<E, Integer> distancias = new HashMap<>();
        HashMap<E, E> predecesores = new HashMap<>();
        PriorityQueue<E> pq = new PriorityQueue<>(Comparator.comparingInt(distancias::get));

        // Inicializamos todas las distancias en "Infinito"
        for (int i = 0; i < vertices.length(); i++) {
            E nodo = vertices.get(i).getVertex().getData();
            distancias.put(nodo, Integer.MAX_VALUE);
        }

        // La distancia al nodo de origen es 0
        distancias.put(v, 0);
        pq.add(v);

        while (!pq.isEmpty()) {
            E actual = pq.poll();

            if (actual.equals(w)) break; // Llegamos al destino

            AdjList<E> adj = findVertex(actual);
            for (int i = 0; i < adj.getEdges().length(); i++) {
                Edge<E> arista = adj.getEdges().get(i);
                E vecino = arista.getVertex().getData();
                
                int nuevaDistancia = distancias.get(actual) + arista.getWeight();

                // Si encontramos un camino más corto, actualizamos
                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    predecesores.put(vecino, actual);
                    // Actualizamos en la cola de prioridad
                    pq.remove(vecino); 
                    pq.add(vecino);
                }
            }
        }

        // Construir la ruta final usando la pila (hacia atrás)
        E rastreo = w;
        if (predecesores.containsKey(rastreo) || rastreo.equals(v)) {
            while (rastreo != null) {
                ruta.push(rastreo);
                rastreo = predecesores.get(rastreo);
            }
        }
        return ruta;
    }

    // 4. CAMINO MÁS CORTO (En ArrayList)
    public ArrayList<E> shortPath(E v, E z) {
        ArrayList<E> listaRuta = new ArrayList<>();
        
        // ¡Reutilizamos el método Dijkstra para no repetir código!
        Stack<E> pilaRuta = Dijsktra(v, z);

        // Vaciamos la pila en el ArrayList. 
        // Como la pila es LIFO, al sacarlos quedarán ordenados desde el origen hasta el destino.
        while (!pilaRuta.isEmpty()) {
            listaRuta.add(pilaRuta.pop());
        }

        return listaRuta;
    } 
    
    @Override 
    public String toString() { 
    
        StringBuilder sb = new StringBuilder(); 
    
        for(int i = 0; i < vertices.length(); i++) { 
    
            AdjList<E> adj = vertices.get(i); 
            sb.append(adj.getVertex()).append(" -> "); 
    
            for(int j = 0; j < adj.getEdges().length(); j++) { 
    
                sb.append(adj.getEdges().get(j)).append(" "); 
            } 
    
            sb.append("\n"); 
        } 
    
        return sb.toString(); 
    }
}