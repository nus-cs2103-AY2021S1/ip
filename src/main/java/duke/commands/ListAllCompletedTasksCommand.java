package duke.commands;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import duke.task.Task;
import duke.task.TaskManager;

import duke.utils.Messages;

/**
 * Represents the user command that will list out all of the user's completed tasks.
 */
public class ListAllCompletedTasksCommand extends Command {

    /**
     * Finds all {@code Task} that is marked as completed and returns an output containing it.
     *
     * @param taskManger the {@code TaskManager} object that contains the list of {@code Task}s.
     * @return the output from executing the command.
     */
    @Override
    public CommandOutput executeCommand(TaskManager taskManger) {
        List<Task> completedTasks = taskManger.getAllCompletedTasks();
        String allCompletedTasksOutput = outputResult(completedTasks);
        return new CommandOutput(allCompletedTasksOutput, false);
    }

    private String outputResult(List<Task> completedTasks) {
        StringBuilder allCompletedTasksOutput = new StringBuilder();
        boolean hasCompletedTasks = completedTasks.size() > 0;
        if (hasCompletedTasks) {
            allCompletedTasksOutput.append("Here are all your completed tasks:\n");
            AtomicInteger listNumber = new AtomicInteger();
            for (Task completedTask : completedTasks) {
                String taskDescription = completedTask.toString();
                String taskDescriptionInListFormat = String.format("%d) %s\n", listNumber.get() + 1, taskDescription);
                allCompletedTasksOutput.append(taskDescriptionInListFormat);
                listNumber.getAndIncrement();
            }
        } else {
            allCompletedTasksOutput.append(Messages.NO_COMPLETED_TASKS_MESSAGE);
        }
        return allCompletedTasksOutput.toString();
    }
}
