package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.io.IOException;
import java.time.LocalDate;

public class ReminderC extends Command {
    private final String input;

    public ReminderC(String input) {
        this.input = input;
    }

    /**
     * checks if the deadline is within the date range.
     * @param d
     * @param reminder
     * @param today
     * @return
     */
    public boolean checkDeadline(Deadline d, LocalDate reminder, LocalDate today) {
        return d.by.isBefore(reminder) && ( d.by.isAfter(today) || d.by.isEqual(today));
    }

    /**
     * checks if the event is within the date range.
     * @param e
     * @param reminder
     * @param today
     * @return
     */
    public boolean checkEvent(Event e, LocalDate reminder, LocalDate today) {
        return e.at.isBefore(reminder) && ( e.at.isAfter(today) || e.at.isEqual(today));
    }

    /**
     * Takes the range from the input and returns all events within that range as a reminder.
     * @param ui
     * @param todoList
     * @param store
     * @return
     * @throws IOException
     * @throws DukeException
     */
    @Override
    public String execute(Ui ui, TaskList todoList, Storage store) throws IOException, DukeException {
        String result = "";
        try {
            int range = Integer.parseInt(input.substring(9));
            LocalDate today = LocalDate.now();
            LocalDate by = today.plusDays(range);

            result += "In " + range + " days, you have:\n";
            int reminderCount = 0;
            for (Task task : todoList.todoList) {
                if (task instanceof Deadline) {
                    if (checkDeadline((Deadline) task, by, today)) {
                        reminderCount++;
                        result += reminderCount + ". " + task.toString() + "\n";
                    }
                }
                if (task instanceof Event) {
                    if (checkEvent((Event) task, by, today)) {
                        reminderCount++;
                        result += reminderCount + ". " + task.toString() + "\n";
                    }
                }
            }
            if (reminderCount > 0) {
                result += "   [ A total of " + reminderCount + " reminder(s)]";
            } else {
                result += "      [ You have no events or deadlines by this day ]";
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("You didn't enter a range!");
        }

        return result;
    }
}
