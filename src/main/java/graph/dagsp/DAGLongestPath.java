package graph.dagsp;

import java.util.*;

public class DAGLongestPath {

    public static int[] longestPaths(List<List<DAGShortestPaths.Edge>> dag, int source, graph.utils.Metrics metrics) {
        List<List<DAGShortestPaths.Edge>> invDag = new ArrayList<>();
        for (int i = 0; i < dag.size(); i++) {
            invDag.add(new ArrayList<>());
            for (DAGShortestPaths.Edge e : dag.get(i)) {
                invDag.get(i).add(new DAGShortestPaths.Edge(e.to(), -e.weight()));
            }
        }

        int[] negDist = DAGShortestPaths.shortestPaths(invDag, source, metrics);
        int[] dist = new int[negDist.length];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = -negDist[i];
        }
        return dist;
    }
}
