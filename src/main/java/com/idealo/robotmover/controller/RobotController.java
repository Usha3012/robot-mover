package com.idealo.robotmover.controller;

import com.idealo.robotmover.dto.RobotLocation;
import com.idealo.robotmover.dto.RobotMoveRequest;
import com.idealo.robotmover.service.RobotMover;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RobotController {
    private final RobotMover robotMover;

    @RequestMapping(value = "/robots/move", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RobotLocation> moveRobot(@RequestBody RobotMoveRequest robotMove) {
        RobotLocation location = robotMover.move(robotMove);
        return ResponseEntity.ok(location);
    }
}
