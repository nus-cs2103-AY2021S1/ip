package rogue.logic.directives;

import rogue.storage.Storage;
import rogue.model.task.TaskList;
import rogue.ui.Ui;

import rogue.model.task.Task;

import rogue.logic.Report;

import java.util.List;

public class FindDirective implements Executable {
    private final String MESSAGE_FIND_SUCCESS = "wHaT? iS NaP TiMe oVeR? hErE ArE YoUr tAsKs tHeN:";
    private final String MESSAGE_FIND_FAILURE = "oF CoUrSe %s iS NoT FoUnD. "
            + "DoN'T YoU EvEn rEmEmBeR ThE TaSkS YoU AdDeD?";

    private final String searchTerm;

    public FindDirective(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public Report execute(Storage storage, TaskList tasks, Ui ui) {
        List<Task> matchingTasks = tasks.search(searchTerm);

        if (matchingTasks.isEmpty()) {
            return new Report(MESSAGE_FIND_FAILURE);
        } else {
            StringBuilder builder = new StringBuilder();

            builder.append(String.format(MESSAGE_FIND_SUCCESS, searchTerm));
            builder.append("\n");
            for (Task task : matchingTasks) {
                builder.append(task);
            }

            return new Report(builder.toString());
        }
    }
}
