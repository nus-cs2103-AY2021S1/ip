package duke.task;

import duke.exception.MissingDeadlineException;
import duke.tool.Parser;

import java.time.LocalDate;

/**
 * Represents a task that is occurring at a specific time.
 */
public class Event extends Task {
    protected String time;
    public static String SPLIT_IDENTIFIER = " /at ";

    public Event(String desc) throws MissingDeadlineException {
        super("E", desc.split(SPLIT_IDENTIFIER, 2)[0]);
        String[] temp = desc.split(SPLIT_IDENTIFIER, 2);
        if (temp.length == 1) {
            throw new MissingDeadlineException("event");
        } else {
            String date = desc.split(SPLIT_IDENTIFIER, 2)[1];
            String[] recurrence = Parser.parseDescription(date);
            time = containsTime(recurrence[0])
                            ? formatDateTime(recurrence[0])
                            : formatDate(recurrence[0]);
            if (recurrence.length != 1) {
                addRecurrence(recurrence[1], LocalDate.now());
            }
        }
    }

    public Event(String desc, String time) {
        super("E", desc);
        this.time = time;
    }

    @Override
    public String formatTaskForFile() {
        return taskType + " | " + (isDone ? "1" : "0") + " | " +
                description + " | " + time + (isRecurring() ? " | " + recurrence + " @ " + dateRepeated: "");
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + description + " (at: " + time + ")" +
                (isRecurring() ? " (repeats: every " + recurrence + ")" : "");
    }
}
