package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimedTask extends Task {
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
        str += "| " + this.date.format(Common.DATE_FORMATTER);
        return str;
    }

    public String toString(String taskLetter) {
        String dateOutput = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[%s]%s (by: %t", taskLetter, super.toString(), dateOutput);
    }

}
