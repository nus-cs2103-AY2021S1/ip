package dd.commands;

import dd.exception.DukeException;
import dd.storage.DataStorage;
import dd.tasks.TaskList;
import dd.ui.Ui;

/**
 * A command with a certain type and user input modifies
 * the task list stored in the system.
 */
public abstract class Command {
    protected String command;
    protected String item;

    /**
     * Class Constructor.
     *
     * @param command Command given.
     * @param item Details of item.
     */
    public Command(String command, String item) {
        this.command = command;
        this.item = item;
    }

    /**
     * Executes the appropriate method based on command.
     *
     * @param taskList Current TaskList to modify.
     * @param u Ui used to print statements.
     * @param ds DataStorage used to load or write data.
     * @throws DukeException If details in item are invalid.
     */
    public abstract void execute(TaskList taskList, Ui u, DataStorage ds) throws DukeException;

    /**
     * Returns whether exit command is given.
     *
     * @return true if exit command is given, false if not.
     */
    public abstract boolean isExit();
}
