package duke.commands;

import static duke.utils.Messages.MESSAGE_TODAY;

import java.util.List;
import java.util.stream.Collectors;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.tasks.TimeBased;

/**
 * Represents the command that displays all tasks happening today to the user when executed.
 */
public class TodayCommand extends Command {

    /**
     * Returns a CommandResult containing all tasks happening today to the user.
     *
     * @param taskList The taskList involved.
     * @return The result of the command.
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        List<Task> tasksToday = getTasksToday(taskList);
        // todo: sort tasks
        String response = ListCommand.tasksToString(tasksToday, MESSAGE_TODAY);
        return new CommandResult(response, false);
    }

    private List<Task> getTasksToday(TaskList taskList) {
        List<Task> tasksToday = taskList.getTasks().stream()
                .filter(task -> task instanceof TimeBased)
                .map(task -> (TimeBased) task)
                .filter(task -> task.getTime() != null && task.getTime().isToday())
                .map(task -> (Task) task)
                .collect(Collectors.toList());

        return tasksToday;
    }

}
