package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * TimedTask is a form of Task that contains an additional date parameter
 */
public class TimedTask extends Task {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");
    public static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");

    protected LocalDate date;

    TimedTask(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    TimedTask(String description, boolean isDone, LocalDate date) {
        super(description, isDone);
        this.date = date;
    }

    /**
     * Converts the TimedTask into standard form for ease of saving to data file.
     *
     * @param taskLetter taskLetter indicates the type of task.
     * @return Description of TimedTask in standard form.
     */
    @Override
    public String toText(String taskLetter) {
        String str = super.toText(taskLetter);
        str += "| " + this.date.format(DATE_FORMATTER);
        return str;
    }

    /**
     * Prints the TimedTask
     *
     * @param taskLetter taskLetter indicates the type of task.
     * @return Description of TimedTask.
     */
    public String toString(String taskLetter) {
        String dateFormatted = date.format(OUTPUT_DATE_FORMATTER);
        return String.format("[%s]%s (by: %s)", taskLetter, super.toString(), dateFormatted);
    }

}
