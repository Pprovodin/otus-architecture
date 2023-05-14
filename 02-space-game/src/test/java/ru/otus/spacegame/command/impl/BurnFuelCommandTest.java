package ru.otus.spacegame.command.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spacegame.businessentity.FuelExpending;
import ru.otus.spacegame.command.Command;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BurnFuelCommandTest {
    @Mock
    private FuelExpending fuelExpending;

    @Captor
    private ArgumentCaptor<Integer> interceptValueFuel;

    private Command command;

    @BeforeEach
    void initTest() {
        command = new BurnFuelCommand(fuelExpending);
    }

    @Test
    @DisplayName("Fuel reduction")
    public void testExecuteVerifyExpendFuel() {
        when(fuelExpending.getRemainder()).thenReturn(10);
        when(fuelExpending.getConsumption()).thenReturn(2);
        doNothing().when(fuelExpending).setRemainder(interceptValueFuel.capture());

        command.execute();

        assertEquals(8, interceptValueFuel.getValue());
    }
}