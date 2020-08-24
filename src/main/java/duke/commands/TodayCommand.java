package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.ui.Ui;
import duke.utils.DukeDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/** Represents the command that displays all tasks happening today to the user when executed. */
public class TodayCommand extends Command {

    /** Displays all tasks happening today to the user.
     *
     * @param taskList The taskList involved.
     * @param ui The ui involved to show messages to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
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
        ui.show(tasksTodayString(tasksToday));
    }

    private String tasksTodayString(ArrayList<Task> tasks) {
        String response = "\t Here are your tasks today:\n";
        return ListCommand.tasksToString(tasks, response);
    }
}
