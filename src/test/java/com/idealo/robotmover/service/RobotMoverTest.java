package com.idealo.robotmover.service;

import com.idealo.robotmover.domain.Face;
import com.idealo.robotmover.domain.Position;
import com.idealo.robotmover.dto.RobotLocation;
import com.idealo.robotmover.dto.RobotMoveRequest;
import com.idealo.robotmover.exception.InvalidCommandException;
import com.idealo.robotmover.exception.InvalidPositionException;
import com.idealo.robotmover.service.CommandExtractor;
import com.idealo.robotmover.service.ConstraintChecker;
import com.idealo.robotmover.service.RobotMover;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class RobotMoverTest {
    private final ConstraintChecker constraintChecker = new ConstraintChecker(5, 5);
    private final CommandExtractor commandExtractor = new CommandExtractor();
    private final RobotMover mover = new RobotMover(commandExtractor, constraintChecker);

    @Test
    @DisplayName("Move Robot with single command")
    public void testMoveRobot() {
        LinkedList<String> commands = new LinkedList<>();
        commands.add("POSITION 1 3 EAST");
        RobotMoveRequest request = new RobotMoveRequest();
        request.setCurrentPosition(new Position(0, 0, Face.EAST));
        request.setCommands(commands);
        RobotLocation location = mover.move(request);
        assertAll("Robot command",
                () -> assertEquals(new Position(1, 3, Face.EAST), location.getPosition()));
    }

    @Test
    @DisplayName("Move Robot with multiple command")
    public void testMoveRobotAndTurn() {
        LinkedList<String> commands = new LinkedList<>();
        commands.add("POSITION 1 3 EAST");
        commands.add("TURNAROUND");
        RobotMoveRequest request = new RobotMoveRequest();
        request.setCurrentPosition(new Position(0, 0, Face.EAST));
        request.setCommands(commands);
        RobotLocation location = mover.move(request);
        assertEquals(new Position(1, 3, Face.WEST), location.getPosition());
    }

    @Test
    @DisplayName("Move Robot with multiple command")
    public void testMoveRobotAndMoveAndTurn() {
        LinkedList<String> commands = new LinkedList<>();
        commands.add("POSITION 1 3 EAST");
        commands.add("FORWARD 1");
        commands.add("TURNAROUND");
        RobotMoveRequest request = new RobotMoveRequest();
        request.setCurrentPosition(new Position(0, 0, Face.EAST));
        request.setCommands(commands);
        RobotLocation location = mover.move(request);
        assertEquals(new Position(1, 4, Face.WEST), location.getPosition());
    }

    @Test
    @DisplayName("Move Robot with multiple command")
    public void testMoveRobotOverGrid() {
        LinkedList<String> commands = new LinkedList<>();
        commands.add("POSITION 1 3 EAST");
        commands.add("FORWARD 2");
        commands.add("TURNAROUND");
        RobotMoveRequest request = new RobotMoveRequest();
        request.setCurrentPosition(new Position(0, 0, Face.EAST));
        request.setCommands(commands);
        assertThrows(InvalidPositionException.class, () -> mover.move(request));
    }

    @Test
    @DisplayName("Move Robot with multiple command")
    public void testMoveRobotWithInvalidCommand() {
        LinkedList<String> commands = new LinkedList<>();
        commands.add("");
        RobotMoveRequest emptyRequest = new RobotMoveRequest();
        emptyRequest.setCurrentPosition(new Position(0,0,Face.EAST));
        emptyRequest.setCommands(commands);

        commands.clear();
        commands.add(" ");

        RobotMoveRequest blankRequest = new RobotMoveRequest();
        blankRequest.setCurrentPosition(new Position(0,0,Face.EAST));
        blankRequest.setCommands(commands);

        commands.clear();
        commands.add(null);

        RobotMoveRequest nullRequest = new RobotMoveRequest();
        nullRequest.setCurrentPosition(new Position(0,0,Face.EAST));
        nullRequest.setCommands(commands);

        assertAll("Invalid Commands",
                ()->assertThrows(InvalidCommandException.class, () -> mover.move(emptyRequest)),
                ()->assertThrows(InvalidCommandException.class, () -> mover.move(blankRequest)),
                ()->assertThrows(InvalidCommandException.class, () -> mover.move(nullRequest)));
        ;
    }
}
