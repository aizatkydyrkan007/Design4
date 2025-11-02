package graph;


import graph.scc.SCCResult;
import graph.scc.TarjanSCC;
import graph.utils.SimpleMetrics;
import org.junit.jupiter.api.Test;


import java.util.*;


import static org.junit.jupiter.api.Assertions.*;


public class TarjanSCCTest {


    @Test
    public void testSmallGraph() {
        List<List<Integer>> graph = Arrays.asList(
                List.of(1), // 0 -> 1
                List.of(2), // 1 -> 2
                Arrays.asList(0,3), // 2 -> 0,3
                List.of(4), // 3 -> 4
                Collections.emptyList() // 4
        );
        SimpleMetrics metrics = new SimpleMetrics();
        TarjanSCC tarjan = new TarjanSCC(graph, metrics);
        SCCResult result = tarjan.computeSCCs();
        assertEquals(3, result.components().size());
    }


    @Test
    public void testSingleNode() {
        List<List<Integer>> graph = Collections.singletonList(Collections.emptyList());
        SimpleMetrics metrics = new SimpleMetrics();
        TarjanSCC tarjan = new TarjanSCC(graph, metrics);
        SCCResult result = tarjan.computeSCCs();
        assertEquals(1, result.components().size());
    }
}