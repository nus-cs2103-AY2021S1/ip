package duke.commands;

import static duke.utils.Messages.MESSAGE_TODAY;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.utils.DukeDateTime;

/** Represents the command that displays all tasks happening today to the user when executed. */
public class TodayCommand extends Command {

    /** Returns a CommandResult containing all tasks happening today to the user.
     *
     * @param taskList The taskList involved.
     * @return The result of the command.
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        LocalDateTime now = LocalDate.now().atStartOfDay();
        ArrayList<Task> tasksToday = new ArrayList<>();
        taskList.getTasks().forEach(task -> {
            if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getAt().isSameDate(new DukeDateTime(now, false))) {
                    tasksToday.add(event);
                }
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getBy().isSameDate(new DukeDateTime(now, false))) {
                    tasksToday.add(deadline);
                }
            }
        });
        // todo: sort tasks
        String response = tasksTodayString(tasksToday);
        return new CommandResult(response, false);
    }

    private String tasksTodayString(ArrayList<Task> tasks) {
        String response = MESSAGE_TODAY;
        return ListCommand.tasksToString(tasks, response);
    }
}
