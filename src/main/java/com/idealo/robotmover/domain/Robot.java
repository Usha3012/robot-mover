package com.idealo.robotmover.domain;

import com.idealo.robotmover.command.RobotCommand;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Robot {
    private Position currentPosition;


    public void run(RobotCommand command) {
        command.execute(this);
    }

    public void position(int x, int y) {
        this.currentPosition = new Position(x, y, currentPosition.getFaceDirection());
    }


    public void moveRight(int steps) {
        log.debug("Move Right "+steps+" steps");
        switch (currentPosition.getFaceDirection()) {
            case NORTH:
                moveEast(steps);
                break;
            case SOUTH:
                moveWest(steps);
                break;
            case EAST:
                moveSouth(steps);
                break;
            case WEST:
                moveNorth(steps);
                break;
        }
    }

    public void moveForward(int steps) {
        log.debug("Move Forward "+steps+" steps");
        switch (currentPosition.getFaceDirection()) {
            case EAST:
                moveEast(steps);
                break;
            case WEST:
                moveWest(steps);
                break;
            case SOUTH:
                moveSouth(steps);
                break;
            case NORTH:
                moveNorth(steps);
                break;
        }
    }


    private void moveEast(int steps) {
        this.currentPosition = new Position(currentPosition.getX(), currentPosition.getY() + steps,
                Face.EAST);

    }

    private void moveWest(int steps) {
        this.currentPosition = new Position(currentPosition.getX(), currentPosition.getY() - steps,
                Face.WEST);
    }

    private void moveNorth(int steps) {
        this.currentPosition = new Position(currentPosition.getX() - steps, currentPosition.getY(),
                Face.NORTH);
    }

    private void moveSouth(int steps) {
        this.currentPosition = new Position(currentPosition.getX() + steps, currentPosition.getY(),
                Face.SOUTH);
    }

    public void turn() {
        switch (currentPosition.getFaceDirection()) {
            case EAST:
                this.currentPosition = new Position(currentPosition.getX(), currentPosition.getY(), Face.WEST);
                break;
            case WEST:
                this.currentPosition = new Position(currentPosition.getX(), currentPosition.getY(), Face.EAST);
                break;
            case NORTH:
                this.currentPosition = new Position(currentPosition.getX(), currentPosition.getY(), Face.SOUTH);
                break;
            case SOUTH:
                this.currentPosition = new Position(currentPosition.getX(), currentPosition.getY(), Face.NORTH);
                break;
        }
    }
}
