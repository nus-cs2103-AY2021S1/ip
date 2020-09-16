package Task;

import java.time.LocalDate;

/**
 * Represents a task.
 */
public class Task implements java.io.Serializable, Comparable<Task> {
    public String text;
    public boolean isDone;
    LocalDate date;

    /**
     * @param text Task.Task description.
     * @param date Date of the task.
     */
    public Task(String text, LocalDate date) {
        this.text = text;
        this.date = date;
    }

    /**
     * @return String representation of the task.
     */
    public String toString() {
        return "[" + (isDone ? "✓" : "✗") + "] " + text;
    }

    /**
     * Compares this task with the specified task for order based on date. The task with no date is considered greater.
     * Otherwise, the task with a later date is considered greater.
     *
     * @param t the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Task t) {
        if(this.date == null){
            return 1;
        } else if (t.date == null){
            return -1;
        } else {
            return this.date.compareTo(t.date);
        }
    }
}
