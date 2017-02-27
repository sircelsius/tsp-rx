package com.sircelsius.rx.service;

import com.sircelsius.rx.model.TravellingSalesmanProblem;
import com.sircelsius.rx.model.TravellingSalesmanSolution;
import io.reactivex.Single;

@FunctionalInterface
public interface TravellingSalesmanService {
    public Single<TravellingSalesmanSolution> solve(TravellingSalesmanProblem problem);
}
