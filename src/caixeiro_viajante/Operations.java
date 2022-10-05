package caixeiro_viajante;

import java.util.ArrayList;
import java.util.List;

class BestPath {

    ArrayList<Integer> paths = new ArrayList<Integer>();
    int bestWeight = Integer.MAX_VALUE;
}

public class Operations {

	public static List<Integer> FindAdjacentVertex(int graph[][], int CurrentPosition) {

		List<Integer> vertices = new ArrayList<Integer>();

		for (int i = 0; i < graph.length; i++) {

			if (CurrentPosition != i && graph[CurrentPosition][i] != -1) {
				vertices.add(i);
			}
		}

		return vertices;
	}

    public static boolean AllVerticesTraversed(boolean[] vertices, int size) {
        int cont = 0;
        for (int i = 0; i < size; i++) {

            if (vertices[i] == true) {
                cont++;
            }
        }
        // Se a quantidade dos vertices forem iguais
        // pq todos os vertices foram visitados
        // System.out.println(cont);

        if (cont == size) {
            return true;
        } else {
            return false;
        }

    }

    static BestPath hamiltonianCycle(int[][] graph, boolean[] v, int CurrentPosition, int nVertex, int weight,
            int bestWeight, BestPath best, ArrayList<Integer> aux, int pos) {

        List<Integer> verticesAdj = new ArrayList<>();

        verticesAdj = FindAdjacentVertex(graph, CurrentPosition);

        // aux.add(CurrentPosition);
        //ArrayList<Integer> aux2 = new ArrayList<>();
        //aux2.add(CurrentPosition);
        if (AllVerticesTraversed(v, nVertex)) {
            if (graph[CurrentPosition][pos] > 0) {
              //  System.err.println("Antes:" + aux);
                if (best.bestWeight > weight + graph[CurrentPosition][pos]) {
                    best.bestWeight = weight + graph[CurrentPosition][pos];
                    best.bestWeight = Math.min(best.bestWeight, weight + graph[CurrentPosition][pos]);
                    best.paths.clear();
                    aux.add(pos);
                    for (int i = 0; i < nVertex; i++) {
                        best.paths.add(aux.get(i));
                        // System.err.println(aux.get(i));
                    }
                    aux.remove(aux.size() - 1);
                }

                return best;
            }
        }
        // BACKTRACKING
        for (int i = 0; i < verticesAdj.size(); i++) {
            if (v[verticesAdj.get(i)] == false) {

                v[verticesAdj.get(i)] = true;
                aux.add(verticesAdj.get(i));

                best = hamiltonianCycle(graph, v, verticesAdj.get(i), nVertex,
                        weight + graph[CurrentPosition][verticesAdj.get(i)], best.bestWeight, best, aux, pos);

                v[verticesAdj.get(i)] = false;
                aux.remove(verticesAdj.get(i));

            }
        }
        return best;
    }

}
