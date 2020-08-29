package main.java.command;

import java.util.List;

import main.java.exception.UserException;
import main.java.task.TaskList;


public abstract class Command {
    private List<String> input;

    /**
     * Creates a new command with user's input.
     *
     * @param input
     */
    public Command(List<String> input) {
        this.input = input;
    }

    /**
     * Runs the command.
     *
     * @param taskList the current list of tasks
     * @throws UserException
     */
    public abstract void run(TaskList taskList) throws UserException;
}

