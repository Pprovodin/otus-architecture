package ru.otus.spacegame.command.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.spacegame.businessentity.Rotatable;
import ru.otus.spacegame.command.Command;

@RequiredArgsConstructor
public class RotateCommand implements Command {
    private final Rotatable rotatable;

    @Override
    public void execute() {
        rotatable.setDirection(
                (rotatable.getDirection() + rotatable.getAngularVelocity()) % rotatable.getDirectionNumbers()
        );
    }
}
