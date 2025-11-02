import graph.scc.SCCResult;
import graph.scc.TarjanSCC;
import graph.topo.TopologicalSort;
import graph.dagsp.DAGShortestPaths;
import graph.dagsp.DAGLongestPath;
import graph.utils.SimpleMetrics;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String file = args.length > 0 ? args[0] : "data/small1.json";
        SimpleMetrics metrics = new SimpleMetrics();

        List<List<Integer>> graph = loadGraph(file);
        System.out.println("Loaded graph with n=" + graph.size());

        TarjanSCC tarjan = new TarjanSCC(graph, metrics);
        SCCResult sccResult = tarjan.computeSCCs();
        sccResult.printSummary();
        metrics.print();
        metrics.reset();

        List<List<Integer>> condensationDAG = buildCondensationDAG(graph, sccResult);
        System.out.println("Condensation DAG nodes: " + condensationDAG.size());

        List<Integer> topoOrder = TopologicalSort.sort(condensationDAG, metrics);
        System.out.println("Topological order: " + topoOrder);
        metrics.print();
        metrics.reset();

        List<List<DAGShortestPaths.Edge>> dagEdges = convertToEdges(condensationDAG);
        int source = 0;
        int[] shortest = DAGShortestPaths.shortestPaths(dagEdges, source, metrics);
        System.out.println("Shortest paths from node " + source + ": " + Arrays.toString(shortest));
        metrics.print();
        metrics.reset();

        int[] longest = DAGLongestPath.longestPaths(dagEdges, source, metrics);
        System.out.println("Longest paths from node " + source + ": " + Arrays.toString(longest));
        metrics.print();
        metrics.reset();
    }

    private static List<List<Integer>> loadGraph(String filePath) {
        try {
            String content = Files.readString(Paths.get(filePath));
            Pattern pNodes = Pattern.compile("\"nodes\" *: *(\\d+)");
            Matcher mNodes = pNodes.matcher(content);
            int n;
            if (mNodes.find()) {
                n = Integer.parseInt(mNodes.group(1));
            } else {
                throw new RuntimeException("Cannot find 'nodes' field in " + filePath);
            }
            List<List<Integer>> g = new ArrayList<>();
            for (int i = 0; i < n; i++) g.add(new ArrayList<>());
            Pattern pEdge = Pattern.compile("\\[(\\d+)\\s*,\\s*(\\d+)]");
            Matcher mEdge = pEdge.matcher(content);
            while (mEdge.find()) {
                int a = Integer.parseInt(mEdge.group(1));
                int b = Integer.parseInt(mEdge.group(2));
                if (a >= 0 && a < n && b >= 0 && b < n) g.get(a).add(b);
            }
            return g;
        } catch (Exception e) {
            throw new RuntimeException("Failed to read file: " + filePath, e);
        }
    }

    private static List<List<Integer>> buildCondensationDAG(List<List<Integer>> graph, SCCResult sccResult) {
        List<List<Integer>> comps = sccResult.components();
        int m = comps.size();
        int[] compId = new int[graph.size()];
        for (int i = 0; i < comps.size(); i++) {
            for (int v : comps.get(i)) compId[v] = i;
        }
        List<Set<Integer>> dagSet = new ArrayList<>();
        for (int i = 0; i < m; i++) dagSet.add(new HashSet<>());
        for (int u = 0; u < graph.size(); u++) {
            for (int v : graph.get(u)) {
                int cu = compId[u];
                int cv = compId[v];
                if (cu != cv) dagSet.get(cu).add(cv);
            }
        }
        List<List<Integer>> dag = new ArrayList<>();
        for (int i = 0; i < m; i++) dag.add(new ArrayList<>(dagSet.get(i)));
        return dag;
    }

    private static List<List<DAGShortestPaths.Edge>> convertToEdges(List<List<Integer>> dag) {
        List<List<DAGShortestPaths.Edge>> edges = new ArrayList<>();
        for (List<Integer> adj : dag) {
            List<DAGShortestPaths.Edge> list = new ArrayList<>();
            for (int v : adj) list.add(new DAGShortestPaths.Edge(v, 1));
            edges.add(list);
        }
        return edges;
    }
}

