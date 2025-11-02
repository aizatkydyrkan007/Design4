🏙️ Smart City Task Scheduling

This project implements task scheduling algorithms for Smart City / Smart Campus environments.
It uses classical graph algorithms to analyze dependencies, define execution order, and optimize total task duration.

🎯 Objectives

Detect cycles in task dependencies

Convert cyclic graphs into DAGs using SCC compression

Determine valid task execution order via Topological Sorting

Compute minimum and maximum completion times for all tasks

Identify the critical path and bottleneck tasks

⚙️ Implemented Algorithms
Component	Algorithm	Purpose
Strongly Connected Components (SCC)	Tarjan's Algorithm	Detect cycles and group mutually dependent tasks
Condensation Graph	DAG Construction	Collapse SCCs into single nodes
Topological Sort	Kahn's Algorithm	Ensure valid execution order
Shortest Path in DAG	Dynamic Programming	Compute minimum project completion time
Longest Path / Critical Path	Dynamic Programming	Identify maximum time and critical task chain
🗂️ Data Format

All datasets are stored in the data/ folder.

Example Input (JSON)
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

🧪 Test Results

All automated unit tests passed successfully.

Test Suite	Tests Run	Failures	Errors	Skipped	Time (s)
DAGSPTest	2	0	0	0	0.071
TarjanSCCTest	2	0	0	0	0.010
TopoSortTest	1	0	0	0	0.004

✅ Build Status: SUCCESS
Total Tests Run: 5 | Failures: 0 | Errors: 0 | Skipped: 0

📊 Analysis

SCC detection runs efficiently even on large graphs.

Graph density and number of cycles influence runtime performance.

Shortest Path computation is fast since DAGs contain no cycles.

Sparse graphs are easier to sort topologically, while dense graphs require more memory.

🧭 Conclusions

Use SCC to detect and compress cyclic dependencies.

Apply Topological Sort to define valid task execution order.

Use Shortest Path to compute minimal completion times.

Use Longest Path to identify critical paths and total project duration.

These algorithms form the foundation for task planning, maintenance scheduling, and workflow optimization in Smart City systems.
