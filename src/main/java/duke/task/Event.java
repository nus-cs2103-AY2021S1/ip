package duke.task;

import duke.dukeException.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String at;
    protected LocalDate date;
    protected LocalTime time;
    protected DateTimeFormatter dateParser = DateTimeFormatter.ofPattern("dd/MM/yy");
    protected DateTimeFormatter timeParser = DateTimeFormatter.ofPattern("HH:mm");

    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;
        try {
            String s[] = at.split(" ");
            if (s.length <= 1) {
                time = null;
            } else {
                time = LocalTime.parse(s[1], timeParser);
            }
            date = LocalDate.parse(s[0], dateParser);
        } catch (DateTimeParseException e) {
            throw new DukeException("Yo! DateTime format is wrong. <dd/MM/yy [HH:MM]>");
        }
    }

    @Override
    public String toString() {
        if (time == null) {
            return "[E]" + super.toString() + " (at: " + dateParser.format(date) + ")";
        } else {
            return "[E]"
                    + super.toString()
                    + " (at: "
                    + dateParser.format(date)
                    + " "
                    + timeParser.format(time)
                    + ")";
        }
    }

    @Override
    public String toFile() {
        return "E | " + getStatusCode() + " | " + description + " | " + at;
    }
}
