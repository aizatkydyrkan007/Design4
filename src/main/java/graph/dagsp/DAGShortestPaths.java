package graph.dagsp;

import graph.topo.TopologicalSort;
import graph.utils.Metrics;
import java.util.*;

public class DAGShortestPaths {

    public static int[] shortestPaths(List<List<Edge>> dag, int source, Metrics metrics) {
        int n = dag.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        List<Integer> topo = TopologicalSort.sort(convertToAdjacencyList(dag), metrics);

        for (int u : topo) {
            if (dist[u] != Integer.MAX_VALUE) {
                for (Edge e : dag.get(u)) {
                    metrics.increment("relaxations");
                    if (dist[e.to] > dist[u] + e.weight) {
                        dist[e.to] = dist[u] + e.weight;
                    }
                }
            }
        }
        return dist;
    }

    private static List<List<Integer>> convertToAdjacencyList(List<List<Edge>> dag) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < dag.size(); i++) adj.add(new ArrayList<>());
        for (int u = 0; u < dag.size(); u++) {
            for (Edge e : dag.get(u)) {
                adj.get(u).add(e.to);
            }
        }
        return adj;
    }

    public record Edge(int to, int weight) {
    }
}
