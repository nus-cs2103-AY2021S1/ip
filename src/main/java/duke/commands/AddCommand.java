package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.task.Task;

/**
 * Handles the addition of a task in the chatbot.
 */
public class AddCommand implements Command {
    private Task task;

    /**
     * AddCommand constructor.
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the addition of task and sends the appropriate response to the user.
     * It also stores the changes to the storage.
     * @param tasks The TaskList.
     * @param ui The Ui.
     * @param storage The Storage.
     * @return A boolean to indicate that it is not the terminating command.
     * @throws DukeException Exceptions when executing the different methods of TaskList,
     * Ui and Storage.
     */
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addToPlanner(task);
        ui.addMessage(task, tasks.getSize());
        storage.save(tasks.getPlanner());
        return false;
    }

    @Override
    public String toString() {
        return "Command: Add " + task.toString() + " to TaskList";
    }

}
