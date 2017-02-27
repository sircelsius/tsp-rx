package com.sircelsius.rx.service;

import com.sircelsius.rx.model.TravellingSalesmanProblem;
import com.sircelsius.rx.model.TravellingSalesmanSolution;
import io.reactivex.Single;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
public class NearestNeighbourAlgorithm implements TravellingSalesmanService {
    @Override
    public Single<TravellingSalesmanSolution> solve(TravellingSalesmanProblem problem) {
        log.info("Solving Problem: {}", problem);
        TravellingSalesmanSolution.TravellingSalesmanSolutionBuilder builder =
                TravellingSalesmanSolution.builder();

        TravellingSalesmanSolution solution = builder.vertices(problem.getVertices())
                .cost(42)
                .build();

        log.info("Returning Solution: {}", solution);

        return Single.just(solution);
    }
}
