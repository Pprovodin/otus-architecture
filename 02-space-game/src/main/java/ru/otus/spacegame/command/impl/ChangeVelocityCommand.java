package ru.otus.spacegame.command.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.spacegame.businessentity.VelocityChangeable;
import ru.otus.spacegame.command.Command;
import ru.otus.spacegame.util.Vector;

@RequiredArgsConstructor
public class ChangeVelocityCommand implements Command {
    private final VelocityChangeable velocityChangeable;

    @Override
    public void execute() {
        Vector currentVelocity = velocityChangeable.getVelocity();
        int angleDegrees = (int)(360 * velocityChangeable.getDirection() /
                velocityChangeable.getDirectionNumbers());
        double angle = Math.toRadians(angleDegrees);
        double angleVelocity = Math.sqrt(Math.pow(currentVelocity.getX(),2) +
                Math.pow(currentVelocity.getY(),2));

        Vector newVelocity = new Vector(
                (int)(angleVelocity * Math.cos(angle)),
                (int)(angleVelocity * Math.sin(angle))
        );

        velocityChangeable.setVelocity(newVelocity);
    }
}
