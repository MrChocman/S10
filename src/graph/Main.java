package graph;

public class Main {
    public static void main(String[] args) {
        
        CityNetwork network = new CityNetwork();

        // Agregando las ciudades según el ejemplo
        network.addCity("Arequipa");
        network.addCity("Cusco");
        network.addCity("Puno");
        network.addCity("Tacna");
        network.addCity("Moquegua");

        // Agregando las conexiones según el ejemplo
        network.addRoad("Arequipa", "Cusco", 510);
        network.addRoad("Arequipa", "Moquegua", 230);
        network.addRoad("Moquegua", "Tacna", 160);
        network.addRoad("Cusco", "Puno", 390);
        network.addRoad("Puno", "Tacna", 420);

        // Mostrando información general
        network.printCities();
        network.printRoads();

        // Calculando el camino más corto (Ejemplo: Arequipa a Tacna)
        // El programa debería deducir que ir por Moquegua (230+160=390) 
        // es más rápido que ir por Cusco y Puno (510+390+420=1320).
        network.calculateShortestPath("Arequipa", "Tacna");
        
        // Otro cálculo de prueba
        network.calculateShortestPath("Cusco", "Tacna");
    }
}