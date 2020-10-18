package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that deletes a task.
 * @version 1.0
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Creates a new DeleteCommand object with the given task index in the task list.
     *
     * @param taskIndex Index of the task in the task list.
     */
    public DeleteCommand(int taskIndex) {
        this.commandName = "Delete";
        this.taskIndex = taskIndex;
        this.isExit = false;
    }

    /**
     * Deletes the task indexed from the specified TaskList.
     * Updates the specified storage with the changed TaskList.
     * Show action feedback to user.
     *
     * @param list A TaskList object of which the command is executed on.
     * @param ui An UI object to interact with the user if required by the command.
     * @param storage A Storage object to write/access information to/from a file if required by the command.
     * @throws DukeException thrown if any DukeException is thrown by the called functions.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        if (taskIndex > list.getActiveTasks()) {
            throw new DukeException("Task at index does not exist.");
        }
        Task task = list.getTaskAtIndex(taskIndex);
        list.deleteTask(task);
        storage.write(list.getList());
        ui.showDelete(task);
        list.printList("All");
    }
}
