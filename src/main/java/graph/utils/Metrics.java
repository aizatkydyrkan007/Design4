package graph.utils;

public interface Metrics {
    void increment(String counterName);
    void reset();
    long get(String counterName);
}
