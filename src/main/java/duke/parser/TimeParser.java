package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a TimeParser and consists of methods related to
 * parsing the string representing the time of tasks.
 */
public class TimeParser {

    private LocalDate localDate;
    private String time;

    /**
     * Constructs a TimeParser object.
     *
     * @param localDate The localDate of the task.
     * @param time The time of the task,
     */
    public TimeParser(LocalDate localDate, String time) {
        this.localDate = localDate;
        this.time = time;
    }

    /**
     * Returns a string representing the formatted time.
     *
     * @return The formatted time.
     */
    public String getFormattedTime() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            String formattedTime = localDate.format(formatter);
            return formattedTime;
        } catch (Exception e) {
            return time;
        }

    }
}

