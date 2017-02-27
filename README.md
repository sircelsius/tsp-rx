# RxJava Travelling Salesman Problem

## Goal

Demonstrate how to solve the [TSP](https://simple.wikipedia.org/wiki/Travelling_salesman_problem) both with the [Nearest Neughbour algorithm](https://en.wikipedia.org/wiki/Nearest_neighbour_algorithm) (NN) and [Simulated Annealing heuristic](https://en.wikipedia.org/wiki/Simulated_annealing) (SA).

## Implementing a simple NN implementation

I have added a simple unit test for the NN algorithm.

I have also created an implementation of our `TravellingSalesmanService` that doesn't do anything (it returns the input problem's vertices and a constant cost of 42).

*Task* Implement a correct implementation of the Nearest Neighbour Algorithm. It should pass the test defined in [`src/test/java/com/sircelsius/rx/service/NearestNeighbourAlgorithmTest.java`](src/test/java/com/sircelsius/rx/service/NearestNeighbourAlgorithmTest.java)
