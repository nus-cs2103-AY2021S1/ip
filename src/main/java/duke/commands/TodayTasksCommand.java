package duke.commands;

import duke.task.Task;
import duke.task.TaskManager;
import duke.utils.Messages;

import java.time.LocalDate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TodayTasksCommand extends Command {
    private static final LocalDate TODAY = LocalDate.now();

    @Override
    public CommandOutput executeCommand(TaskManager taskManager) {
        List<Task> tasksForToday = taskManager.findTasksByDate(TODAY);
        int numberOfCompletedTasks = this.getNumberOfCompletedTasks(tasksForToday);
        int numberOfUncompletedTasks = this.getNumberOfUncompletedTasks(tasksForToday);
        String tasksForTodayOutput = outputResult(tasksForToday, numberOfCompletedTasks, numberOfUncompletedTasks);
        return new CommandOutput(tasksForTodayOutput, false);
    }

    private String outputResult(List<Task> tasksForToday, int numberOfCompletedTasks, int numberOfUncompletedTasks) {
        StringBuilder tasksForTodayOutput = new StringBuilder();
        boolean hasTasksForToday = tasksForToday.size() > 0;
        if (hasTasksForToday) {
            tasksForTodayOutput.append("Here are your tasks for today:\n");
            AtomicInteger listNumber = new AtomicInteger();
            for (Task task : tasksForToday) {
                String taskDescription = task.toString();
                String taskDescriptionInListFormat = String.format("%d) %s\n", listNumber.get() + 1, taskDescription);
                tasksForTodayOutput.append(taskDescriptionInListFormat);
                listNumber.getAndIncrement();
            }
            boolean hasMoreThanOneCompletedTasks = numberOfCompletedTasks > 1;
            String completedTasks = numberOfCompletedTasks
                    + (hasMoreThanOneCompletedTasks ? " tasks" : " task");
            boolean hasMoreThanOneUncompletedTasks = numberOfUncompletedTasks > 1;
            String uncompletedTasks = numberOfUncompletedTasks
                    + (hasMoreThanOneUncompletedTasks ? " tasks" : " task");
            tasksForTodayOutput.append("You have completed " + completedTasks + " and have yet to complete "
                    + uncompletedTasks);
        } else {
            tasksForTodayOutput.append(Messages.NO_TASKS_FOR_TODAY_MESSAGE);
        }
        return tasksForTodayOutput.toString();
    }

    private int getNumberOfCompletedTasks(List<Task> tasksForToday) {
        int numOfCompletedTasks = (int) tasksForToday.stream().filter(Task::getIsDone).count();
        return numOfCompletedTasks;
    }

    private int getNumberOfUncompletedTasks(List<Task> tasksForToday) {
        int numOfUncompletedTasks = (int) tasksForToday.stream().filter(task -> !task.getIsDone()).count();
        return numOfUncompletedTasks;
    }
}
