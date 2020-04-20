package com.idealo.robotmover.service;

import com.idealo.robotmover.command.RobotCommand;
import com.idealo.robotmover.domain.Robot;
import com.idealo.robotmover.dto.RobotLocation;
import com.idealo.robotmover.dto.RobotMoveRequest;
import com.idealo.robotmover.exception.InvalidPositionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RobotMover {
    private final CommandExtractor commandExtractor;
    private final ConstraintChecker constraintChecker;

    public RobotLocation move(RobotMoveRequest robotMove) {
        Robot robot = new Robot();
        robot.setCurrentPosition(robotMove.getCurrentPosition());
        List<RobotCommand> commands = robotMove.getCommands().stream()
                .map(this::createCommand)
                .collect(Collectors.toList());
        return run(commands, robot);
    }

    private RobotLocation run(List<RobotCommand> commands, Robot robot) {
        for ( RobotCommand command : commands ) {
            robot.run(command);
            if (!constraintChecker.isValidPosition(robot.getCurrentPosition())) {
                log.error("Robot position is wrong {}", robot.getCurrentPosition());
                throw new InvalidPositionException("position " + robot.getCurrentPosition().getX() + ","
                        + robot.getCurrentPosition().getY() + " is wrong grid size is ("
                        + constraintChecker.getMaxX() + "X" + constraintChecker.getMaxY() + ")");
            }
        }
        RobotLocation robotLocation = new RobotLocation();
        robotLocation.setPosition(robot.getCurrentPosition());
        return robotLocation;
    }

    private RobotCommand createCommand(String command) {
        return commandExtractor.extractCommand(command);
    }
}
