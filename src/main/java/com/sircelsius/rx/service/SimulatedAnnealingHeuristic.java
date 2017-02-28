package com.sircelsius.rx.service;

import com.sircelsius.rx.model.Edge;
import com.sircelsius.rx.model.TravellingSalesmanProblem;
import com.sircelsius.rx.model.TravellingSalesmanSolution;
import com.sircelsius.rx.model.Vertex;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.*;
import java.util.stream.IntStream;

import static io.reactivex.Single.just;

@AllArgsConstructor
@Log4j2
public class SimulatedAnnealingHeuristic implements TravellingSalesmanService {
    private final TravellingSalesmanService initialSolutionProvider;

    private final double MIN_TEMPERATURE = 0.001;

    private final double START_TEMPERATURE = 100.0;

    private final double TEMPERATURE_FACTOR = 0.9;

    @Override
    public Single<TravellingSalesmanSolution> solve(TravellingSalesmanProblem problem) {
        return initialSolutionProvider.solve(problem);
    }

    private Single<TravellingSalesmanSolution> getRecursive(
            TravellingSalesmanSolution currentSolution,
            TravellingSalesmanProblem problem,
            double currentTemperature) {

        if(Math.abs(currentTemperature) < MIN_TEMPERATURE) {
            return just(currentSolution);
        }
        return getRandomChange(currentSolution, problem)
                .flatMap(solution -> {
                    // if better solution take it
                    if(solution.getCost() <= currentSolution.getCost()) {
                        return getRecursive(solution, problem, currentTemperature);
                    }

                    // lower temperature
                    double newTemperature = TEMPERATURE_FACTOR * currentTemperature;

                    // small probability to still take the lesser solution
                    if(getProbability(currentSolution.getCost(), solution.getCost(), newTemperature)) {
                        return getRecursive(solution, problem, newTemperature);
                    }
                    // else iterate again on same solution
                    return getRecursive(currentSolution, problem, newTemperature);
                });
    }

    private Single<TravellingSalesmanSolution> getRandomChange(TravellingSalesmanSolution solution,
                                                               TravellingSalesmanProblem problem) {
        return just(solution)
                .map(s -> swapElementsInList(s.getVertices()))
                .map(vertices -> TravellingSalesmanSolution.builder()
                .vertices(vertices)
                .cost(getCost(problem, vertices))
                .build()
                );
    }

    private List<Vertex> swapElementsInList(List<Vertex> vertices) {
        int size = vertices.size();
        List<Vertex> result = new ArrayList<>();
        result.addAll(vertices);

        if ( size < 3) {
            return vertices;
        }

        Random generator = new Random();
        // generate random integer between 1 and size - 2.
        // This is so that we do not take the first or two before last vertex.
        int random = 1 + generator.nextInt(size - 2);
        Vertex tempVertex = vertices.get(random);
        result.set(random, vertices.get(random + 1));
        result.set(random +  1, tempVertex);
        return result;
    }

    private Boolean getProbability(int oldEnergy, int newEnergy, double temperature) {
        return Math.exp(( oldEnergy - newEnergy ) / temperature) > Math.random();
    }

    private Integer getCost(TravellingSalesmanProblem problem, List<Vertex> vertices) throws Exception {
        return IntStream.range(0, vertices.size() - 1)
                .map(i -> {
                    Optional<Edge> edgeOptional = problem.getEdges().stream()
                            .filter(edge -> Objects.equals(edge.getDepartureVertexId(), vertices.get(i).getId())
                                    && Objects.equals(edge.getArrivalVertexId(), vertices.get(i + 1).getId())
                            )
                            .findAny();

                    if( ! edgeOptional.isPresent() ) {
                        log.error("Unable to find Edge");
                        return 0;
                    }
                    return edgeOptional.get().getValue();
                })
                .sum();
    }
}
