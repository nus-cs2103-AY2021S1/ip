package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

/**
 * Represents the command given by the user.
 */
public abstract class Command {
    protected TaskList taskList;
    protected String command;
    protected Ui ui;
    protected Storage storage;
    protected boolean isExit = false;

    public Command(String command) {
        this.command = command;
    }

    public void init(TaskList taskList, Ui ui, Storage storage) {
        this.ui = ui;
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
     * @param ui       the type of output for the UI depending on the command
     * @param storage  the database to store the user's task list when he/she exits the program
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

}
