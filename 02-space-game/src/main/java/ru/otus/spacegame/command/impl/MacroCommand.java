package ru.otus.spacegame.command.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.spacegame.command.Command;

import java.util.Arrays;

@RequiredArgsConstructor
public class MacroCommand implements Command {
    private final Command[] commands;

    @Override
    public void execute() {
        Arrays.stream(commands).forEach(Command::execute);
    }
}
