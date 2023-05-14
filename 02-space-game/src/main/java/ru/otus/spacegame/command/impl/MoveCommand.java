package ru.otus.spacegame.command.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.spacegame.businessentity.Movable;
import ru.otus.spacegame.command.Command;
import ru.otus.spacegame.util.Vector;

@RequiredArgsConstructor
public class MoveCommand implements Command {
    private final Movable movable;

    @Override
    public void execute() {
        movable.setPosition(new Vector(
                movable.getPosition().getX() + movable.getVelocity().getX(),
                movable.getPosition().getY() + movable.getVelocity().getY()
        ));
    }
}
