package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Handles the addition of a task in the chatbot.
 */
public class AddCommand implements Command {
    private Task task;

    /**
     * AddCommand constructor.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the addition of task and sends the appropriate response to the user and
     * stores the changes to the storage.
     *
     * @param tasks   The TaskList.
     * @param ui      The Ui.
     * @param storage The Storage.
     * @return The add message by the Ui.
     * @throws DukeException Exceptions when executing the different methods of TaskList,
     *                       Ui and Storage.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeException {
        tasks.addToPlanner(task);
        String response = ui.addMessage(task, tasks.getSize());
        storage.save(tasks.getPlanner());
        return response;
    }

    @Override
    public String toString() {
        return "Command: Add " + task.toString() + " to TaskList";
    }

}
