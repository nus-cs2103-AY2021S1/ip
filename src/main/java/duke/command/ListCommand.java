package duke.command;

import duke.task.TaskList;
import duke.utils.Ui;

/**
 * Represents command that displays current task list in duke.KK upon execution.
 */
public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Uses ui to display the tasks info.
     *
     * @param tasks  a TaskList object which contains a list of task
     * @param ui a UI object which can prints message to console
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.showListMessage(tasks);
    }
}
