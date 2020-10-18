package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that does a task.
 * @version 1.0
 */
public class DoCommand extends Command {
    private final int taskIndex;

    /**
     * Creates a new DoCommand with the given task index in the task list.
     *
     * @param taskIndex Index of the task in the task list.
     */
    public DoCommand(int taskIndex) {
        this.commandName = "Do";
        this.taskIndex = taskIndex;
        this.isExit = false;
    }

    /**
     * Sets the status of the task indexed in the specified TaskList to be done.
     * Updates the specified storage with the changed TaskList.
     * Shows action feedback to user.
     *
     * @param list A TaskList object of which the command is executed on.
     * @param ui An UI object to interact with the user if required by the command.
     * @param storage A Storage object to write/access information to/from a file if required by the command.
     * @throws DukeException thrown if any DukeException is thrown by the called functions.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        if (taskIndex > list.getActiveTasks()) {
            throw new DukeException("Task at index does not exist or already marked as done.");
        }
        Task task = list.getTaskAtIndex(taskIndex);
        list.markTaskAsDone(task);
        storage.write(list.getList());
        ui.showDone(task);
        list.printList("Undone");
    }
}
