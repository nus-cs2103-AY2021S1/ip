package duke.task;

import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.util.DateTimeParsing;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class Event extends Task {
    private final String TIME12H;
    private final LocalDate DATE;

    private Event(String description, String TIME12H, LocalDate DATE) {
        super(description);
        this.TIME12H = TIME12H;
        this.DATE = DATE;
    }

    /**
     * Factory method for creating an event task.
     *
     * @param details String details of the task
     * @return Event the event task
     * @throws InvalidEventException If the format of the details is invalid
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
        } catch(DateTimeParseException | NumberFormatException e) {
            throw new InvalidEventException();
        }
    }

    @Override
    public boolean isDueOn(LocalDate date) {
        return this.DATE.equals(date);
    }

    @Override
    public String toSaveString() {
        String date = DateTimeParsing.localDateToString(DATE);
        String time = DateTimeParsing.to24HTimeFormat(TIME12H);
        return (isDone ? 1 : 0) + "event " + description + "/at " + date + " " + time;
    }

    @Override
    public String toString() {
        String formattedDate = DateTimeParsing.localDateToFormattedString(DATE);
        return "[E]" + super.toString() + "(at: " + formattedDate + " " + TIME12H + ")";
    }
}
