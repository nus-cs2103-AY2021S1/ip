package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidEventException;
import duke.parser.DateTimeParsing;

public class Event extends Task {
    private final String time12h;
    private final LocalDate date;

    private Event(String description, String time12h, LocalDate date) {
        super(description);
        this.time12h = time12h;
        this.date = date;
    }

    /**
     * Factory method for creating an event task.
     *
     * @param details String details of the task.
     * @return Event the event task.
     * @throws InvalidEventException If the format of the details is invalid.
     */
    protected static Event createEvent(String details) throws InvalidEventException {
        String[] info = details.split("/");
        if (info.length == 1) {
            throw new InvalidEventException();
        }
        String desc = info[0];
        String[] dateTime = info[1].replaceFirst("at ", "").split(" ");
        try {
            LocalDate date = DateTimeParsing.parseDate(dateTime[0]);
            String time12h = DateTimeParsing.to12HTimeFormat(dateTime[1]);
            return new Event(desc, time12h, date);
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new InvalidEventException();
        }
    }

    @Override
    public boolean isDueOn(LocalDate date) {
        return this.date.equals(date);
    }

    @Override
    public String toSaveString() {
        String date = DateTimeParsing.localDateToString(this.date);
        String time = DateTimeParsing.to24HTimeFormat(time12h);
        return (isDone ? 1 : 0) + "event " + description + "/at " + date + " " + time;
    }

    @Override
    public String toString() {
        String formattedDate = DateTimeParsing.localDateToFormattedString(date);
        return "[E]" + super.toString() + "(at: " + formattedDate + " " + time12h + ")";
    }
}
