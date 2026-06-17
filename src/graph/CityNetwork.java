package graph;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import java.util.Set;

public class CityNetwork {

    // Grafo no dirigido y ponderado de JGraphT
    private Graph<String, DefaultWeightedEdge> graph;

    public CityNetwork() {
        this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    }

    // Método para agregar ciudades (Vértices)
    public void addCity(String city) {
        graph.addVertex(city);
    }

    // Método para agregar carreteras con distancia (Aristas con peso)
    public void addRoad(String city1, String city2, double distance) {
        DefaultWeightedEdge edge = graph.addEdge(city1, city2);
        if (edge != null) {
            graph.setEdgeWeight(edge, distance);
        }
    }

    // Mostrar en consola la lista de ciudades
    public void printCities() {
        System.out.println("--- LISTA DE CIUDADES ---");
        Set<String> cities = graph.vertexSet();
        for (String city : cities) {
            System.out.println("- " + city);
        }
        System.out.println();
    }

    // Mostrar en consola las carreteras registradas
    public void printRoads() {
        System.out.println("--- CARRETERAS REGISTRADAS ---");
        Set<DefaultWeightedEdge> edges = graph.edgeSet();
        for (DefaultWeightedEdge edge : edges) {
            String source = graph.getEdgeSource(edge);
            String target = graph.getEdgeTarget(edge);
            double weight = graph.getEdgeWeight(edge);
            System.out.println(source + " <---> " + target + " : " + weight + " km");
        }
        System.out.println();
    }

    // Calcular el camino más corto entre dos ciudades usando Dijkstra
    public void calculateShortestPath(String source, String target) {
        System.out.println("--- RUTA ÓPTIMA: " + source + " a " + target + " ---");
        
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraAlg = new DijkstraShortestPath<>(graph);
        GraphPath<String, DefaultWeightedEdge> path = dijkstraAlg.getPath(source, target);

        if (path == null) {
            System.out.println("No hay conexión entre estas ciudades.");
        } else {
            System.out.println("Camino más corto: " + path.getVertexList());
            System.out.println("Costo total: " + path.getWeight() + " km");
        }
        System.out.println();
    }
}