package duke.commands;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import duke.task.Task;
import duke.task.TaskManager;

import duke.utils.Messages;

/**
 * Represents the command which will list out all the tasks that are currently stored in the storage
 * upon execution.
 */
public class ListTasksCommand extends Command {

    /**
     * Retrieves all the user's {@code Task} and returns an output containing it.
     *
     * @param taskManager the {@code TaskManager} object that contains the list of {@code Task}s.
     * @return the output from executing the command.
     */
    @Override
    public CommandOutput executeCommand(TaskManager taskManager) {
        List<Task> allTasks = taskManager.getAllTasks();
        int numberOfCompletedTasks = taskManager.getNumberOfCompletedTasks();
        int numberOfUncompletedTasks = taskManager.getNumberOfUncompletedTasks();
        String listAllTasksOutput = outputResult(allTasks, numberOfCompletedTasks, numberOfUncompletedTasks);
        return new CommandOutput(listAllTasksOutput, false);
    }

    private String outputResult(List<Task> allTasks, int numberOfCompletedTasks, int numberOfUncompletedTasks) {
        StringBuilder allTasksOutput = new StringBuilder();
        boolean hasTasks = allTasks.size() > 0;
        if (hasTasks) {
            allTasksOutput.append("Here are all your tasks: \n");
            AtomicInteger listNumber = new AtomicInteger();
            for (Task task : allTasks) {
                String taskDescription = task.toString();
                String taskDescriptionInListFormat = String.format("%d) %s\n", listNumber.get() + 1, taskDescription);
                allTasksOutput.append(taskDescriptionInListFormat);
                listNumber.getAndIncrement();
            }
            boolean hasMoreThanOneCompletedTask = numberOfCompletedTasks > 1;
            boolean hasMoreThanOneUncompletedTask = numberOfUncompletedTasks > 1;
            String completedTasks = numberOfCompletedTasks + (hasMoreThanOneCompletedTask ? " tasks" : " task");
            String uncompletedTasks = numberOfUncompletedTasks + (hasMoreThanOneUncompletedTask ? " tasks." : " task");
            allTasksOutput.append("You have completed " + completedTasks + " and have yet to complete "
                    + uncompletedTasks);
        } else {
            allTasksOutput.append(Messages.NO_TASKS_MESSAGE);
        }
        return allTasksOutput.toString();
    }
}
