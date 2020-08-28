package dobby.task;

import java.time.LocalDate;

/**
 * Class for deadline tasks
 */
public class Deadline extends TimedTask {
    private static final String TAG = "[D]";

    public Deadline(String description, String by) {
        super(description, by, TAG);
    }

    public Deadline(String description, String time, LocalDate date) {
        super(description, time, date, TAG);
    }
}