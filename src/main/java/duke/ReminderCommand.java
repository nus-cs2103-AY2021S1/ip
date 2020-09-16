package duke;

import java.time.LocalDate;

/**
 * Represents a command to remind the user of overdue tasks and current tasks.
 */
public class ReminderCommand extends Command {

    /**
     * Checks if the deadline task is overdue.
     *
     * @param deadline The deadline task.
     * @param today The time of the LocalDate object
     * @return a boolean object indicating true if not overdue and false otherwise
     */
    public boolean checkDeadlineOverdue(Deadline deadline, LocalDate today) {
        return (deadline.by.isAfter(today) || deadline.by.isEqual(today));
    }

    /**
     * Checks if the event task is overdue.
     *
     * @param event The event task.
     * @param today The time of the LocalDate object
     * @return a boolean object indicating true if not overdue and false otherwise
     */
    public boolean checkEventOverdue(Event event, LocalDate today) {
        return (event.at.isAfter(today) || event.at.isEqual(today));
    }
    
    @Override
    public String execute(TaskList taskList, Ui ui, Storage store) {
        LocalDate today = LocalDate.now();
        String overdue = "";
        String notOverdue = "";
        for (Task task : taskList.getList()) {
            if (task instanceof Deadline) {
                if (checkDeadlineOverdue((Deadline) task, today)) {
                    notOverdue += task.toString() + "\n";
                } else {
                    overdue += task.toString() + "\n";
                }
            }
            if (task instanceof Event) {
                if (checkEventOverdue((Event) task, today)) {
                    notOverdue += task.toString() + "\n";
                } else {
                    overdue += task.toString() + "\n";
                }
            }
            if (task instanceof ToDo) {
                notOverdue += task.toString() + "\n";
            }
        }
        return ui.showOverdue() + overdue + ui.showNotOverdue() + notOverdue;
    }
}
