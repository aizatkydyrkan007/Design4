package graph.topo;

import graph.utils.Metrics;
import java.util.*;

public class TopologicalSort {

    public static List<Integer> sort(List<List<Integer>> dag, Metrics metrics) {
        int n = dag.size();
        int[] inDegree = new int[n];
        for (List<Integer> edges : dag) {
            for (int v : edges) {
                inDegree[v]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }

        List<Integer> topoOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            metrics.increment("queue pops");
            topoOrder.add(u);
            for (int v : dag.get(u)) {
                inDegree[v]--;
                metrics.increment("in-degree decrements");
                if (inDegree[v] == 0) queue.add(v);
            }
        }

        if (topoOrder.size() != n) {
            throw new RuntimeException("Graph is not a DAG (cycle detected)");
        }

        return topoOrder;
    }
}
