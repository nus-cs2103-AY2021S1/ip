package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates a command to list the tasks in the current task list
 */
public class ListTasksCommand extends Command {

    /**
     * Executes the command to list the tasks in the current task list.
     *
     * @param storage Storage
     * @param tasks Task list
     * @param ui Ui
     * @return Output strings displayed on the UI showing current task list
     */
    @Override
    public String[] execute(Storage storage, TaskList tasks, Ui ui) {
        assert tasks != null;
        assert ui != null;

        return ui.getTaskListStrings(tasks);
    }
}
