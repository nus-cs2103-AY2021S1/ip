package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command that will terminate the program.
 */
public class ByeCommand extends Command {
    /**
     * Executes the Bye command by printing a farewell message.
     *
     * @param tasks   The list of tasks known by the chat bot.
     * @param ui      The UI that is used by the chat bot.
     * @param storage The storage that is used by the chat bot.
     * @return A string detailing the outcome of the execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns <code>true</code> to indicate that this command should result in the termination of
     * the program.
     *
     * @return <code>true</code>
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
