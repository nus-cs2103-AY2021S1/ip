package duke.commands;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskManager;

import java.time.LocalDate;

import java.util.List;

public class TodayTasksCommand extends Command {
    private static final LocalDate TODAY = LocalDate.now();

    @Override
    public CommandOutput executeCommand(TaskManager taskManager, Storage storage) {
        List<Task> tasksForToday = taskManager.findTasksByDate(TODAY);
        StringBuilder tasksForTodayoutput = new StringBuilder();
        tasksForTodayoutput.append("Here are your tasks for today:\n");
        for (int i = 0; i < tasksForToday.size(); i++) {
            String taskDescription = String.format("%d) %s\n", (i + 1), tasksForToday.get(i).toString());
            tasksForTodayoutput.append(taskDescription);
        }
        String completedTasks = this.getNumberOfCompletedTasks(tasksForToday)
                + (this.isPluralCompletedTasks(tasksForToday) ? " tasks" : " task");
        String uncompletedTasks = this.getNumberOfUncompletedTasks(tasksForToday)
                + (this.isPluralUncompletedTasks(tasksForToday) ? " tasks." : " task");
        tasksForTodayoutput.append("You have completed " + completedTasks + " and have yet to complete "
                + uncompletedTasks);
        return new CommandOutput(tasksForTodayoutput.toString(), false);
    }

    private int getNumberOfCompletedTasks(List<Task> tasksForToday) {
        int numOfCompletedTasks = 0;
        for (Task task : tasksForToday) {
            if (task.getIsDone()) {
                numOfCompletedTasks++;
            }
        }
        return numOfCompletedTasks;
    }

    private int getNumberOfUncompletedTasks(List<Task> tasksForToday) {
        int numOfUncompletedTasks = 0;
        for (Task task : tasksForToday) {
            if (!task.getIsDone()) {
                numOfUncompletedTasks++;
            }
        }
        return numOfUncompletedTasks;
    }

    private boolean isPluralCompletedTasks(List<Task> tasksForToday) {
        return this.getNumberOfCompletedTasks(tasksForToday) > 2;
    }

    private boolean isPluralUncompletedTasks(List<Task> tasksForToday) {
        return this.getNumberOfUncompletedTasks(tasksForToday) > 2;
    }
}
