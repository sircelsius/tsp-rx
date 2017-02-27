package com.sircelsius.rx;

import com.sircelsius.rx.model.Edge;
import com.sircelsius.rx.model.TravellingSalesmanProblem;
import com.sircelsius.rx.model.Vertex;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class App 
{
    public static void main( String[] args )
    {
        log.info("Creating a simple TSP.");

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

        TravellingSalesmanProblem problem = builder.edges(edges)
                .vertices(vertices)
                .departureId(0)
                .arrivalId(10)
                .build();

        log.info("Generated TSP: {}", problem.toString());
    }
}
