package duke.commands;

import duke.util.Ui;
import duke.util.Storage;
import duke.util.DukeException;
import duke.tasks.TaskList;
import duke.tasks.Task;

/**
 * commands.Command that list out the task in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * List out the task in TaskList
     * @param taskList TaskList containing Tasks.
     * @param ui Ui that handles system output.
     * @param storage Storage that handles file saving.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.print();
    }
}
