package main.java;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Encapsulates a task with a deadline.
 */

public class Deadline extends Task {
    /**The deadline of the task to be completed by. */
    LocalDateTime  deadline;
    /**The format of inputted dates that the class can accept. */
    static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    /**The format of outputted dates by the class. */
    static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    /**
     * Constructs a task that has not been completed
     * with a brief description and deadline for the task to be completed by.
     *
     * @param description a brief description of the deadline.
     * @param deadline a String in a specific format (inputFormatter) which specifies a date.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDateTime.parse(deadline, inputFormatter);
    }

    /**
     * Constructs a task, which may or may not have been completed,
     * with a brief description and deadline for the task to be completed by.
     *
     * @param isDone indicates if the deadline has been completed.
     * @param description a brief description of the deadline.
     * @param deadline a String in a specific format (inputFormatter) which specifies a date.
     */
    public Deadline(boolean isDone, String description, String deadline) {
        super(isDone, description);
        this.deadline = LocalDateTime.parse(deadline, inputFormatter);
    }

    /**
     * Returns a String representation of the deadline with the format of outputFormatter.
     *
     * @return a String representation of the deadline with the format of outputFormatter.
     */
    String getDeadline() {
        return this.deadline.format(outputFormatter).toString();
    }

    /**
     * Returns a String representation of the task.
     * This representation includes the status icon, description, and deadline in the format of outputFormatter.
     *
     * @return a String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription() + " (by: " + getDeadline() +")";
    }

    /**
     * Returns a boolean value indicating if the task is equal to
     * another object by determining if descriptions, deadline, and isDone parameters
     * are equal.
     *
     * @param o an object that is compared to the task to determine if both are equal
     * @return true or false if the task is equal or not equal to the object respectively.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Deadline) {
            Deadline task = (Deadline) o;
            return this.description.equals(task.description) && this.deadline.equals(task.deadline) && this.isDone == task.isDone;
        } else {
            return false;
        }
    }

    /**
     * Returns the string representation of the task in a format to be inputted into a text file for data storage.
     *
     * @return the string representation of the task to be saved in a text file.
     */
    @Override
    public String saveFormat() {
        if (isDone) {
            return "D | 1 | " + this.getDescription() + " | " + this.deadline.format(inputFormatter).toString();
        } else {
            return "D | 0 | " + this.getDescription() + " | " + this.deadline.format(inputFormatter).toString();
        }
    }
}
