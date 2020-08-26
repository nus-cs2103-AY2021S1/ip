package main.java.command;

import main.java.*;
import main.java.exceptions.InvalidFileException;
import main.java.exceptions.InvalidInputException;
import main.java.tasks.TaskList;

public abstract class Command {
    public final String input;

    public Command(String input) {
        this.input = input;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException, InvalidFileException;

    public abstract boolean isExit();

}
