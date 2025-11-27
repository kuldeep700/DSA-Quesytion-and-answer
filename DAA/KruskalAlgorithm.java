import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class DSU {
    int[] parent;
    
    public DSU(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }

    public void union(int i, int j) {
        int root_i = find(i);
        int root_j = find(j);
        if (root_i != root_j) {
            parent[root_i] = root_j;
        }
    }
}

public class KruskalAlgorithm {
    
    public static void kruskalMST(List<Edge> allEdges, int V) {
        Collections.sort(allEdges);

        DSU dsu = new DSU(V);
        List<Edge> mst = new ArrayList<>();
        int totalWeight = 0;

        for (Edge edge : allEdges) {
            int u = edge.src;
            int v = edge.dest;

            if (dsu.find(u) != dsu.find(v)) {
                mst.add(edge);
                totalWeight += edge.weight;
                dsu.union(u, v);
            }
        }
        
        System.out.println("--- Kruskal's Algorithm ---");
        System.out.println("Edges in the MST:");
        for (Edge edge : mst) {
            System.out.println(edge.src + " -- " + edge.dest + " (Weight: " + edge.weight + ")");
        }
        System.out.println("Total MST Weight: " + totalWeight);
    }

    public static void main(String[] args) {
        int V = 4; 
        List<Edge> allEdges = new ArrayList<>();
        
        allEdges.add(new Edge(0, 1, 10));
        allEdges.add(new Edge(0, 2, 6));
        allEdges.add(new Edge(0, 3, 5));
        allEdges.add(new Edge(1, 3, 15));
        allEdges.add(new Edge(2, 3, 4)); 
        
        kruskalMST(allEdges, V);
    }
}