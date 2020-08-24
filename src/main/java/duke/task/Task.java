package duke.task;

import duke.io.Parser;

import java.util.ArrayList;

/**
 * Represents a task. 
 */
public class Task {
    private boolean done;
    protected String description;

    public Task(String task) {
        this.description = task;
        done = false;
    }

    /**
     * Label task as completed or done.
     */
    public void markDone() {
        done = true;
    }

    /**
     * Return a tick or cross unicode depending on the done status of a task.
     * 
     * @return String of unicode symbol.
     */
    private String getStatusSymbol() {
        return done ? "[\u2713]" : "[\u2718]";
    }

    /**
     * Return 0 or 1 depending on the done status of a task.
     * For storage purposes.
     * 
     * @return 0 or 1.
     */
    protected int getDoneInteger() {
        return done ? 0 : 1;
    }

    public String toString() {
        return getStatusSymbol() + " " + description;
    }

    public String toSave() {
        return getDoneInteger() + " | " + description;
    }

    /**
     * Return specified deadline or event time in a different format 
     * if valid date and time are given.
     * For ease of readability.
     * 
     * @param date Date to format.
     * @return ArrayList of LocalDate object and String that represents 12 hour time.
     */
    protected ArrayList<Object> dateAndTimeFormatter(String date) {
        Parser parser = new Parser();
        return parser.dateAndTimeFormatter(date);
    }
    
}
