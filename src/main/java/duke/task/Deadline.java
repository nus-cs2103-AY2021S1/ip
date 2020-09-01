package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDeadlineException;
import duke.parser.DateTimeParsing;

public class Deadline extends Task {
    private final String time12h;
    private final LocalDate date;

    private Deadline(String description, String time12h, LocalDate date) {
        super(description);
        this.time12h = time12h;
        this.date = date;
    }

    /**
     * Factory method for creating a deadline task.
     *
     * @param details String details of the task.
     * @return Deadline the deadline task.
     * @throws InvalidDeadlineException If the format of the details is invalid.
     */
    protected static Deadline createDeadline(String details) throws InvalidDeadlineException {
        String[] info = details.split("/");
        if (info.length == 1) {
            throw new InvalidDeadlineException();
        }
        String desc = info[0];
        String[] dateTime = info[1].replaceFirst("by ", "").split(" ");
        try {
            LocalDate date = DateTimeParsing.parseDate(dateTime[0]);
            String time12h = DateTimeParsing.to12HTimeFormat(dateTime[1]);
            return new Deadline(desc, time12h, date);
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new InvalidDeadlineException();
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
        return (isDone ? 1 : 0) + "deadline " + description + "/by " + date + " " + time;
    }

    @Override
    public String toString() {
        String formattedDate = DateTimeParsing.localDateToFormattedString(date);
        return "[D]" + super.toString() + "(by: " + formattedDate + " " + time12h + ")";
    }
}
