package duke.command;

import duke.util.TaskList;
import duke.util.Ui;

/**
 * {@code: ExitCommand} is a child of {@code: Command} object.
 *      On execution, it will set the isTerminated flag to true to stop the programme.
 */
public class ExitCommand extends Command {

    /**
     * Set the isTerminated flag to true.
     *
     * @param tasks The list of task.
     * @param ui The displaying user interface.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        Command.isTerminated = true;
    }
}
