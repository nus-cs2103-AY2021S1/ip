package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command that will execute the delete command.
 */
public class DeleteCommand extends Command {
    /**
     * User given input to indicate which task to delete.
     */
    private final String input;

    /**
     * Initializes a new instance of deleteCommand.
     *
     * @param input ID of task to delete.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the deletion of a task from the bot task list and storage then
     * prints out a message upon success.
     *
     * @param taskList          The list of tasks known by the chat bot.
     * @param ui                The Ui that is used by the chat bot.
     * @param storage           The storage used by the chat bot.
     * @return                  Chat bot message
     * @throws DukeException    If the execution fails at any step.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int num = Integer.parseInt(this.input);
            Task curr = taskList.deleteTask(num);
            storage.deleteTask(num);
            String numTasks = "Now you have " + taskList.size() + " tasks in the list.\n";
            return ui.replyDelete(String.format("%s\n%s", curr.toString(), numTasks));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please key in the number of an existing task to be removed!");
        }
    }
}
