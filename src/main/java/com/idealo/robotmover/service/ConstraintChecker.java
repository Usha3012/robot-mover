package com.idealo.robotmover.service;

import com.idealo.robotmover.domain.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConstraintChecker {

    @Value("${grid.x:5}")
    private int maxX;
    @Value("${grid.y:5}")
    private int maxY;

    public boolean isValidPosition(Position position) {

        return (position.getX() > 0 && position.getX() < maxX) && (position.getY() > 0 && position.getY() < maxY);
    }
}
