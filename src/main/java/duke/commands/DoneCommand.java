package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;
import duke.exception.InvalidCommand;

/**
 * Represents a command to mark a task done in the task list.
 *
 */
public class DoneCommand extends Command {
    private int taskIndex;

    /**
     * Creates DoneCommand with given index.
     *
     * @param taskIndex Index of item to be mark as done in the current task list.
     */
    public DoneCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    /**
     * Executes main logic to mark a task done from the task list.
     * Displays task marked successfully message to user.
     *
     * @param ui Ui used to generate messages to users.
     * @param listStorage Backend storage to store items in the task list.
     * @param taskList List of tasks added by users so far.
     */
    public void execute(Ui ui, Storage listStorage, TaskList taskList) {
        try {
            Task editedTask = taskList.get(this.taskIndex);
            listStorage.editTask(editedTask, this.taskIndex, taskList);
            ui.markAsDone(this.taskIndex, taskList);
        } catch (IndexOutOfBoundsException ex) {
            try {
                throw new InvalidCommand("Please enter a valid task number.");
            } catch (InvalidCommand invalidCommand) {
                invalidCommand.printStackTrace();
            }
        }
    }
}
