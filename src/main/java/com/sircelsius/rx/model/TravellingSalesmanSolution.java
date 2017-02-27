package com.sircelsius.rx.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Builder;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
public class TravellingSalesmanSolution {
    private final List<Vertex> vertices;
    private final Integer cost;
}
