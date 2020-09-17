package rogue.logic.directives;

import rogue.model.report.Report;
import rogue.model.task.Task;
import rogue.model.task.TaskList;
import rogue.storage.Storage;
import rogue.ui.Ui;

/**
 * Lists all stored {@code Task} within the {@code TaskList}.
 */
public class ListDirective implements Executable {
    /**
     * Lists the {@code Task} in the {@code TaskList}.
     *
     * @param storage   An instance of {@code Storage}.
     * @param tasks     The {@code TaskList} from which {@code Task} should be listed.
     * @param ui        An instance of {@code Ui}.
     * @return A {@code Report} with a list of {@code Task}
     */
    @Override
    public Report execute(Storage storage, TaskList tasks, Ui ui) {
        StringBuilder builder = new StringBuilder();
        builder.append("Do you really need me to name them out for you?\n");
        builder.append("\n");

        int i = 0;
        for (Task task : tasks.getTaskList()) {
            builder.append(String.format("%d. %s\n", ++i, task));
        }

        return new Report(builder.toString());
    }
}
