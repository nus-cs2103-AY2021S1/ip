package commands;

import java.time.LocalDate;

import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ReminderCommand extends Command {
    public static final String COMMAND_WORD = "reminder";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays upcoming events and deadlines within the next 7 days.\n"
            + "Example: " + COMMAND_WORD + "\n";
    public static final int reminderThreshold = 7;

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        LocalDate currentDate = LocalDate.now();
        String upcomingEvents = "Upcoming Events\n";
        String upcomingDeadlines = "Upcoming Deadlines\n";
        for (Task task : tasks.getTaskList()) {
            if (task instanceof Event) {
                int withinThreshold = currentDate.plusDays(reminderThreshold).compareTo(((Event) task).getDate());
                if (withinThreshold >= 0) {
                    upcomingEvents += task + "\n";
                }
            }

            if (task instanceof Deadline) {
                int withinThreshold = currentDate.plusDays(reminderThreshold).compareTo(((Deadline) task).getDueDate());
                if (withinThreshold >= 0) {
                    upcomingDeadlines += task + "\n";
                }
            }
        }
        return new CommandResult(upcomingEvents
            + "\n" + Ui.DIVIDER + "\n" + upcomingDeadlines);
    }
}
