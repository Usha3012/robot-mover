package com.idealo.robotmover.command;

import com.idealo.robotmover.domain.Robot;

public class TurnCommand implements RobotCommand {

    @Override
    public void execute(Robot robot) {
         robot.turn();
    }
}
