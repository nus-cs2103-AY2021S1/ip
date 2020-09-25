package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

/**
 * Class that simulates the command of the user.
 */
public abstract class Command {

    protected String[] inputArr;

    /**
     * Initialize a Command object.
     *
     * @param inputArr Array of length 2 that contains information of the user input
     * Index 0 contains the type of command while Index 1 contains the message of the command.
     */
    Command(String[] inputArr) {
        this.inputArr = inputArr;
    }

    /**
     * Display out the number of task in the user's list.
     *
     * @param tasks the task object containing the list of task.
     * @return Returns a String representing the number of tasks in the list.
     */
    public String printNumTask(TaskList tasks) {
        return String.format("Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Executes the given command.
     *
     * @param tasks Object contains the task list.
     * @param ui Object that deals with interactions with the user.
     * @param storage Object that deals with loading tasks from the file and saving tasks in the file
     * @return A string representing the outcome of the command.
     * @throws DukeException When the command is invalid, Duke exception will be thrown.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
