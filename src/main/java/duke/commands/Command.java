package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents the command given by the user.
 */
public abstract class Command {
    protected TaskList taskList;
    protected String command;
    protected Storage storage;
    protected boolean isExit = false;

    public Command(String command) {
        this.command = command;
    }

    /**
     * Sets the respective fields for Command object.
     *
     * @param taskList is the current state of the user's task list.
     * @param storage is the database of the user.
     */
    public void init(TaskList taskList, Storage storage) {
        this.storage = storage;
        this.taskList = taskList;
    }

    public boolean getExitStatus() {
        return this.isExit;
    }

    /**
     * Executes the command given by the user depending on the type of command given.
     *
     * @param taskList the list of tasks to be operated on based on the command
     * @param storage  the database to store the user's task list when he/she exits the program
     */
    public abstract String execute(TaskList taskList, Storage storage);

}
