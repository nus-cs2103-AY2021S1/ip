package duke.commands;

import java.time.LocalDate;

import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Responsible for the logic of listing all upcoming
 * events and deadlines.
 */
public class ReminderCommand extends Command {
    public static final String COMMAND_WORD = "reminder";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays upcoming events and deadlines within the next 7 days.\n"
            + "Example: " + COMMAND_WORD + "\n";
    public static final int REMINDER_THRESHOLD = 7;

    /**
     * Collates all events and deadlines in the task list that are
     * happening or due in the next 7 days.
     * @param tasks List of tasks.
     * @param storage Saves tasks in text file.
     * @return CommandResult containing events and deadlines in the
     * next 7 days.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        LocalDate currentDate = LocalDate.now();
        String upcomingEvents = "Upcoming Events\n";
        String upcomingDeadlines = "Upcoming Deadlines\n";
        for (Task task : tasks.getTaskList()) {
            if (task instanceof Event) {
                int withinThreshold = currentDate.plusDays(REMINDER_THRESHOLD)
                        .compareTo(((Event) task).getDate());
                if (withinThreshold >= 0) {
                    upcomingEvents += task + "\n";
                }
            }

            if (task instanceof Deadline) {
                int withinThreshold = currentDate.plusDays(REMINDER_THRESHOLD)
                        .compareTo(((Deadline) task).getDueDate());
                if (withinThreshold >= 0) {
                    upcomingDeadlines += task + "\n";
                }
            }
        }
        return new CommandResult(upcomingEvents
            + "\n" + Ui.DIVIDER + "\n" + upcomingDeadlines);
    }
}
