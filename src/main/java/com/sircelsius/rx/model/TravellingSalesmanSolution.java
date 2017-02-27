package com.sircelsius.rx.model;


import lombok.*;
import lombok.experimental.Builder;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class TravellingSalesmanSolution {
    private final List<Vertex> vertices;
    private final Integer cost;
}
