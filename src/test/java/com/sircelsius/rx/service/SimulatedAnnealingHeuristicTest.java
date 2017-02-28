package com.sircelsius.rx.service;

import com.sircelsius.rx.model.Edge;
import com.sircelsius.rx.model.TravellingSalesmanProblem;
import com.sircelsius.rx.model.TravellingSalesmanSolution;
import com.sircelsius.rx.model.Vertex;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SimulatedAnnealingHeuristicTest {
    @Test
    public void solve() throws Exception {
        List<Vertex> vertices = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        Vertex vertex;
        Edge edge;
        int size = 25;

        for (int i = 1; i < size; i++) {
            vertex = Vertex.builder()
                    .id(i)
                    .build();
            vertices.add(vertex);

            for (int j = 1; j < i; j++) {
                edge = Edge.builder()
                        .value( i - j )
                        .departureVertexId(i)
                        .arrivalVertexId( i - j )
                        .build();
                edges.add(edge);
            }

            for ( int k = i + 1; k < size; k++) {
                edge = Edge.builder()
                        .value( size + i - k )
                        .departureVertexId(i)
                        .arrivalVertexId( k )
                        .build();
                edges.add(edge);
            }
        }

        TravellingSalesmanProblem problem = TravellingSalesmanProblem
                .builder()
                .departureId(1)
                .arrivalId(size - 1)
                .vertices(vertices)
                .edges(edges)
                .build();

        TravellingSalesmanService travellingSalesmanService = new NearestNeighbourAlgorithm();
        TravellingSalesmanService service = new SimulatedAnnealingHeuristic(travellingSalesmanService);
        Single<TravellingSalesmanSolution> solution = service.solve(problem);
        TestObserver<TravellingSalesmanSolution> observer = new TestObserver<>();
        solution.subscribe(observer);

        observer.assertSubscribed();
        observer.awaitTerminalEvent();
        observer.assertValueCount(1);
        observer.assertNoErrors();

        observer.values()
                .forEach(travellingSalesmanSolution -> {
                    for (int l = 0; l < size - 2; l++) {
                        if ( l % 2 == 0 ) {
                            assertEquals((long) (l / 2 ) + 1, (long) travellingSalesmanSolution.getVertices().get(l).getId());
                        } else {
                            assertEquals( (long) size - 1 - ( l + 1 ) / 2,
                                    (long) travellingSalesmanSolution.getVertices().get(l).getId());
                        }
                    }
                    assertEquals((long) size - 1, (long) travellingSalesmanSolution.getVertices().get(size - 2).getId());
                });

    }
}