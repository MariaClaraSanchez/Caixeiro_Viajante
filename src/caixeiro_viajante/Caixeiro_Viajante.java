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

        
        /* Declração das Variaveis*/
        BestPath bestpath = new BestPath();
        ArrayList<Integer> aux = new ArrayList<>();
        int nVertex = graph.length;
        boolean[] vertices = new boolean[nVertex];

        
        // Atribuindo todos vertices como false, pára sinalizar não visitados!
        for (int i = 0; i < nVertex; i++) {
            vertices[i] = false;
        }

        int va  = 3;
        vertices[va] = true;
        int pos = va;
        
        
        /* Chamada do algoritmo ótimo*/
        
		// Inicio da contagem do tempo
		long start = System.currentTimeMillis();
		
        bestpath = Operations.hamiltonianCycle(graph, vertices, va, nVertex, 1, bestpath.bestWeight, bestpath, aux, pos);
       
		long end = System.currentTimeMillis();
		// Fim da contagem do tempo
        
		/* Contagem da memoria */
		int dataSize = 1024 * 1024;
		Runtime runtime = Runtime.getRuntime();
		
		System.out.printf("\n Tempo de Execução: %.3f ms%n", (end - start) / 1000d);
		System.out.println("\n Memoria usada: " + (runtime.totalMemory() - runtime.freeMemory()) / dataSize + "MB");
        System.out.println("\n Melhor Caminho: " + bestpath.paths);
        System.out.println("\n Menor Esforco: " + (bestpath.bestWeight-1));

    }

}
