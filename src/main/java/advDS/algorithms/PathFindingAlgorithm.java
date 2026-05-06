package advDS.algorithms;

import advDS.graph.*;

public interface PathFindingAlgorithm {
    PathResult findPath(Graph graph, Node start, Node goal);
}