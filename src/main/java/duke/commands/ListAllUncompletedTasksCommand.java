package duke.commands;

import duke.task.Task;
import duke.task.TaskManager;

import duke.utils.Messages;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ListAllUncompletedTasksCommand extends Command {

    @Override
    public CommandOutput executeCommand(TaskManager taskManger) {
        List<Task> uncompletedTasks = taskManger.getAllUncompletedTasks();
        String allUncompletedTasksOutput = outputResult(uncompletedTasks);
        return new CommandOutput(allUncompletedTasksOutput, false);
    }

    private String outputResult (List<Task> uncompletedTasks) {
        StringBuilder allUncompletedTasksOutput = new StringBuilder();
        boolean hasUncompletedTasks = uncompletedTasks.size() > 0;
        if (hasUncompletedTasks) {
            allUncompletedTasksOutput.append("Here are all your uncompleted tasks:\n");
            AtomicInteger listNumber = new AtomicInteger();
            for (Task uncompletedTask : uncompletedTasks) {
                String taskDescription = uncompletedTask.toString();
                String taskDescriptionInListFormat = String.format("%d) %s\n", listNumber.get() + 1, taskDescription);
                allUncompletedTasksOutput.append(taskDescriptionInListFormat);
                listNumber.getAndIncrement();
            }
        } else {
            allUncompletedTasksOutput.append(Messages.NO_UNCOMPLETED_TASKS_MESSAGE);
        }
        return allUncompletedTasksOutput.toString();
    }
}
