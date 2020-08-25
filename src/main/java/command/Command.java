package main.java.command;

import main.java.exception.UserException;
import main.java.task.TaskList;

import java.util.List;

public abstract class Command {
    List<String> input;

    /**
     * Creates a new command with user's input.
     * @param input
     */
    public Command(List<String> input) {
        this.input = input;
    }

    /**
     * Runs the command.
     * @param taskList the current list of tasks
     * @throws UserException
     */
    abstract public void run(TaskList taskList) throws UserException;
}

