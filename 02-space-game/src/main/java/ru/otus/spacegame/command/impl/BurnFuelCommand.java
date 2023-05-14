package ru.otus.spacegame.command.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.spacegame.businessentity.FuelExpending;
import ru.otus.spacegame.command.Command;

@RequiredArgsConstructor
public class BurnFuelCommand implements Command {
    private final FuelExpending fuelExpending;

    @Override
    public void execute() {
        fuelExpending.setRemainder(fuelExpending.getRemainder() - fuelExpending.getConsumption());
    }
}
