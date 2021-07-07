package duke.commands;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import duke.task.Task;
import duke.task.TaskManager;

import duke.utils.Messages;

/**
 * Represents the user command which will list out all the tasks which are uncompleted.
 */
public class ListAllUncompletedTasksCommand extends Command {

    /**
     * Finds all {@code Task} which is uncompleted and returns an output containing it.
     *
     * @param taskManger the {@code TaskManager} object that contains the list of {@code Task}s.
     * @return the output from executing the command.
     */
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
