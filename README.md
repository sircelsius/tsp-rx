# RxJava Travelling Salesman Problem

## Goal

Demonstrate how to solve the [TSP](https://simple.wikipedia.org/wiki/Travelling_salesman_problem) both with the [Nearest Neughbour algorithm](https://en.wikipedia.org/wiki/Nearest_neighbour_algorithm) (NN) and [Simulated Annealing heuristic](https://en.wikipedia.org/wiki/Simulated_annealing) (SA).

## Basic Model

Good, you now have the basic project set up.

I have created a few classes to model a simple Travelling Salesman Problem and its solutions, go ahead and check them out in [`src/main/java/com/sircelsius/rx/model`](src/main/java/com/sircelsius/rx/model).

I also created an interface to solve our problem: [`src/main/java/com/sircelsius/rx/service/TravellingSalesmanService.java`](src/main/java/com/sircelsius/rx/service/TravellingSalesmanService.java).

If you run the application with `./gradlew run`, you should see a simple problem printed out to your terminal.

Play around with the model. Once you're done, move on to [`1.1.0`](https://github.com/sircelsius/tsp-rx/tree/1.1.0)