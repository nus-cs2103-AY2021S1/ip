package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates a list command for the Duke program. This is the command that lists out the tasks in the task list
 * and presents them to the user. The format for this command is: "list".
 */
public class ListCommand extends Command {

    /**
     * Executes the command and lists out the tasks in the task list and presents them to the user.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The Ui object being used in the program.
     * @param storage The Storage object being used in the program.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showReply(ui.showTaskList(tasks));
    }
}
