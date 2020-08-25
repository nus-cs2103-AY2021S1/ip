package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates a command that will mark a task as done.
 */
public class DoneCommand extends Command {
    /**
     * User given input which will specify which task to mark as done.
     */
    private final String input;

    /**
     * Initializes a new DoneCommand.
     *
     * @param input ID of task to mark as done.
     */
    public DoneCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the done command by marking the task with the given ID as done, updating the storage and
     * printing a message upon success.
     *
     * @param taskList          The list of tasks known by the chat bot.
     * @param ui                The Ui that is used by the chat bot.
     * @param storage           The storage used by the chat bot.
     * @throws DukeException    If the execution fails at any step.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int num = Integer.parseInt(this.input);
            Task curr = taskList.getTask(num);
            curr.markAsDone();
            storage.updateTask(num, curr);
            ui.replyDone(curr.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please key in the number of an existing task to be marked as done!");
        }
    }
}
