package duke.task;

import duke.component.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected LocalDate date;
    protected LocalTime time;

    /**
     * constructor of Deadline task.
     * @param description string of description of task.
     * @param by date and time that task is to be completed by.
     * @throws DukeException exception thrown when exception caught while running.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        try {
            String[] dateTimeUnformattedArr = by.substring(1).split(" ");
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
        LocalDate dateFormatted = LocalDate.parse(dateUnformatted);
        assert dateFormatted instanceof LocalDate : "Return type of this method should be of type LocalDate";
        return dateFormatted;
    }

    /**
     * extracts time.
     * @param timeUnformatted string of unformatted time.
     * @return formatted Localtime from string of unformatted time.
     */
    private LocalTime extractTime(String timeUnformatted) {
        return LocalTime.parse(timeUnformatted);
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
        final String DEADLINE_STRING_SHOWED_TO_USER =
                "[D]" + super.toString() + this.stringOfTags + " " + "(by: " + this.date.format(dtfDate) + " " +
                        this.time.format(dtfTime) + ")";
        return DEADLINE_STRING_SHOWED_TO_USER;
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
        final String DEADLINE_STRING_TO_SAVE =
                "D " + "| " + status + " | " + this.description + this.stringOfTags + " " + "| " +
                        this.date.format(dtfDate) + " " + this.time.format(dtfTime);
        return DEADLINE_STRING_TO_SAVE;
    }

}