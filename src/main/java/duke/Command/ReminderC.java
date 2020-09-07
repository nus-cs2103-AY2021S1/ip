package duke.Command;

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

    public boolean checkDeadline(Deadline d, LocalDate reminder, LocalDate today) {
        return d.by.isBefore(reminder) && ( d.by.isAfter(today) || d.by.isEqual(today));
    }

    public boolean checkEvent(Event e, LocalDate reminder, LocalDate today) {
        return e.at.isBefore(reminder) && ( e.at.isAfter(today) || e.at.isEqual(today));
    }

    @Override
    public String execute(Ui ui, TaskList todoList, Storage store) throws IOException {
        int range = Integer.parseInt(input.substring(9));
        LocalDate today = LocalDate.now();
        LocalDate by = today.plusDays(range);
        String result = "";
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
        return result;
    }
}
