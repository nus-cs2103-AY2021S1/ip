package duke.task;

import duke.DateTime;

/**
 * Represents an Event task that contains both task name and a date to serve as the event date.
 * Time can be added as an optional parameter.
 */
public class Event extends Task {

    /**
     * Creates an Event task containing the description, date and time of the task.
     *
     * @param name Description of the event task.
     * @param dateTime Stores the date and any specified time of the task deadline.
     */
    public Event(String name, DateTime dateTime) {
        super(name, dateTime);
    }

    /**
     * Returns the string representation of an Event task to be appended to a local file.
     *
     * @return String representation of an Event task with description, done status, date and/or time.
     */
    public String appendFile() {
        String doneString = (isDone() ? "1" : "0");
        return "event" + " | " + doneString + " | " + getName() + " | " + getDateTime().getFileFormattedDateTime();
    }

    /**
     * Returns the string representation of an Event task.
     *
     * @return String representation of an Event task containing the description, done status, formatted date and/or
     * time.
     */
    @Override
    public String toString() {
        String doneString = (isDone() ? "✓" : "✗");
        return "[E]" + "[" + doneString + "] " + getName() + " (at: " + getDateTime().getPrintFormattedDate()
                + " " + getDateTime().getPrintFormattedTime() + ")";
    }
}
