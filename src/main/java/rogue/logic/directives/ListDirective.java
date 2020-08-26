package rogue.logic.directives;

import rogue.storage.Storage;
import rogue.model.task.TaskList;
import rogue.ui.Ui;

import rogue.model.task.Task;

import rogue.logic.Report;

public class ListDirective implements Executable {
    @Override
    public Report execute(Storage storage, TaskList tasks, Ui ui) {
        StringBuilder builder = new StringBuilder();
        int i = 0;

        builder.append("dO YoU ReAlLy nEeD Me tO NaMe tHeM OuT foR yOu?\n");
        for (Task task : tasks.getTaskList()) {
            builder.append(String.format("%d. %s\n", ++i, task));
        }

        return new Report(builder.toString());
    }
}
