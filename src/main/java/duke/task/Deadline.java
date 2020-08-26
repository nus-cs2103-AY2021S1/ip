package duke.task;

import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidTodoException;
import duke.util.DateTimeParsing;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class Deadline extends Task {
    private final String TIME12H;
    private final LocalDate DATE;

    private Deadline(String description, String time12h, LocalDate date) {
        super(description);
        this.TIME12H = time12h;
        this.DATE = date;
    }

    /**
     * Factory method for creating a deadline task.
     *
     * @param details String details of the task
     * @return Deadline the deadline task
     * @throws InvalidDeadlineException If the format of the details is invalid
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
        } catch(DateTimeParseException | NumberFormatException e) {
            throw new InvalidDeadlineException();
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
        return (isDone ? 1 : 0) + "deadline " + description + "/by " + date + " " + time;
    }

    @Override
    public String toString() {
        String formattedDate = DateTimeParsing.localDateToFormattedString(DATE);
        return "[D]" + super.toString() + "(by: " + formattedDate + " " + TIME12H + ")";
    }
}
