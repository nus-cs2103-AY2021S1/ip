package duke.task;

import duke.exception.MissingDeadlineException;
import duke.tool.Parser;

import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected String deadline;
    public static String SPLIT_IDENTIFIER = " /by ";

    public Deadline(String desc) throws MissingDeadlineException {
        super("D", desc.split(SPLIT_IDENTIFIER, 2)[0]);
        String[] temp = desc.split(SPLIT_IDENTIFIER, 2);
        if (temp.length == 1) {
            throw new MissingDeadlineException("deadline");
        } else {
            String date = desc.split(SPLIT_IDENTIFIER, 2)[1];
            String[] recurrence = Parser.parseDescription(date);
            this.deadline = containsTime(recurrence[0])
                                ? formatDateTime(recurrence[0])
                                : formatDate(recurrence[0]);
            if (recurrence.length != 1) {
                addRecurrence(recurrence[1], LocalDate.now());
            }
        }
    }

    public Deadline(String desc, String date) {
        super("D", desc);
        deadline = date;
    }


    @Override
    public String formatTaskForFile() {
        return taskType + " | " + (isDone ? "1" : "0") + " | " +
                description + " | " + deadline + (isRecurring() ? " | " + recurrence + " @ " + dateRepeated : "");
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + description + " (by: " + deadline + ")" +
                (isRecurring() ? " (repeats: every " + recurrence + ")" : "");
    }
}
