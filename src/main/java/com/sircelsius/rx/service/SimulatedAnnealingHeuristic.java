package com.sircelsius.rx.service;

import com.sircelsius.rx.model.TravellingSalesmanProblem;
import com.sircelsius.rx.model.TravellingSalesmanSolution;
import io.reactivex.Single;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SimulatedAnnealingHeuristic implements TravellingSalesmanService {
    private final TravellingSalesmanService initialSolutionProvider;

    @Override
    public Single<TravellingSalesmanSolution> solve(TravellingSalesmanProblem problem) {
        return initialSolutionProvider.solve(problem);
    }
}
