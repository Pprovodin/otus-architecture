package ru.otus.spacegame.command.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spacegame.businessentity.Movable;
import ru.otus.spacegame.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MoveCommandTest {
    @Mock
    private Movable movable;

    @Captor
    private ArgumentCaptor<Vector> interceptNewPosition;

    private MoveCommand moveCommand;
    private Vector startPosition;
    private Vector startVelocity;

    @BeforeEach
    public void initTest() {
        moveCommand = new MoveCommand(movable);
        startPosition = new Vector(12, 5);
        startVelocity = new Vector(-7, 3);
    }

    @Test
    @DisplayName("Для объекта, находящегося в точке (12, 5) и движущегося со скоростью (-7, 3) движение меняет положение объекта на (5, 8)")
    public void executeWhenGivenPositionAndVelocityThenNewPosition() {
        Vector expectedPosition = new Vector(5, 8);

        when(movable.getPosition()).thenReturn(startPosition);
        when(movable.getVelocity()).thenReturn(startVelocity);
        doNothing().when(movable).setPosition(interceptNewPosition.capture());

        moveCommand.execute();

        Vector newPosition = interceptNewPosition.getValue();

        assertEquals(expectedPosition, newPosition);
    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно прочитать положение в пространстве, приводит к ошибке")
    public void executeWhenExceptionGetPositionThenException() {
        when(movable.getPosition()).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> moveCommand.execute());
    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно прочитать значение мгновенной скорости, приводит к ошибке")
    public void executeWhenExceptionMoveThenException() {
        when(movable.getPosition()).thenReturn(startPosition);
        when(movable.getVelocity()).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> moveCommand.execute());
    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно изменить положение в пространстве, приводит к ошибке")
    public void executeWhenExceptionSetPositionThenException() {
        when(movable.getPosition()).thenReturn(startPosition);
        when(movable.getVelocity()).thenReturn(startVelocity);
        doThrow(IllegalArgumentException.class).when(movable).setPosition(any());

        assertThrows(IllegalArgumentException.class, () -> moveCommand.execute());
    }
}