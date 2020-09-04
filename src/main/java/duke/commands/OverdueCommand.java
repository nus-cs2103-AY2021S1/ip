package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskManager;

import java.time.LocalDate;
import java.util.List;

public class OverdueCommand extends Command {
    private static final LocalDate TODAY = LocalDate.now();
    @Override
    public CommandOutput executeCommand(TaskManager taskManger, Storage storage) throws DukeException {
        List<Task> overdueTasks = taskManger.getAllTasksBeforeQueryDate(TODAY);
        StringBuilder allOverdueTasks = new StringBuilder("Here are yopur overdue tasks:\n");
        for (int i = 0; i < overdueTasks.size(); i++) {
            String taskDescriptionInListFormat = String.format("%d) %s\n", (i + 1), overdueTasks.get(i));
            allOverdueTasks.append(taskDescriptionInListFormat);
        }
        return new CommandOutput(allOverdueTasks.toString(), false);
    }
}
