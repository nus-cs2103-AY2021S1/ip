package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;

import duke.task.TaskManager;

/**
 * Represents a user command. Different types of command should extend
 * from this abstract class and implement its own respective executeCommand
 * method.
 */

public abstract class Command {
    public abstract CommandOutput executeCommand(TaskManager taskManger, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
