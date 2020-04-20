package com.idealo.robotmover.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idealo.robotmover.RobotMoverApplication;
import static io.restassured.RestAssured.*;

import com.idealo.robotmover.domain.Face;
import com.idealo.robotmover.domain.Position;
import com.idealo.robotmover.dto.RobotMoveRequest;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = RobotMoverApplication.class)
public class RobotControllerIntegrationTest {

    private final ObjectMapper objectMapper=new ObjectMapper();
    @LocalServerPort
    private int port;

    @BeforeEach
    public  void init(){
        RestAssured.port = port;
    }

    @Test
    public void testMoveRobotWithValidCommand() throws JsonProcessingException {
        RobotMoveRequest request = new RobotMoveRequest();
        request.setCurrentPosition(new Position(0,0, Face.EAST));
        request.setCommands(Arrays.asList("POSITION 1 3 EAST","WAIT","TURNAROUND"));
        given()
                .body(objectMapper.writeValueAsString(request))
                .contentType("application/json")
                .log().all()
        .when()
                .post("/robots/move")
                .prettyPeek()
        .then()
                 .statusCode(200)
                .body("position.faceDirection", Matchers.is("WEST"));
    }

    @Test
    public void testMoveRobotWithInValidCommand() throws JsonProcessingException {
        RobotMoveRequest request = new RobotMoveRequest();
        request.setCurrentPosition(new Position(0,0, Face.EAST));
        request.setCommands(Arrays.asList("INVALID 1 3 EAST","WAIT","TURNAROUND"));
        given()
                .body(objectMapper.writeValueAsString(request))
                .contentType("application/json")
                .log().all()
                .when()
                .post("/robots/move")
                .prettyPeek()
                .then()
                .statusCode(400);
    }

    @Test
    public void testMoveRobotWithOverTheGrid() throws JsonProcessingException {
        RobotMoveRequest request = new RobotMoveRequest();
        request.setCurrentPosition(new Position(0,0, Face.EAST));
        request.setCommands(Arrays.asList("POSITION 1 3 EAST","FORWARD 2","WAIT","TURNAROUND"));
        given()
                .body(objectMapper.writeValueAsString(request))
                .contentType("application/json")
                .log().all()
                .when()
                .post("/robots/move")
                .prettyPeek()
                .then()
                .statusCode(400);
    }

    @Test
    public void testMoveRobotWithValidRightTurn() throws JsonProcessingException {
        RobotMoveRequest request = new RobotMoveRequest();
        request.setCurrentPosition(new Position(0,0, Face.EAST));
        request.setCommands(Arrays.asList("POSITION 1 3 EAST","FORWARD 1","WAIT","RIGHT"));
        given()
                .body(objectMapper.writeValueAsString(request))
                .contentType("application/json")
                .log().all()
                .when()
                .post("/robots/move")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("position.faceDirection", Matchers.is("SOUTH"))
                .body("position.x", Matchers.is(1))
                .body("position.y", Matchers.is(4));
    }
}
