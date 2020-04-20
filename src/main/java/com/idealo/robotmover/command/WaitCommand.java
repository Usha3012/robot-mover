package com.idealo.robotmover.command;

import com.idealo.robotmover.domain.Robot;

public class WaitCommand implements RobotCommand {
    @Override
    public void execute(Robot robot) {
        noop();
    }
}
