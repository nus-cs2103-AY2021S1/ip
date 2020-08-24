package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime date;


    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    private String formatDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
    }

    @Override
    public String toString() {
        return "["+ getTaskType() +"]" +"["+ getStatusIcon()+ "]" + description + " " + "(by: " + formatDate() + ")";
    }
}
