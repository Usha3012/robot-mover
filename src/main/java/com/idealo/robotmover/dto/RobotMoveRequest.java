package com.idealo.robotmover.dto;

import com.idealo.robotmover.domain.Position;
import lombok.Data;

import java.util.List;

@Data
public class RobotMoveRequest {
    private Position currentPosition;
    private List<String> commands;
}
