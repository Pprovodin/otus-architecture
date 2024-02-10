package ru.otus.spacegame.command.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spacegame.businessentity.VelocityChangeable;
import ru.otus.spacegame.command.Command;
import ru.otus.spacegame.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChangeVelocityCommandTest {
    @Mock
    private VelocityChangeable velocityChangeable;

    @Captor
    ArgumentCaptor<Vector> interceptNewVelocity;

    private Command command;

    @BeforeEach
    void setUoTest(){
        command = new ChangeVelocityCommand(velocityChangeable);
    }

    @Test
    @DisplayName("Тестирование изменения скорости, при повороте на 90 градусов, " +
            "объект со скоростью (0, 100) меняет значения вертикальной/горизонтальной скорости")
    void testExecuteWhenAngleNinetyDegreesThenChangeValueVectorVelocity() {
        when(velocityChangeable.getVelocity()).thenReturn(new Vector(100, 0));
        when(velocityChangeable.getDirection()).thenReturn(1);
        when(velocityChangeable.getDirectionNumbers()).thenReturn(4);
        doNothing().when(velocityChangeable).setVelocity(interceptNewVelocity.capture());

        command.execute();

        Vector expectedVelocity = new Vector(0, 100);
        Vector actualVelocity = interceptNewVelocity.getValue();

        assertEquals(expectedVelocity, actualVelocity);
    }

}