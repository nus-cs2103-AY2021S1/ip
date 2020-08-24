package task;

import exception.EmptyTaskException;
import exception.InvalidDateException;
import exception.MissingDateException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final LocalDate dueDate;

    private Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public static Deadline create(String task)
            throws EmptyTaskException, MissingDateException, InvalidDateException {
        if (task.length() <= 9) throw new EmptyTaskException("deadline");
        String[] taskInfo = task.substring(9).split(" /by ", 2);
        if (taskInfo.length < 2) throw new MissingDateException();
        LocalDate dateTime = null;
        for (DateTimeFormat format : DateTimeFormat.values()) {
            try {
                dateTime = LocalDate.parse(taskInfo[1], format.toDateFormat());
            } catch (DateTimeParseException ignored) { }
        }
        System.out.println(dateTime);
        if (dateTime == null) throw new InvalidDateException();
        return new Deadline(taskInfo[0], dateTime);
    }

    @Override
    public String toString() {
        String dateTime = dueDate.format(DateTimeFormat.FORMAT5.toDateFormat());
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }
}
