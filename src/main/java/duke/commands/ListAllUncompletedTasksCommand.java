package duke.commands;

import duke.exceptions.DukeException;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskManager;

import java.util.List;

public class ListAllUncompletedTasksCommand extends Command {

    @Override
    public CommandOutput executeCommand(TaskManager taskManger, Storage storage) throws DukeException {
        List<Task> uncompletedTasks = taskManger.getAllUncompletedTasks();
        StringBuilder allUncompletedTasksOutput = new StringBuilder("Here are all your uncompleted tasks:\n");
        for (int i = 0; i < uncompletedTasks.size(); i++) {
            String taskDescriptionInListFormat = String.format("%d) %s\n", (i + 1), uncompletedTasks.get(i).toString());
            allUncompletedTasksOutput.append((taskDescriptionInListFormat));
        }
        return new CommandOutput(allUncompletedTasksOutput.toString(), false);
    }
}
