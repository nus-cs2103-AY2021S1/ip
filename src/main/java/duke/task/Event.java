package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exception.InvalidDateException;
import duke.exception.MissingDateException;
import duke.exception.UnreadableSaveTaskException;

/**
 * Class representing an event task.
 * It consists of a description containing information on the task
 * and a date representing when the event is to occur.
 */
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
     * @throws MissingDateException If " /at " is not given or if there is no date provided.
     * @throws InvalidDateException If the date provided is in the wrong format.
     */
    public static Event create(String task)
            throws MissingDateException, InvalidDateException {
        String[] taskInfo = task.split(" /at ", 2);
        if (taskInfo.length < 2) {
            throw new MissingDateException();
        }

        LocalDate date = DateFormat.getLocalDate(taskInfo[1]);
        return new Event(taskInfo[0], date);
    }

    /**
     * Creates a new Event object represented by its String when read from a file.
     * The read string, when split with the '/' regex, must produce an array of length
     * 4 in the form <code>[E, {isDone indicator}, {description}, {event date}]</code>.
     *
     * @param data Description of task.
     * @return Event object representing the given details.
     * @throws UnreadableSaveTaskException If data does not have length 4.
     */
    public static Event createFromFile(String[] data)
            throws UnreadableSaveTaskException {
        if (data.length != 4) {
            throw new UnreadableSaveTaskException();
        }

        DateTimeFormatter format = DateFormat.FORMAT6.toDateFormat();
        return new Event(data[2], LocalDate.parse(data[3], format));
    }

    @Override
    public String toDataString() {
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
