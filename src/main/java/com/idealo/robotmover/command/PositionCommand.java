package com.idealo.robotmover.command;

import com.idealo.robotmover.domain.Face;
import com.idealo.robotmover.domain.Position;
import com.idealo.robotmover.domain.Robot;
import com.idealo.robotmover.exception.InvalidCommandException;

public class PositionCommand implements RobotCommand {
    private int x;
    private int y;
    private Face face;

    public PositionCommand(int x, int y, Face face) {
        this.x = x;
        this.y = y;
        this.face = face;
    }

    public static PositionCommand fromString(String commandText) {
        String[] parts = commandText.toLowerCase().split("\\s+");
        int x = 0;
        int y = 0;
        if (parts.length < 4) {
            throw new InvalidCommandException(commandText + " is wrong should be " + seeUsage());
        }
        try {
            x = Integer.parseInt(parts[1]);
            y = Integer.parseInt(parts[2]);
        } catch (NumberFormatException ex) {
            throw new InvalidCommandException(commandText + "is wrong steps should be integer number");
        }
        Face face = Face.valueOf(parts[3].toUpperCase());
        return new PositionCommand(x, y, face);
    }

    private static String seeUsage() {
        return "POSITION <x>,<y>,<EAST|WEST|NORTH|SOUTH";
    }

    @Override
    public void execute(Robot robot) {
        robot.setCurrentPosition(new Position(x, y, face));
    }
}
