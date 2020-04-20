package com.idealo.robotmover.service;

import com.idealo.robotmover.command.CommandFactory;
import com.idealo.robotmover.command.RobotCommand;
import com.idealo.robotmover.exception.InvalidCommandException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class CommandExtractor {
    public RobotCommand extractCommand(String commandText) {
        if (StringUtils.isBlank(commandText)) {
            throw new InvalidCommandException("command cannot be empty");
        }
        return CommandFactory.from(commandText);
    }


}
