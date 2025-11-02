package graph;

import graph.topo.TopologicalSort;
import graph.utils.SimpleMetrics;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TopoSortTest {

    @Test
    public void testSimpleDAG() {
        List<List<Integer>> dag = Arrays.asList(
                Arrays.asList(1,2), // 0 -> 1,2
                List.of(3),   // 1 -> 3
                List.of(3),   // 2 -> 3
                Collections.emptyList() // 3
        );
        SimpleMetrics metrics = new SimpleMetrics();
        List<Integer> order = TopologicalSort.sort(dag, metrics);
        // Проверяем, что 0 идет перед 1 и 2, а 1,2 перед 3
        assertTrue(order.indexOf(0) < order.indexOf(1));
        assertTrue(order.indexOf(0) < order.indexOf(2));
        assertTrue(order.indexOf(1) < order.indexOf(3));
        assertTrue(order.indexOf(2) < order.indexOf(3));
    }
}
