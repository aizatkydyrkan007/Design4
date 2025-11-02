package graph.utils;

import java.util.HashMap;
import java.util.Map;

public class SimpleMetrics implements Metrics {
    private final Map<String, Long> counters = new HashMap<>();

    @Override
    public void increment(String counterName) {
        counters.put(counterName, counters.getOrDefault(counterName, 0L) + 1);
    }

    @Override
    public void reset() {
        counters.clear();
    }

    @Override
    public long get(String counterName) {
        return counters.getOrDefault(counterName, 0L);
    }

    public void print() {
        System.out.println("Metrics:");
        counters.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
