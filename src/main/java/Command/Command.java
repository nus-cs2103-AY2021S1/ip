package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

/**
 * Command is an abstract class that will execute user inputs from the Parser
 * and cause changes to the TaskList and Ui.
 *
 * @author Joshua
 */
public abstract class Command {
    /**
     * task is the task that may be involved with the Command.
     * output is the message to be displayed to the user.
     */
    protected Task task;
    protected String output = "";

    /**
     * Carries out the given Command with the given TaskList, Ui and Storage. This is
     * an abstract method and needs to be overridden.
     *
     * @param taskList the TaskList to be updated.
     * @param ui the Ui that interacts with the user.
     * @param storage the Storage that is updated with TaskList.
     * @return output to be displayed to the user.
     * @throws DukeException throws exception that prints error message.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
