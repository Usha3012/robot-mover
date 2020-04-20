# Robot Mover

The robot mover application takes current position of robot on grid and a list of
commands to execute and it returns the final position of robot on the grid or error
if otherwise.

It is assumed any frontend will send the below request and calculate the final positon.
The application is stateless it does not store the last location of robot so it is required
for the client to send current position.

## How to Run
- JAVA 1.8
- Run `mvn spring-boot:run`

## API

POST /robots/move

REQUEST 

```json
{
    "currentPosition": {
        "x": 0,
        "y": 0,
        "faceDirection": "EAST"
    },
    "commands": [
        "POSITION 1 3 EAST",
        "FORWARD 1",
        "TURNAROUND",
        "WAIT",
        "RIGHT"
    ]
}
```

RESPONSE
```json
{
    "position": {
        "x": 1,
        "y": 4,
        "faceDirection": "NORTH"
    }
}
```
## Improvements

Didn't get time to create a small front end app to use the API. 