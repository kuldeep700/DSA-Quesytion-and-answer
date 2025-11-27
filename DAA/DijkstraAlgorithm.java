import java.util.*;

class Edge {
    public int destination;
    public int weight;

    public Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

class VertexDistance implements Comparable<VertexDistance> {
    public int vertex;
    public int distance;

    public VertexDistance(int vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    @Override
    public int compareTo(VertexDistance other) {
        return Integer.compare(this.distance, other.distance);
    }
}

public class DijkstraAlgorithm {

    private Map<Integer, List<Edge>> adj;
    private int V;

    public DijkstraAlgorithm(int V) {
        this.V = V;
        adj = new HashMap<>();
        for (int i = 0; i < V; i++) {
            adj.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        adj.get(source).add(new Edge(destination, weight));
    }

    public Map<Integer, Integer> shortestPath(int source) {
        Map<Integer, Integer> distances = new HashMap<>();
        PriorityQueue<VertexDistance> pq = new PriorityQueue<>();

        for (int i = 0; i < V; i++) {
            distances.put(i, Integer.MAX_VALUE);
        }

        distances.put(source, 0);
        pq.add(new VertexDistance(source, 0));

        while (!pq.isEmpty()) {
            VertexDistance current = pq.poll();
            int u = current.vertex;
            int dist_u = current.distance;

            if (dist_u > distances.get(u)) {
                continue;
            }

            for (Edge edge : adj.get(u)) {
                int v = edge.destination;
                int weight_uv = edge.weight;

                if (distances.get(u) != Integer.MAX_VALUE && dist_u + weight_uv < distances.get(v)) {
                    distances.put(v, dist_u + weight_uv);
                    pq.add(new VertexDistance(v, distances.get(v)));
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        int V = 5;
        DijkstraAlgorithm graph = new DijkstraAlgorithm(V);

        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 4, 5);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 4, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 2, 6);
        graph.addEdge(4, 1, 3);
        graph.addEdge(4, 2, 9);
        graph.addEdge(4, 3, 2);

        int sourceVertex = 0;
        
        Map<Integer, Integer> shortestDistances = graph.shortestPath(sourceVertex);

        System.out.println("Shortest distances from source vertex " + sourceVertex + ":");
        for (Map.Entry<Integer, Integer> entry : shortestDistances.entrySet()) {
            int vertex = entry.getKey();
            int distance = entry.getValue();
            
            String distString = (distance == Integer.MAX_VALUE) ? "INF" : String.valueOf(distance);
            System.out.println("Vertex " + vertex + ": " + distString);
        }
    }
}