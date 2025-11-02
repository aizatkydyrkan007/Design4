package graph.scc;

import java.util.List;

public record SCCResult(List<List<Integer>> components) {

    public void printSummary() {
        System.out.println("SCC count: " + components.size());
        for (int i = 0; i < components.size(); i++) {
            System.out.println("SCC " + (i + 1) + ": " + components.get(i) + " (size=" + components.get(i).size() + ")");
        }
    }
}
