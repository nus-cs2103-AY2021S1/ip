package main.java.farrell.duke.command;

import main.java.farrell.duke.DukeException;
import main.java.farrell.duke.task.TaskList;

public abstract class Command {
    protected CommandType type;

    /**
     * Determines if the program should exit after the command is run
     * @return true if the program should exit. false otherwise.
     */
    public boolean shouldExit() {
        return false;
    };

    /**
     * Executes the instructions corresponding to the command
     * @param taskList The task list to execute instructions on.
     * @return The output message of the command
     * @throws DukeException If a problem is encountered while executing the instructions.
     */
    public abstract String execute(TaskList taskList) throws DukeException;
}
