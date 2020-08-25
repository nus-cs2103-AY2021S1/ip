package duke.command;

import duke.component.*;
import duke.task.Task;

/**
 * Command that delete a task in task list and storage
 */
public class DeleteCommand implements Command {
    private int index;

    /**
     * Initializes an DeleteCommand with the given index
     * @param index the index given
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Returns whether this is an exit command
     * @return false at all times
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the DeleteCommand to delete the task in the given task list and storage,
     * and print the response using the given ui
     *
     * @param taskList the task list to be updated
     * @param ui the ui that handles inputs and outputs
     * @param storage the permanent storage of task list
     * @throws DukeException if the user has entered an illegal index,
     *                      or the task list cannot be saved to the storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("The task index should be an index on the list!");
        }
        Task task = taskList.get(index);
        taskList.remove(index);
        storage.saveList(taskList);

        ui.giveResponse(" Noted. I've removed this task:\n       " +
                task +
                "\n\t Now you have " + taskList.size() +
                " task" + (taskList.size() > 1 ? "s" : "") + " in the list.");
    }
}
