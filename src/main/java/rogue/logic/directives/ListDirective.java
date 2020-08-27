package rogue.logic.directives;

import rogue.model.task.Task;
import rogue.model.task.TaskList;

import rogue.logic.Report;

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
        int i = 0;

        builder.append("dO YoU ReAlLy nEeD Me tO NaMe tHeM OuT foR yOu?\n");
        for (Task task : tasks.getTaskList()) {
            builder.append(String.format("%d. %s\n", ++i, task.toString()));
        }

        return new Report(builder.toString());
    }
}
