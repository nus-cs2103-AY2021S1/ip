package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    protected String time;
    protected LocalDate date;

    public EventTask(String description, String time) {
        super(description);
        this.time = time;
        this.date = null;
    }

    public EventTask(String description, String time, LocalDate date) {
        super(description);
        this.time = time;
        this.date = date;
    }


    public EventTask(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        if (time.contains(")")) {
            time = time.substring(0, time.indexOf(')'));
        }

        if (date == null) {
            assert date == null : "date should be null";
            return "[E]" + super.toString() + " (at: " + time + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter
                    .ofPattern("MMM d yyyy")) + ")";
        }
    }
}
