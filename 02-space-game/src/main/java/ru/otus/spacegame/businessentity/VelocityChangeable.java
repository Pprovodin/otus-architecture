package ru.otus.spacegame.businessentity;

import ru.otus.spacegame.util.Vector;

public interface VelocityChangeable {
    Vector getVelocity();
    void setVelocity(Vector velocity);
    int getDirection();
    int getDirectionNumbers();
}
