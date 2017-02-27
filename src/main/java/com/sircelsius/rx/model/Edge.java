package com.sircelsius.rx.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Builder;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
public class Edge {
    private final Integer departureVertexId;
    private final Integer arrivalVertexId;
    private final Integer value;
}
