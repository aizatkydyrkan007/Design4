# Assignment 4 – Smart City Task Scheduling

## Project Goal
This project implements algorithms for managing tasks in Smart City or Smart Campus scenarios. It combines:

- Strongly Connected Components (SCC) and Topological Sort
- Shortest and Longest Paths in Directed Acyclic Graphs (DAG)

The algorithms help detect cycles, plan a valid task order, and calculate optimal task completion times.

## Example Dataset
All datasets are stored in `/data/`. Each dataset contains:
- Number of nodes and edges
- Whether the graph is cyclic or a DAG
- Edge weights or node durations

Example JSON:
```json
{
  "directed": true,
  "n": 8,
  "edges": [
    {"u": 0, "v": 1, "w": 3},
    {"u": 1, "v": 2, "w": 2},
    {"u": 2, "v": 3, "w": 4},
    {"u": 3, "v": 1, "w": 1},
    {"u": 4, "v": 5, "w": 2},
    {"u": 5, "v": 6, "w": 5},
    {"u": 6, "v": 7, "w": 1}
  ],
  "source": 4,
  "weight_model": "edge"
}
Methods
SCC and Condensation Graph
Tarjan's algorithm identifies strongly connected components (SCCs). Condensation graph is built as a DAG where each node represents an SCC.

Topological Sort
Kahn's algorithm determines a valid order of SCCs to plan tasks step by step.

Shortest and Longest Paths
Dynamic programming is applied on the DAG:

Shortest Path computes minimal total time or cost.

Longest Path (Critical Path) identifies the slowest chain of tasks.

Test Results
All automated tests passed successfully:

Test Suite	Tests Run	Failures	Errors	Skipped	Time (s)
DAGSPTest	2	0	0	0	0.071
TarjanSCCTest	2	0	0	0	0.010
TopoSortTest	1	0	0	0	0.004

Build Status: SUCCESS
Total tests run: 5
Failures: 0
Errors: 0
Skipped: 0

Analysis
SCC detection is fast even on large graphs.

Graph density and number of cycles affect runtime.

Shortest path computation is efficient since DAGs have no cycles.

Sparse graphs are easier to sort topologically; dense graphs take more memory.

Conclusions
Use SCC to detect and compress cycles.

Use Topological Sort to determine a valid task execution order.

Use Shortest Path to find minimal completion times.

Use Longest Path to identify critical paths and total duration.

These algorithms are useful for task planning, maintenance, and scheduling in Smart City scenarios.
