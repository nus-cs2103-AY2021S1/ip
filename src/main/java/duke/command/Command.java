package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

/**
 * Encapsulates a command
 * All command types must extend from this class
 */
public abstract class Command {

    /**
     * Executes the command
     *
     * @param storage Storage
     * @param tasks Task list
     * @param ui Ui
     * @return Output strings
     */
    public abstract String[] execute(Storage storage, TaskList tasks, Ui ui);
}
