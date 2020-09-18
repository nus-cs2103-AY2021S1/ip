package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String identity;
    private LocalDate date;
    String[] desArray, timeArray;

    public Deadline(String description) {
        super(description);
        this.identity = "D";
        desArray = this.description.split("/", 2);
        timeArray = desArray[1].split(" ", 2);
        this.date = LocalDate.parse(timeArray[1]);
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription() + "("
                + timeArray[0] + ": " + getDate() + ")";
    }
}