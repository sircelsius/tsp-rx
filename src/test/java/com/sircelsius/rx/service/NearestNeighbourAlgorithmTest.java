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

public class NearestNeighbourAlgorithmTest {
    @Test
    public void simpleTest() throws Exception {
        TravellingSalesmanProblem problem = getProblem();

        TravellingSalesmanService service = new NearestNeighbourAlgorithm();

        Single<TravellingSalesmanSolution> single = service.solve(problem);
        TestObserver<TravellingSalesmanSolution> subscriber = new TestObserver<>();

        single.subscribe(subscriber);

        subscriber.assertNoErrors();
        subscriber.assertValueCount(1);
        subscriber.assertValue(getExpectedSolution());
    }

    private TravellingSalesmanProblem getProblem() {
        TravellingSalesmanProblem.TravellingSalesmanProblemBuilder builder =
                TravellingSalesmanProblem.builder();

        List<Vertex> vertices = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();

        for( int i = 0; i < 10; i++) {
            vertices.add(Vertex.builder().id(i).build());
            edges.add(Edge.builder()
                    .departureVertexId(i)
                    .arrivalVertexId(i+1)
                    .value(i)
                    .build()
            );
        }
        vertices.add(Vertex.builder().id(10).build());

        return builder.edges(edges)
                .vertices(vertices)
                .departureId(0)
                .arrivalId(10)
                .build();

    }

    private TravellingSalesmanSolution getExpectedSolution() {
        TravellingSalesmanSolution.TravellingSalesmanSolutionBuilder builder =
                TravellingSalesmanSolution.builder();
        List<Vertex> vertices = new ArrayList<>();
        Integer cost = 0;

        for(int i = 0; i <= 10; i++) {
            vertices.add(Vertex.builder().id(i).build());
            cost += i;
        }

        return builder.cost(cost)
                .vertices(vertices)
                .build();
    }
}