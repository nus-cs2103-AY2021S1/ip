package duke.commands;

import duke.storage.Storage;

import duke.task.TaskManager;

import duke.utils.Ui;

/**
 * Represents a user command. Different types of command should extend
 * from this abstract class and implement its own respective executeCommand
 * method.
 */

public abstract class Command {
    public abstract void executeCommand(TaskManager taskManger, Ui formatter, Storage storage);

    public boolean isExit() {
        return false;
    }
}
