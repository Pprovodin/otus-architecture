package ru.otus.spacegame.command.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.otus.spacegame.businessentity.FuelExpending;
import ru.otus.spacegame.businessentity.Movable;
import ru.otus.spacegame.command.Command;
import ru.otus.spacegame.util.Vector;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MacroCommandFuelTest {
    @Mock
    private Movable movable;
    @Mock
    private FuelExpending fuelExpending;

    private Command macroCommand;

    @BeforeEach
    void initTest() {
        macroCommand = new MacroCommand(
                new Command[]{new CheckFuelCommand(fuelExpending), new MoveCommand(movable),
                        new BurnFuelCommand(fuelExpending)}
        );

        when(movable.getPosition()).thenReturn(new Vector(0, 0));
        when(movable.getVelocity()).thenReturn(new Vector(1, 0));
        doNothing().when(movable).setPosition(any());

        when(fuelExpending.getConsumption()).thenReturn(1);
        doNothing().when(fuelExpending).setRemainder(anyInt());
    }

    @Test
    @DisplayName("Enough fuel")
    public void testExecuteWhenFuelEnoughThenRunThreeCommand() {
        when(fuelExpending.getRemainder()).thenReturn(10);
        // todo: write normal text to check method behavior
        macroCommand.execute();
    }

    @Test
    @DisplayName("Not enough fuel")
    public void testExecuteWhenFuelNotEnoughtThenException() {
        when(fuelExpending.getRemainder()).thenReturn(0);

        assertThrows(IllegalStateException.class, macroCommand::execute);
    }

}
