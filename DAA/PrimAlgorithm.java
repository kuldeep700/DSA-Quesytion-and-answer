import java.util.*;

class Vertex implements Comparable<Vertex> {
    int id;
    int key;

    public Vertex(int id, int key) {
        this.id = id;
        this.key = key;
    }

    @Override
    public int compareTo(Vertex other) {
        return Integer.compare(this.key, other.key);
    }
}

public class PrimAlgorithm {

    private Map<Integer, List<Edge>> adj;
    private int V;

    public PrimAlgorithm(int V) {
        this.V = V;
        adj = new HashMap<>();
        for (int i = 0; i < V; i++) {
            adj.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int weight) {
        adj.get(u).add(new Edge(u, v, weight));
        adj.get(v).add(new Edge(v, u, weight));
    }

    public void primMST(int startVertex) {
        int[] key = new int[V];
        int[] parent = new int[V];
        boolean[] inMST = new boolean[V];

        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            inMST[i] = false;
        }

        key[startVertex] = 0;
        parent[startVertex] = -1;

        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(startVertex, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().id;
            
            inMST[u] = true;

            for (Edge edge : adj.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;

                if (!inMST[v] && weight < key[v]) {
                    parent[v] = u;
                    key[v] = weight;
                    pq.add(new Vertex(v, key[v]));
                }
            }
        }
        
        printMST(parent, key, startVertex);
    }

    private void printMST(int[] parent, int[] key, int startVertex) {
        int totalWeight = 0;
        System.out.println("\n--- Prim's Algorithm ---");
        System.out.println("Edges in the MST (starting from " + startVertex + "):");
        
        for (int i = 0; i < V; i++) {
            if (parent[i] != -1) {
                System.out.println(parent[i] + " -- " + i + " (Weight: " + key[i] + ")");
                totalWeight += key[i];
            }
        }
        System.out.println("Total MST Weight: " + totalWeight);
    }

    public static void main(String[] args) {
        int V = 5;
        PrimAlgorithm graph = new PrimAlgorithm(V);

        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);
        
        graph.primMST(0); 
    }
}