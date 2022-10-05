package caixeiro_viajante;

import java.util.ArrayList;

/**
 * @author Maria Sanchez
 */
public class Caixeiro_Viajante{

    public static void main(String[] args) {
        
    	/* ************ Leitura do arquivo ************ */
        CreationGraph cGr = new CreationGraph();
        ArrayList<String> text = cGr.ler("./data/Teste3.txt");

        int graphInicial[][] = cGr.graph(text);
        for (int[] graph1 : graphInicial) {
            for (int j = 0; j < graphInicial.length; j++) {
                System.out.print(" " + graph1[j] + " ");
            }
            System.out.println();
        }

        /* ************ Declaracao das variaveis ************ */
        int graphMax[][] = new int[11][11];
        int nVertex = graphMax.length;

        BestPath bestpath = new BestPath();
        ArrayList<Integer> aux = new ArrayList<>();
       
        boolean[] vertices = new boolean[nVertex];
        int va  = 0;
        int pos = va;
        
		// Atribuição da quantidade de caminhos a serem utilizados
		for (int i = 0; i < nVertex; i++) {
			for (int j = 0; j < nVertex; j++) {
				graphMax[i][j] = graphInicial[i][j];
			}
		}
        
        // Atribuindo todos vertices como false, para sinalizar no visitados!
        for (int i = 0; i < nVertex; i++) {
            vertices[i] = false;
        }
        
        // Qual o vertices escolhido como origem
        vertices[va] = true;

        
        /* ************ Chamada do algoritmo ótimo ************ */
        
        /* Inicio da contagem do tempo */
		long start = System.currentTimeMillis();
		
        bestpath = Operations.hamiltonianCycle(graphMax, vertices, va, nVertex, 1, bestpath.bestWeight, bestpath, aux, pos);
       
		long end = System.currentTimeMillis();
		/* Fim da contagem do tempo */
        
		/* Contagem da memoria */
		int dataSize = 1024 * 1024;
		Runtime runtime = Runtime.getRuntime();

		/* ************ Saídas ************ */
		
		System.out.printf("\n Tempo de Execucaoo: %.2f ms %n", (end - start) / 1000d);
		System.out.println("\n Memoria usada: " + (runtime.totalMemory() - runtime.freeMemory()) / dataSize + "MB");
        System.out.println("\n Melhor Caminho: " + bestpath.paths);
        System.out.println("\n Menor Esforco: " + (bestpath.bestWeight-1));

    }

}
