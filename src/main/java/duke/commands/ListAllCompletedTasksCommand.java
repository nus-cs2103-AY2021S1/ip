package duke.commands;

import duke.exceptions.DukeException;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskManager;

import java.util.List;

public class ListAllCompletedTasksCommand extends Command {
    @Override
    public CommandOutput executeCommand(TaskManager taskManger, Storage storage) throws DukeException {
        List<Task> completedTasks = taskManger.getAllCompletedTasks();
        StringBuilder allCompletedTasksOutput = new StringBuilder("Here are all your completed tasks:\n");
        for (int i = 0; i < completedTasks.size(); i++) {
            String taskDescriptionInListFormat = String.format("%d) %s\n", (i + 1), completedTasks.get(i));
            allCompletedTasksOutput.append(taskDescriptionInListFormat);
        }
        return new CommandOutput(allCompletedTasksOutput.toString(), false);
    }
}
