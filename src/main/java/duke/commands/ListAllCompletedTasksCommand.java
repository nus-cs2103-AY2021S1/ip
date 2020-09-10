package duke.commands;

import duke.task.Task;
import duke.task.TaskManager;

import duke.utils.Messages;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ListAllCompletedTasksCommand extends Command {
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
