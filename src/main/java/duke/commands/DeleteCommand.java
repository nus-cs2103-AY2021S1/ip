package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidCommand;
import duke.tasks.Task;

/**
 * Represents a command to delete task from the task list.
 *
 */
public class DeleteCommand extends Command {
    private int itemIndex;

    /**
     * Creates DeleteCommand with given index.
     *
     * @param itemIndex Index of item to be deleted in the current task list.
     */
    public DeleteCommand (int itemIndex) {
        super();
        this.itemIndex = itemIndex;
    }

    /**
     * Executes main logic to delete task from the task list.
     * Displays task deleted message to user.
     *
     * @param ui Ui used to generate messages to users.
     * @param listStorage Backend storage to store items in the task list.
     * @param taskList List of tasks added by users so far.
     * @return UI message after executing delete command.
     * @throws InvalidCommand Invalid task number given.
     */
    @Override
    public String execute(Ui ui, Storage listStorage, TaskList taskList) throws InvalidCommand {
        try {
            Task removedTask = taskList.removeTask(itemIndex - 1);
            listStorage.deleteTask(removedTask);
            return ui.deleteTask(removedTask, taskList);
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidCommand("Please enter a valid task number.");
        }
    }
}
