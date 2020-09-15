package duke.task;

import duke.DateTime;

/**
 * Represents a Deadline task that contains both task name and a date to serve as a deadline.
 * Time can be added as an optional parameter.
 */
public class Deadline extends Task {


    /**
     * Creates a Deadline task containing the description, date and time of the task.
     *
     * @param name Description of the deadline task.
     * @param dateTime Stores the date and any specified time of the task deadline.
     */
    public Deadline(String name, DateTime dateTime) {
        super(name, dateTime);
    }

    /**
     * Returns the string representation of a Deadline task to be appended to a local file.
     *
     * @return String representation of a Deadline task with description, done status, date and/or time.
     */
    public String appendFile() {
        String doneString = (isDone() ? "1" : "0");
        return "deadline" + " | " + doneString + " | " + getName() + " | " + getDateTime().getFileFormattedDateTime();
    }

    /**
     * Returns the string representation of a Deadline task.
     *
     * @return String representation of a Deadline task containing the description, done status, formatted date and/or
     * time.
     */
    @Override
    public String toString() {
        String doneString = (isDone() ? "✓" : "✗");
        return "[D]" + "[" + doneString + "] " + getName() + " (by: " + getDateTime().getPrintFormattedDate()
                + " " + getDateTime().getPrintFormattedTime() + ")";
    }
}

