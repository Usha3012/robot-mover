package com.idealo.robotmover.command;

import com.idealo.robotmover.domain.Robot;

public interface RobotCommand {
    default void noop() {

    }

    void execute(Robot robot);

}
