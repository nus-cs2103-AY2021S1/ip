package duke.commands;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskManager;

import java.time.LocalDate;

import java.util.List;

public class TodayTasksCommand extends Command {
    private static final LocalDate TODAY = LocalDate.now();

    @Override
    public CommandOutput executeCommand(TaskManager taskManger, Storage storage) {
        List<Task> tasksForToday = taskManger.findTasksByDate(TODAY);
        StringBuilder output = new StringBuilder();
        output.append("Here are your tasks for today:\n");
        for (int i = 0; i < tasksForToday.size(); i++) {
            String taskDescription = String.format("%d) %s\n", (i + 1), tasksForToday.get(i).toString());
            output.append(taskDescription);
        }

        return new CommandOutput(output.toString(), false);
    }
}
