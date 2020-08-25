package duke.tasks;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public String getDate() {
        return this.date;
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

    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + "(at:" + convertDate() + ")";
    }
}
