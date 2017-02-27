package com.sircelsius.rx;

import com.sircelsius.rx.model.TravellingSalesmanProblem;
import com.sircelsius.rx.service.NearestNeighbourAlgorithm;
import com.sircelsius.rx.service.TravellingSalesmanService;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class App 
{
    public static void main( String[] args )
    {
        TravellingSalesmanService service = new NearestNeighbourAlgorithm();

        service.solve(TravellingSalesmanProblem.builder().build());
    }
}
