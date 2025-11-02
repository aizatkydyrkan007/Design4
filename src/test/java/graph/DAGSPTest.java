package graph;

import graph.dagsp.DAGShortestPaths;
import graph.dagsp.DAGLongestPath;
import graph.utils.SimpleMetrics;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DAGSPTest {

    @Test
    public void testShortestPaths() {
        List<List<DAGShortestPaths.Edge>> dag = Arrays.asList(
                Arrays.asList(new DAGShortestPaths.Edge(1,1), new DAGShortestPaths.Edge(2,2)), // 0 -> 1,2
                List.of(new DAGShortestPaths.Edge(3, 3)), // 1 -> 3
                List.of(new DAGShortestPaths.Edge(3, 1)), // 2 -> 3
                Collections.emptyList() // 3
        );
        SimpleMetrics metrics = new SimpleMetrics();
        int[] dist = DAGShortestPaths.shortestPaths(dag,0,metrics);
        assertEquals(0, dist[0]);
        assertEquals(1, dist[1]);
        assertEquals(2, dist[2]);
        assertEquals(3, dist[3]);
    }

    @Test
    public void testLongestPaths() {
        List<List<DAGShortestPaths.Edge>> dag = Arrays.asList(
                Arrays.asList(new DAGShortestPaths.Edge(1,1), new DAGShortestPaths.Edge(2,2)), // 0 -> 1,2
                List.of(new DAGShortestPaths.Edge(3, 3)), // 1 -> 3
                List.of(new DAGShortestPaths.Edge(3, 1)), // 2 -> 3
                Collections.emptyList() // 3
        );
        SimpleMetrics metrics = new SimpleMetrics();
        int[] dist = DAGLongestPath.longestPaths(dag,0,metrics);
        assertEquals(0, dist[0]);
        assertEquals(1, dist[1]);
        assertEquals(2, dist[2]);
        assertEquals(4, dist[3]);
    }
}
