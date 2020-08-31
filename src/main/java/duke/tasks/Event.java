package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Type of task which includes date.
 */
public class Event extends Task {
    private String date;

    /**
     * Constructor to create Event object.
     *
     * @param description specific details of Event.
     * @param date when the Event is taking place.
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Gets the date of Event.
     *
     * @return date of the Event.
     */
    public String getDate() {
        return this.date;
    }

    private String convertDateAndTime() {
        String d1 = "";
        String[] descriptions = date.split(" ");
        for (int i = 0; i < descriptions.length; i++) {
            try {
                d1 += " " + LocalDate.parse(descriptions[i]).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } catch (DateTimeParseException e) {
                try {
                    String decideAMorPm = LocalTime.parse(descriptions[i]).isAfter(LocalTime.NOON) ? "pm" : "am";
                    d1 += " " + LocalTime.parse(descriptions[i])
                            .format(DateTimeFormatter.ofPattern("hh:mm")) + decideAMorPm;
                } catch (DateTimeParseException e2) {
                    d1 += " " + descriptions[i];
                }
            }
        }
        return d1;
    }

    /**
     * Checks if the date provided exists in the list.
     *
     * @param date date that is being searched for in the list.
     * @return true or false if date is in list or not.
     * @throws DukeException if description provided does not match format of date.
     */
    public boolean isSameDate(LocalDate date) throws DukeException {
        LocalDate d1 = date;
        LocalDate d2 = null;
        String[] descriptions = this.date.split(" ");
        for (int i = 0; i < descriptions.length; i++) {
            try {
                d2 = LocalDate.parse(descriptions[i]);
                return d2.equals(d1);
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return false;
    }

    /**
     * Checks if the time provided exists in the list.
     *
     * @param time time that is being searched for in the list.
     * @return true or false if time is in list or not.
     * @throws DukeException if description provided does not match format of time.
     */
    public boolean isSameTime(LocalTime time) throws DukeException {
        LocalTime d1 = time;
        LocalTime d2 = null;
        String[] descriptions = date.split(" ");
        for (int i = 0; i < descriptions.length; i++) {
            try {
                d2 = LocalTime.parse(descriptions[i]);
                if (d2.equals(d1)) {
                    return d2.equals(d1);
                } else {
                    continue;
                }
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return false;
    }

    /**
     * Prints out the task in correct format.
     *
     * @return task in the form of a string.
     */
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + "(at:" + convertDateAndTime() + ")";
    }
}
