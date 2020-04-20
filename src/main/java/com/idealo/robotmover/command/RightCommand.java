package com.idealo.robotmover.command;

import com.idealo.robotmover.domain.Robot;

public class RightCommand implements RobotCommand {
    private int steps = 0;

    public RightCommand() {

    }

    public RightCommand(int steps) {
        this.steps = steps;
    }

    @Override
    public void execute(Robot robot) {
        robot.moveRight(steps);
    }
}
