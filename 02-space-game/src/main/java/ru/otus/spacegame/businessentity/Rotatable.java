package ru.otus.spacegame.businessentity;

public interface Rotatable {
    int getDirection();
    int getAngularVelocity();
    int getDirectionNumbers();
    void setDirection(int direction);
}
