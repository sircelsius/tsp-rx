package com.sircelsius.rx.model;


import lombok.*;
import lombok.experimental.Builder;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Vertex {
    private final Integer id;
}
