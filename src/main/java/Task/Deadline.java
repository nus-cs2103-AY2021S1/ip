package main.java.Task;

import main.java.Task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents deadline which is a modified form of task.
 */
public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Constructs an deadline with the description of the deadline and the date of the deadline.
     * @param description description of the deadline.
     * @param date date of the deadline.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Constructs a deadline with the description of the deadline, date of the deadline and status of completion.
     * @param description of the deadline.
     * @param date date of the deadline.
     * @param isDone boolean value of whether it is completed.
     */
    public Deadline(String description, LocalDate date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    /**
     * Returns the type of task, which is deadline.
     * @return type of task, which is deadline.
     */
    @Override
    public String getTypeOfTask() {
        return "deadline";
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd E");
        String dateText = this.date.format(formatter);
        return "[D] [" + this.getStatusIcon() + "] " + this.description + " ----- By: " + dateText;
    }

    /**
     * Returns the representation of deadline to be stored in hard disk.
     * @return String representation of the deadline for storage.
     */
    @Override
    public String getStoreRepresentation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String dateText = this.date.format(formatter);
        String doneStatus = this.isDone ? "D," : "N,";
        return "D," + doneStatus + this.description + "," + dateText;

    }
}
