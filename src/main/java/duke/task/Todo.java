package duke.task;

import duke.tool.Parser;

import java.time.LocalDate;

/**
 * Represents a task to be done without a deadline.
 */
public class Todo extends Task {

    public Todo(String desc) {
        super("T", Parser.parseDescription(desc)[0]);
        String[] recurrence = Parser.parseDescription(desc);
        if (recurrence.length != 1) {
            addRecurrence(recurrence[1], LocalDate.now());
        }
    }

    @Override
    public String formatTaskForFile() {
        return taskType + " | " + (isDone ? "1" : "0")
                    + " | " + description + (isRecurring() ? " | " + recurrence + " @ " + dateRepeated: "");
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + description +
                (isRecurring() ? " (repeats: every " + recurrence + ")" : "");
    }
}
