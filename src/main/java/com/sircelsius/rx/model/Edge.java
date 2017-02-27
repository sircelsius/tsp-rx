package com.sircelsius.rx.model;

import lombok.*;
import lombok.experimental.Builder;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Edge {
    private final Integer departureVertexId;
    private final Integer arrivalVertexId;
    private final Integer value;
}
