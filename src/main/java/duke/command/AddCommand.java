package duke.command;

import duke.exception.DukeException;
import duke.storage.StateManager;
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
     * @param tasks The TaskList.
     * @param stateManager The Storage.
     * @return CommandResult object for ui
     * @throws DukeException If storage could not be updated.
     */
    @Override
    public CommandResult execute(TaskList tasks, StateManager stateManager) throws DukeException {
        assert tasks != null && stateManager != null;
        tasks.addTask(taskToAdd);
        stateManager.updateState(tasks);
        return new CommandResult("Got it. I've added this task:\n"
                + taskToAdd.toString() + "\n"
                + "Now you have " + tasks.numberOfTasks() + " tasks in the list.");
    }
}
