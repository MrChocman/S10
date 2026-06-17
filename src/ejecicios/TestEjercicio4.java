package ejecicios;

public class TestEjercicio4 {
    public static void main(String[] args) {
        GraphAnalyzer<String, Integer> analyzer = new GraphAnalyzer<>();

        System.out.println("====== PRUEBAS EJERCICIO 4 ======\n");

        // --- GRAFO 1 ---
        GraphLink<String, Integer> g1 = new GraphLink<>();
        g1.insertVertex("A");
        g1.insertVertex("B");
        g1.insertVertex("C");
        g1.insertVertex("D");

        // Conexiones para formar un ciclo: A->B, B->C, C->D, D->A
        g1.insertEdge("A", "B", 1);
        g1.insertEdge("B", "C", 1);
        g1.insertEdge("C", "D", 1);
        g1.insertEdge("D", "A", 1);

        System.out.println("G1 es Conexo?: " + analyzer.isConexo(g1)); // Debe ser true
        System.out.println("G1 es Plano?: " + analyzer.isPlano(g1));   // Debe ser true

        // --- GRAFO 2 ---
        GraphLink<String, Integer> g2 = new GraphLink<>();
        g2.insertVertex("W");
        g2.insertVertex("X");
        g2.insertVertex("Y");
        g2.insertVertex("Z");

        // Conexiones de G2 (Misma forma que G1 pero con letras diferentes)
        g2.insertEdge("W", "X", 1);
        g2.insertEdge("X", "Y", 1);
        g2.insertEdge("Y", "Z", 1);
        g2.insertEdge("Z", "W", 1);

        System.out.println("\nG1 y G2 son Isomorfos?: " + analyzer.isIsomorfo(g1, g2)); // Debe ser true

        // --- GRAFO AUTO COMPLEMENTARIO (El clásico pentágono C5) ---
        GraphLink<String, Integer> pentagono = new GraphLink<>();
        pentagono.insertVertex("1");
        pentagono.insertVertex("2");
        pentagono.insertVertex("3");
        pentagono.insertVertex("4");
        pentagono.insertVertex("5");
        
        // Conexiones para formar el pentágono 1-2-3-4-5-1 (bidireccional para simular grafo base)
        pentagono.insertEdge("1", "2", 1); pentagono.insertEdge("2", "1", 1);
        pentagono.insertEdge("2", "3", 1); pentagono.insertEdge("3", "2", 1);
        pentagono.insertEdge("3", "4", 1); pentagono.insertEdge("4", "3", 1);
        pentagono.insertEdge("4", "5", 1); pentagono.insertEdge("5", "4", 1);
        pentagono.insertEdge("5", "1", 1); pentagono.insertEdge("1", "5", 1);

        // El Pentágono es el ejemplo matemático clásico de un grafo Auto Complementario
        System.out.println("\nEl Pentágono es Auto Complementario?: " + analyzer.isAutoComplementario(pentagono, 1)); // Debe ser true
    }
}
