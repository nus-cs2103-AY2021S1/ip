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

    /**
     * constructor of Event task.
     * @param description string of description of task.
     * @param at date and time event takes place.
     * @throws DukeException exception thrown when exception caught while running.
     */
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

    /**
     * extracts date.
     * @param dateUnformatted string of unformatted date.
     * @return formatted Localdate from string of unformatted date.
     */
    private LocalDate extractDate(String dateUnformatted) {
        dateUnformatted = dateUnformatted.replaceAll("/", "-");
        LocalDate formattedDate = LocalDate.parse(dateUnformatted);
        assert formattedDate instanceof LocalDate : "return value of this method has to be of type LocalDate";
        return formattedDate;
    }

    /**
     * extracts time.
     * @param timeUnformatted string of unformatted time.
     * @return formatted Localtime from string of unformatted time.
     */
    private LocalTime extractTime(String timeUnformatted) {
        LocalTime formattedTime = LocalTime.parse(timeUnformatted);
        assert formattedTime instanceof LocalTime : "return value of this method has to be of type LocalTime";
        return formattedTime;
    }

    /**
     * returns string of task to be printed out and shown to user.
     * @return string of task.
     */
    @Override
    public String toString() {
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter dtfTime = DateTimeFormatter.ISO_LOCAL_TIME;
        assert dtfDate instanceof DateTimeFormatter : "date formatter has to be of type DateTimeFormatter";
        assert dtfTime instanceof DateTimeFormatter : "time formatter has to be of type DateTimeFormatter";
        final String EVENT_STRING_SHOWED_TO_USER =
                "[E]" + super.toString() + this.stringOfTags + " " + "(at: " + this.date.format(dtfDate) +
                " " + this.time.format(dtfTime) + ")";
        return EVENT_STRING_SHOWED_TO_USER;
    }

    /**
     * returns string of task to be saved in storage.
     * @return string of task.
     */
    @Override
    public String stringToSave() {
        char status = this.isDone ? '1' : '0';
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter dtfTime = DateTimeFormatter.ISO_LOCAL_TIME;
        assert dtfDate instanceof DateTimeFormatter : "date formatter has to be of type DateTimeFormatter";
        assert dtfTime instanceof DateTimeFormatter : "time formatter has to be of type DateTimeFormatter";
        final String EVENT_STRING_TO_SAVE =
                "E " + "| " + status + " | " + this.description+ this.stringOfTags + " " + "| " + this.date.format(dtfDate) + " " +
                        this.time.format(dtfTime);
        return EVENT_STRING_TO_SAVE;
    }

}