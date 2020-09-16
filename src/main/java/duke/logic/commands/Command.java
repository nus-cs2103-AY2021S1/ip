package duke.logic.commands;

import java.io.IOException;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.model.TaskManager;
import duke.model.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Abstract class for the different Duke commands.
 */
public abstract class Command {
    protected String command;

    /**
     * Constructor for Commands.
     *
     * @param command String input by user.
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Abstract method for executing user commands.
     *
     * @param tm TaskManager that handles tasks in memory.
     * @param ui User interface that interacts with the user.
     * @param storage Storage class that handles saving and loading from file.
     * @return Response generated after command is executed.
     * @throws DukeException If command is not properly formatted.
     */
    public abstract String execute(TaskManager tm, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns the exit signal for Duke to terminate.
     *
     * @return False for all Commands except ExitCommand.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Updates the DB at the end of command execution.
     *
     * @param tm TaskManager used to manage tasks.
     * @param storage Storage class that handles saving to disk.
     */
    public void postCommandSave(TaskManager tm, Storage storage) {
        ArrayList<Task> taskList = tm.getTaskList();
        try {
            storage.save(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
