package duke.commands;

import duke.exceptions.DukeException;

import duke.task.TaskManager;

/**
 * Represents a user command. Different types of command should extend
 * from this abstract class and implement its own respective executeCommand
 * method.
 */

public abstract class Command {
    public abstract CommandOutput executeCommand(TaskManager taskManger) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
