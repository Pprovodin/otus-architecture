package ru.otus.spacegame.command.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spacegame.businessentity.FuelExpending;
import ru.otus.spacegame.command.Command;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CheckFuelCommandTest {
    @Mock
    private FuelExpending fuelExpending;

    private Command command;

    @BeforeEach
    void initTest() {
        command = new CheckFuelCommand(fuelExpending);
    }

    @Test
    @DisplayName("Not enough fuel")
    public void testExecuteWhenFuelNotEnoughThenException() {
        when(fuelExpending.getRemainder()).thenReturn(1);
        when(fuelExpending.getConsumption()).thenReturn(2);

        assertThrows(IllegalStateException.class, () -> command.execute());
    }

    @Test
    @DisplayName("Enough fuel")
    public void testExecuteWhenFuelEnoughThenNoExceptionThrown() {
        when(fuelExpending.getRemainder()).thenReturn(10);
        when(fuelExpending.getConsumption()).thenReturn(2);

        assertDoesNotThrow(command::execute);
    }
}