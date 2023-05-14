package ru.otus.spacegame.exception;

public class CommandException extends RuntimeException {
    public CommandException(String message) {
        super(message);
    }
}
