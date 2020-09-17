package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimedTask extends Task {

    public static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");
    public static DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");

    protected LocalDate date;

    TimedTask(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    TimedTask(String description, boolean isDone, LocalDate date) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toText(String taskLetter) {
        String str = super.toText(taskLetter);
        str += "| " + this.date.format(DATE_FORMATTER);
        return str;
    }

    public String toString(String taskLetter) {
        String dateFormatted = date.format(OUTPUT_DATE_FORMATTER);
        return String.format("[%s]%s (by: %s)", taskLetter, super.toString(), dateFormatted);
    }

}
