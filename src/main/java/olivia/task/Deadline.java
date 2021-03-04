package olivia.task;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class that represents a Task with a deadline. Extends from the Task class.
 */

public class Deadline extends DatedTask {

    /**
     * Constructor that creates a Deadline object that has a description of the
     * task, the date and time of the deadline, and whether the task has been
     * completed.
     *
     * @param description a String representing the description of the task
     * @param deadline a LocalDateTime representing the date and time of the deadline
     * @param isDone a boolean representing whether the task has been completed
     * @throws DateTimeParseException exception thrown if input date was not
     *                                correctly formatted
     */

    public Deadline(String description, String deadline, boolean isDone)
            throws DateTimeParseException {
        super(description, "D", deadline, isDone);
    }

    /**
     * Returns a String formatted to how an Deadline should be saved in the associated
     * save file.
     *
     * @return a formatted String to be written to the save file
     */

    @Override
    public String toSave() {
        return String.format("%s | %s", super.toSave(),
                this.time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(),
                this.time.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }

}
