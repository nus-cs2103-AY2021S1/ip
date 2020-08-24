package main.java;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;

    public Deadline(String name, String eventDetail) throws DateTimeParseException {
        super(name);
        String[] input = eventDetail.split("\\s+");
        try {
            date = LocalDate.parse(input[0]);
            if (input.length == 2) {
                time = LocalTime.parse(input[1]);
            } else{
                time = null;
            }
        } catch (DateTimeParseException err) {
            throw err;
        }
    }

    @Override
    public String toSaveFormat() {
        return String.format("D%s| %s %s", super.toSaveFormat(),
                Task.SAVE_DATE_FORMATTER.format(date), Task.TIME_FORMATTER.format(time));
    }

    @Override
    public String toString() {
        String dateTime = Task.DATE_FORMATTER.format(this.date);
        if (time != null) {
            dateTime += " " + Task.TIME_FORMATTER.format(this.time);
        }
        return String.format("[D]%s (by: %s)", super.toString(), dateTime);
    }
}
