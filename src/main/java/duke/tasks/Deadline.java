package duke.tasks;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Type of task which includes end date.
 */
public class Deadline extends Task {
    String date;

    /**
     * Constructor to create Deadline object.
     *
     * @param description description of the task.
     * @param date date of deadline.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Gets the date of deadline.
     *
     * @return date of the deadline.
     */
    public String getDate() {
        return date;
    }

    private String convertDate() {
        String d1 = "";
        String[] descriptions = date.split(" ");
        for (int i = 0; i < descriptions.length; i++) {
            try {
                d1 += " " + LocalDate.parse(descriptions[i]).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } catch (DateTimeParseException e) {
                d1 += " " + descriptions[i];
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
    public boolean hasDate(String date) throws DukeException {
        try {
            LocalDate d1 = LocalDate.parse(date);
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
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date.");
        }
    }

    /**
     * Prints out the task in correct format.
     *
     * @return task in the form of a string.
     */
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + "(by:" + convertDate() + ")";
    }
}


