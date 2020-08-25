package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String type = "E";
    private String at;
    private LocalDate date;
    private String time = "";

    public Event(String isCompleted, String taskName, String at) {
        super(isCompleted, taskName);
        this.at = at;
        String dateAndTime[] = at.split("\\s");
        this.date = LocalDate.parse(dateAndTime[0]);
        if (dateAndTime.length > 1) {
            // If time is given as well
            this.time = dateAndTime[1].trim();
        }
    }

    @Override
    public String toString() {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (at: " + formattedDate + " " + this.time + ")";
    }
}
