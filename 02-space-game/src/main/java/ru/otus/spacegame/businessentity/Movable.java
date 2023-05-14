package ru.otus.spacegame.businessentity;

import ru.otus.spacegame.util.Vector;

public interface Movable {
    Vector getPosition();
    void setPosition(Vector position);
    Vector getVelocity();
}
