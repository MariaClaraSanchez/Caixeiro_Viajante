package caixeiro_viajante;

import java.util.ArrayList;

/**
 * @author Maria Sanchez
 */
public class Caixeiro_Viajante{

    public static void main(String[] args) {
        /* Leitura do arquivo */

        CreationGraph cGr = new CreationGraph();
        ArrayList<String> text = cGr.ler("./data/Teste.txt");

        int graph[][] = cGr.graph(text);

        for (int[] graph1 : graph) {
            for (int j = 0; j < graph.length; j++) {
                System.out.print(" " + graph1[j] + " ");
            }
            System.out.println();
        }

        BestPath bestpath = new BestPath();
        ArrayList<Integer> aux = new ArrayList<>();
        int nVertex = graph.length;

        boolean[] vertices = new boolean[nVertex];

        // vertices[0] = true;
        for (int i = 0; i < nVertex; i++) {
            vertices[i] = false;
        }

        int va  = 3;
        vertices[va] = true;
        int pos = va;

        bestpath = Operations.hamiltonianCycle(graph, vertices, va, nVertex, 1, bestpath.bestWeight, bestpath, aux, pos);
        
        System.out.println("\n Menor peso: " + (bestpath.bestWeight-1));
        System.out.println("\n Caminho: " + bestpath.paths);

    }

}
