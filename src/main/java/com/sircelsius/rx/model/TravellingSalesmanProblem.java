package com.sircelsius.rx.model;


import lombok.*;
import lombok.experimental.Builder;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class TravellingSalesmanProblem {
    private final Integer departureId;
    private final Integer arrivalId;
    private final List<Vertex> vertices;
    private final List<Edge> edges;
}
