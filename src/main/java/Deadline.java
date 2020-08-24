import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task{
    String date;

    Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }
    private String convertDate() {
        String d1 = "";
        String[] descriptions = this.date.split(" ");
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
        return "[D][" + getStatusIcon() + "] " + this.description + "(by:" + convertDate() + ")";
    }
}


