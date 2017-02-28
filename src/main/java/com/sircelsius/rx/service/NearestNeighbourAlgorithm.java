package com.sircelsius.rx.service;

import com.sircelsius.rx.model.Edge;
import com.sircelsius.rx.model.TravellingSalesmanProblem;
import com.sircelsius.rx.model.TravellingSalesmanSolution;
import com.sircelsius.rx.model.Vertex;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import static io.reactivex.Single.error;
import static io.reactivex.Single.just;

@Log4j2
@NoArgsConstructor
public class NearestNeighbourAlgorithm implements TravellingSalesmanService {
    @Override
    public Single<TravellingSalesmanSolution> solve(TravellingSalesmanProblem problem) {
        log.info("Solving Problem: {}", problem);

        List<Vertex> vertices = new ArrayList<>();
        Optional<Vertex> initialVertex = getById(problem.getVertices(), problem.getDepartureId());

        if( !initialVertex.isPresent() ) {
            return error(new Exception("Departure not found"));
        }

        vertices.add(initialVertex.get());

        return getRecursive(problem, vertices, initialVertex.get())
                .map(verticesList -> {
                    Optional<Vertex> arrival = getById(problem.getVertices(), problem.getArrivalId());

                    if( !arrival.isPresent() ) {
                        throw new Exception("Arrival not found");
                    }
                    verticesList.add(arrival.get());

                    TravellingSalesmanSolution solution = TravellingSalesmanSolution.builder()
                            .vertices(verticesList)
                            .cost(getCost(problem, verticesList))
                            .build();

                    log.info("Returning Solution: {}", solution);
                    return solution;
                });
    }

    private Single<List<Vertex>> getRecursive(TravellingSalesmanProblem problem,
                                              List<Vertex> vertices,
                                              Vertex current) {
        if(vertices.size() >= problem.getVertices().size() - 1) {
            return just(vertices);
        }

        return getNearestNotVisited(problem, vertices, current)
                .flatMap(vertex -> {
                    vertices.add(vertex);
                    return getRecursive(problem, vertices, vertex);
                });
    }

    private Single<Vertex> getNearestNotVisited(TravellingSalesmanProblem problem,
                                                List<Vertex> visited,
                                                Vertex current) {
        return getEdgesToNeighbours(problem, current)
                .sorted((e1, e2) -> Long.compare(e1.getValue(), e2.getValue()))
                .map(edge -> getById(problem.getVertices(), edge.getArrivalVertexId()))
                .filter(vertex -> vertex.isPresent()
                        && !visited.contains(vertex.get())
                        && !(Objects.equals(vertex.get().getId(), problem.getArrivalId()))
                )
                .map(vertex -> vertex.isPresent() ? vertex.get() : null )
                .firstOrError();

    }

    private Observable<Edge> getEdgesToNeighbours(TravellingSalesmanProblem problem,
                                                  Vertex origin) {
        return Observable.fromIterable(problem.getEdges())
                .filter(edge -> Objects.equals(edge.getDepartureVertexId(), origin.getId()));
    }

    private Optional<Vertex> getById(List<Vertex> vertices, Integer id) {
        return vertices.stream()
                .filter(vertex -> Objects.equals(vertex.getId(), id))
                .findFirst();
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
