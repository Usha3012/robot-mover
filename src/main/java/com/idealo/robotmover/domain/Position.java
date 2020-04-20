package com.idealo.robotmover.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Position {
    private int x;
    private int y;
    private Face faceDirection;
}
