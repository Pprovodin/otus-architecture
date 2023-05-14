package ru.otus.spacegame.command.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.spacegame.businessentity.FuelExpending;
import ru.otus.spacegame.command.Command;

@RequiredArgsConstructor
public class CheckFuelCommand implements Command {
    private final FuelExpending fuelExpending;

    @Override
    public void execute() {
        if (fuelExpending.getRemainder() < fuelExpending.getConsumption())
            throw new IllegalStateException("Not enough fuel");
    }
}
