package duke.task;

import duke.component.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;
    protected LocalDate date;
    protected LocalTime time;

    public Event(String description, String at) throws DukeException {

        super(description);
        this.at = at;
        try {
            String[] dateTimeUnformattedArr = at.substring(1).split(" ");
            String dateUnformatted = dateTimeUnformattedArr[0];
            String timeUnformatted = dateTimeUnformattedArr[1];
            this.date = extractDate(dateUnformatted);
            this.time = extractTime(timeUnformatted);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | DateTimeException a) {
            throw new DukeException("Date / time not formatted correctly, please follow format: yyyy/mm/dd hh:mm");
        }
    }

    private LocalDate extractDate(String dateUnformatted) {
        dateUnformatted = dateUnformatted.replaceAll("/", "-");
        return LocalDate.parse(dateUnformatted);
    }

    private LocalTime extractTime(String timeUnformatted) {
        return LocalTime.parse(timeUnformatted);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter dtfTime = DateTimeFormatter.ISO_LOCAL_TIME;
        return "[E]" + super.toString() + "(at: " + this.date.format(dtfDate) + " " + this.time.format(dtfTime) + ")";
    }

    @Override
    public String stringToSave() {
        char status = this.isDone ? '1' : '0';
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter dtfTime = DateTimeFormatter.ISO_LOCAL_TIME;
        return "E " + "| " + status + " | " + this.description + "| " + this.date.format(dtfDate) + " " + this.time.format(dtfTime);
    }

}