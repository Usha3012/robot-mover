package com.idealo.robotmover.command;

import com.idealo.robotmover.exception.InvalidCommandException;

public class CommandFactory {

    public static RobotCommand from(String command) {
        String[] commandParts = command.split("\\s+");
        switch (Command.fromString(commandParts[0])) {
            case FORWARD:
                return MoveCommand.fromString(command);
            case WAIT:
                return new WaitCommand();
            case TURNAROUND:
                return new TurnCommand();
            case POSITION:
                return PositionCommand.fromString(command);
            case RIGHT:
                return new RightCommand();
            default:
                throw new InvalidCommandException(command + " is invalid");
        }
    }
}
