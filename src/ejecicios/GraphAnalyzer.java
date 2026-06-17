package ejecicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Clase de utilidad para analizar propiedades complejas de un Grafo Dirigido.
 */
public class GraphAnalyzer<V extends Comparable<V>, E> {

    /**
     * 1. CONEXO (Grafos Dirigidos: Fuertemente Conexo)
     * Verifica si es posible llegar a cualquier vértice desde cualquier otro vértice.
     */
    public boolean isConexo(GraphLink<V, E> g) {
        List<V> vertices = toJavaList(g.getAllVertices());
        if (vertices.isEmpty() || vertices.size() == 1) return true;

        // Evaluamos si mediante un recorrido (BFS), cada nodo puede alcanzar a todos los demás
        for (V startVertex : vertices) {
            if (!bfsReachAll(g, startVertex, vertices.size())) {
                return false; // Si un solo nodo no puede alcanzar al resto, no es fuertemente conexo
            }
        }
        return true;
    }

    private boolean bfsReachAll(GraphLink<V, E> g, V start, int totalNodes) {
        HashSet<V> visited = new HashSet<>();
        Queue<V> queue = new LinkedList<>();
        
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            List<V> neighbors = toJavaList(g.adjacentVertices(current));
            for (V neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return visited.size() == totalNodes;
    }

    /**
     * 2. PLANO
     * Para grafos, usamos la heurística del Corolario de Euler (Condición necesaria).
     * En un grafo plano conexo: las aristas (E) deben ser <= 3 * Vértices (V) - 6.
     */
    public boolean isPlano(GraphLink<V, E> g) {
        List<V> vertices = toJavaList(g.getAllVertices());
        int v = vertices.size();
        
        if (v <= 3) return true; // Los grafos de 1, 2 o 3 vértices siempre son planos

        // Para aplicar Euler, necesitamos contar las "aristas únicas" (sin considerar dirección)
        HashSet<String> aristasUnicas = new HashSet<>();
        for (V u : vertices) {
            List<V> neighbors = toJavaList(g.adjacentVertices(u));
            for (V w : neighbors) {
                // Ordenamos alfabéticamente para asegurar que A->B y B->A cuenten como una sola conexión
                String connection = u.compareTo(w) < 0 ? u + "-" + w : w + "-" + u;
                aristasUnicas.add(connection);
            }
        }
        
        int e = aristasUnicas.size();

        // Fórmula de Euler para Planaridad: Si E > 3V - 6, es matemáticamente imposible que sea plano.
        return e <= (3 * v - 6);
    }

    /*3. ISOMORFO
     * Determina si dos grafos tienen exactamente la misma "forma" y conexiones.
     */
    public boolean isIsomorfo(GraphLink<V, E> g1, GraphLink<V, E> g2) {
        List<V> v1 = toJavaList(g1.getAllVertices());
        List<V> v2 = toJavaList(g2.getAllVertices());

        // Si no tienen la misma cantidad de vértices, es imposible que sean isomorfos
        if (v1.size() != v2.size()) return false;

        // Utilizamos Backtracking para probar todas las permutaciones y mapeos posibles
        return checkIsomorphism(g1, g2, v1, v2, new HashMap<>(), new HashSet<>());
    }

    private boolean checkIsomorphism(GraphLink<V, E> g1, GraphLink<V, E> g2, List<V> v1, List<V> v2, HashMap<V, V> mapping, HashSet<V> used) {
        // Si ya mapeamos todos los nodos, comprobamos si las conexiones son idénticas
        if (mapping.size() == v1.size()) {
            for (V u : v1) {
                for (V w : v1) {
                    if (u.equals(w)) continue;
                    boolean edgeInG1 = g1.searchEdge(u, w);
                    boolean edgeInG2 = g2.searchEdge(mapping.get(u), mapping.get(w));
                    
                    // Si encontramos una diferencia en las conexiones, este mapeo falla
                    if (edgeInG1 != edgeInG2) return false;
                }
            }
            return true; // Encontramos un mapeo perfecto
        }

        V currentV1 = v1.get(mapping.size());
        for (V candidateV2 : v2) {
            if (!used.contains(candidateV2)) {
                mapping.put(currentV1, candidateV2);
                used.add(candidateV2);
                
                // Llamada recursiva
                if (checkIsomorphism(g1, g2, v1, v2, mapping, used)) {
                    return true;
                }
                
                // Retroceso (Backtracking)
                used.remove(candidateV2);
                mapping.remove(currentV1);
            }
        }
        return false;
    }

    /**
     * 4. AUTO COMPLEMENTARIO
     * Un grafo es auto complementario si su grafo "inverso/complemento" es isomorfo a él mismo.
     */
    public boolean isAutoComplementario(GraphLink<V, E> g, E defaultWeight) {
        GraphLink<V, E> complement = getComplement(g, defaultWeight);
        return isIsomorfo(g, complement);
    }

    // Genera el grafo inverso (donde hay aristas se borran, donde no hay, se crean)
    private GraphLink<V, E> getComplement(GraphLink<V, E> g, E defaultWeight) {
        GraphLink<V, E> complement = new GraphLink<>();
        List<V> vertices = toJavaList(g.getAllVertices());
        
        for (V v : vertices) {
            complement.insertVertex(v);
        }
        
        for (V u : vertices) {
            for (V w : vertices) {
                if (!u.equals(w)) {
                    // Si NO existe conexión en el grafo original, la agregamos al complemento
                    if (!g.searchEdge(u, w)) {
                        complement.insertEdge(u, w, defaultWeight);
                    }
                }
            }
        }
        return complement;
    }

    // Utilidad: Convierte tu ListLinked personalizada a un ArrayList nativo para poder usar la API de Java 
    // en los algoritmos complejos.
    private List<V> toJavaList(listlinked.ListLinked<V> customList) {
        List<V> list = new ArrayList<>();
        if (customList == null) return list;
        
        for (int i = 0; i < customList.length(); i++) {
            list.add(customList.get(i));
        }
        return list;
    }
}