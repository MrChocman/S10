package ejecicios;

import listlinked.ListLinked;


public class GraphLink<V extends Comparable<V>, E> implements Graph<V, E> { 

    // Lista principal que guarda las listas de adyacencia de cada vértice
    private ListLinked<AdjList<V, E>> vertices; 

    public GraphLink() { 
        vertices = new ListLinked<>(); 
    } 

    // Método auxiliar privado para buscar la lista de adyacencia de un dato
    private AdjList<V, E> findVertex(V data) { 
        for(int i = 0; i < vertices.length(); i++) { 
            AdjList<V, E> adj = vertices.get(i); 
            if(adj.getVertex().getData().equals(data)) {
                return adj; 
            }
        } 
        return null; 
    } 

    @Override
    public void insertVertex(V data) { 
        // Solo insertamos si el vértice no existe aún en el grafo
        if(!searchVertex(data)) {
            vertices.insertLast(new AdjList<>(new Vertex<>(data))); 
        }
    }

    @Override
    public void insertEdge(V origin, V destination, E weight) { 
        AdjList<V, E> v1 = findVertex(origin); 
        AdjList<V, E> v2 = findVertex(destination); 

        if(v1 != null && v2 != null) {
            // Inserción en ambas direcciones (Grafo no dirigido)
            v1.getEdges().insertLast(new Edge<>(v2.getVertex(), weight)); 
            v2.getEdges().insertLast(new Edge<>(v1.getVertex(), weight)); 
        }
    }

    @Override
    public void removeVertex(V data) {
        AdjList<V, E> toRemove = findVertex(data);
        if (toRemove == null) return; // Si no existe, no hacemos nada

        // 1. Recorremos todos los vértices para eliminar cualquier arista que apunte a este vértice
        for (int i = 0; i < vertices.length(); i++) {
            AdjList<V, E> adj = vertices.get(i);
            
            for (int j = 0; j < adj.getEdges().length(); j++) {
                Edge<V, E> currentEdge = adj.getEdges().get(j);
                if (currentEdge.getVertex().getData().equals(data)) {
                    adj.getEdges().removeNode(currentEdge);
                    break; // Cortamos el bucle interno si ya eliminamos la conexión
                }
            }
        }
        
        // 2. Finalmente, eliminamos el vértice en sí de la lista principal
        vertices.removeNode(toRemove);
    }

    @Override
    public void removeEdge(V origin, V destination) {
        // Quitamos la arista de Origen -> Destino
        AdjList<V, E> v1 = findVertex(origin);
        if (v1 != null) {
            for (int i = 0; i < v1.getEdges().length(); i++) {
                Edge<V, E> currentEdge = v1.getEdges().get(i);
                if (currentEdge.getVertex().getData().equals(destination)) {
                    v1.getEdges().removeNode(currentEdge);
                    break;
                }
            }
        }

        // Quitamos la arista de Destino -> Origen
        AdjList<V, E> v2 = findVertex(destination);
        if (v2 != null) {
            for (int i = 0; i < v2.getEdges().length(); i++) {
                Edge<V, E> currentEdge = v2.getEdges().get(i);
                if (currentEdge.getVertex().getData().equals(origin)) {
                    v2.getEdges().removeNode(currentEdge);
                    break;
                }
            }
        }
    }

    @Override
    public boolean searchVertex(V data) {
        // Retorna verdadero si la búsqueda no devuelve null
        return findVertex(data) != null;
    }

    @Override
    public boolean searchEdge(V origin, V destination) {
        AdjList<V, E> v1 = findVertex(origin);
        if (v1 == null) return false;

        // Buscamos en las aristas del origen si alguna apunta al destino
        for (int i = 0; i < v1.getEdges().length(); i++) {
            if (v1.getEdges().get(i).getVertex().getData().equals(destination)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ListLinked<V> adjacentVertices(V data) {
        ListLinked<V> adjacents = new ListLinked<>();
        AdjList<V, E> v1 = findVertex(data);
        
        if (v1 != null) {
            // Recolectamos todos los vértices destino de las aristas
            for (int i = 0; i < v1.getEdges().length(); i++) {
                adjacents.insertLast(v1.getEdges().get(i).getVertex().getData());
            }
        }
        return adjacents;
    }

    @Override
    public ListLinked<V> getAllVertices() {
        ListLinked<V> list = new ListLinked<>();
        for(int i = 0; i < vertices.length(); i++) {
            list.insertLast(vertices.get(i).getVertex().getData());
        }
        return list;
    }

    @Override 
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        for(int i = 0; i < vertices.length(); i++) { 
            AdjList<V, E> adj = vertices.get(i); 
            sb.append(adj.getVertex()).append(" -> "); 
            for(int j = 0; j < adj.getEdges().length(); j++) { 
                sb.append(adj.getEdges().get(j)).append(" "); 
            } 
            sb.append("\n"); 
        } 
        return sb.toString(); 
    }
}