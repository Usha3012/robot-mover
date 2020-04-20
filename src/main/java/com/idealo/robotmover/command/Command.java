package com.idealo.robotmover.command;

import com.idealo.robotmover.exception.InvalidCommandException;

import java.util.Arrays;

public enum Command {
    FORWARD,
    POSITION,
    RIGHT,
    WAIT,
    TURNAROUND;

    public static Command fromString(String name) {
        return Arrays.stream(values())
                .filter(v -> v.name().equalsIgnoreCase(name))
                .findFirst().orElseThrow(() -> new InvalidCommandException("Command " + name + " is invalid"));
    }
}
