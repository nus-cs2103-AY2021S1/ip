package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class AddCommand extends Command {
    private Task taskToAdd;


    /**
     * Initializes an AddCommand with the task to be added as the input.
     *
     * @param taskToAdd The task to be added.
     */
    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    /**
     * Adds the taskToAdd to the TaskList and updates the Storage.
     *
     * @param tasks   The TaskList.
     * @param storage The Storage.
     * @return CommandResult object for ui
     * @throws DukeException If storage could not be updated.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null && storage != null;
        tasks.addTask(taskToAdd);
        storage.updateStorage(tasks);
        return new CommandResult("Got it. I've added this task:\n"
                + taskToAdd.toString() + "\n"
                    + "Now you have " + tasks.numberOfTasks() + " tasks in the list.");
    }
}
