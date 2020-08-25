package duke.task;

import duke.exception.EmptyTaskException;
import duke.exception.InvalidDateException;
import duke.exception.MissingDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final LocalDate date;

    private Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Creates a new Event object represented by the given command.
     * The command is split into two parts by the " /at " delimiter, with the
     * first being the task description and the second being the event date.
     *
     * @param task Description of task to be converted to a Event.
     * @return Event object representing the given description.
     * @throws EmptyTaskException If there is no text after "event ".
     * @throws MissingDateException If " /at " is not given or if there is no date provided.
     * @throws InvalidDateException If the date provided is in the wrong format.
     */
    public static Event create(String task)
            throws EmptyTaskException, MissingDateException, InvalidDateException {
        if (task.length() <= 6) throw new EmptyTaskException("event");

        String[] taskInfo = task.substring(6).split(" /at ", 2);
        if (taskInfo.length < 2) throw new MissingDateException();

        LocalDate dateTime = null;
        for (DateFormat format : DateFormat.values()) {
            try {
                dateTime = LocalDate.parse(taskInfo[1], format.toDateFormat());
            } catch (DateTimeParseException ignored) { }
        }
        if (dateTime == null) throw new InvalidDateException();

        return new Event(taskInfo[0], dateTime);
    }

    /**
     * Creates a new Event object represented by the given task details.
     *
     * @param task Description of task.
     * @param date Date on which the task occurs.
     * @return Event object representing the given details.
     */
    public static Event create(String task, String date) {
        DateTimeFormatter format = DateFormat.FORMAT6.toDateFormat();
        return new Event(task, LocalDate.parse(date, format));
    }

    @Override
    public String print() {
        return "E | "
                + (isDone ? 1 : 0) + " | "
                + this.description + " | "
                + this.date;
    }

    @Override
    public String toString() {
        String dateTime = date.format(DateFormat.FORMAT5.toDateFormat());
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Event) {
            return description.equals(((Event) obj).description)
                    && date.equals(((Event) obj).date);
        } else {
            return false;
        }
    }
}
