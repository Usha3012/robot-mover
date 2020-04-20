package com.idealo.robotmover.command;

import com.idealo.robotmover.domain.Robot;
import com.idealo.robotmover.exception.InvalidCommandException;

public class MoveCommand implements RobotCommand {
    private final int steps;

    public MoveCommand(int steps) {
        this.steps = steps;
    }

    public static MoveCommand fromString(String commandText) {
        String[] parts = commandText.toLowerCase().split("\\s+");
        int steps = 0;
        if (parts.length < 2) {
            throw new InvalidCommandException(commandText + " is wrong should be " + seeUsage());
        }
        try {
            steps = Integer.parseInt(parts[1]);
        } catch (NumberFormatException ex) {
            throw new InvalidCommandException(commandText + "is wrong steps should be integer number");
        }
        return new MoveCommand(steps);
    }

    private static String seeUsage() {
        return "FORWARD <steps>";
    }

    @Override
    public void execute(Robot robot) {
        robot.moveForward(steps);
    }

}
