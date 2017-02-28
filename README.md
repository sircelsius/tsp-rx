# RxJava Travelling Salesman Problem

## Goal

Demonstrate how to solve the [TSP](https://simple.wikipedia.org/wiki/Travelling_salesman_problem) both with the [Nearest Neughbour algorithm](https://en.wikipedia.org/wiki/Nearest_neighbour_algorithm) (NN) and [Simulated Annealing heuristic](https://en.wikipedia.org/wiki/Simulated_annealing) (SA).

## Implementing the NN Algorithm

I have added a working implementation of the Nearest Neighbour Algorithm. See [`src/main/java/com/sircelsius/rx/service/NearestNeighbourAlgorithm.java`](src/main/java/com/sircelsius/rx/service/NearestNeighbourAlgorithm.java) and compare to your implementation!

We are now going to work on the Simulated Annealing. We will implement a version where the initial solution will be provided by our Nearest Neighbour Algorithm.

**Task** Implement a version of SA. It should pass the test defined in [`src/test/java/com/sircelsius/rx/service/SimulatedAnnealingHeuristicTest.java`](src/test/java/com/sircelsius/rx/service/SimulatedAnnealingHeuristicTest.java)

Once you have a working solution, check out [`1.3.0`](https://github.com/sircelsius/tsp-rx/tree/1.3.0) and follow the steps there.