package duke.tasks;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;


/**
 * Deadline class.
 * Subclass of Task.
 * Task that needs to be done by a specific date.
 */
public class Deadline extends Task implements Comparable<Task> {

    private static final String DELIMITER = " /by ";

    private final LocalDate date;


    /**
     * Creates a new Deadline task.
     *
     * @param itemString Description string.
     */
    public Deadline(String itemString) throws DateTimeParseException {
        super(Task.parseTaskString(itemString, Deadline.DELIMITER));
        this.setDateString(Task.parseDateString(itemString, Deadline.DELIMITER));
        this.date = LocalDate.parse(this.getDateString());
    }


    /**
     * Constructor for deadline.
     *
     * @param itemString Description string.
     * @param isDone     True is task is done, false otherwise.
     */
    public Deadline(String itemString, boolean isDone) {
        super(Task.parseTaskString(itemString, Deadline.DELIMITER), isDone);
        this.setDateString(Task.parseDateString(itemString, Deadline.DELIMITER));
        this.date = LocalDate.parse(this.getDateString());
    }


    /**
     * Gets LocalDate object of task.
     *
     * @return LocalDate object of task.
     */
    public LocalDate getDate() {
        return this.date;
    }


    /**
     * Gets string array for storage.
     *
     * @return String array for storage.
     */
    @Override
    public String[] toStorageStringArr() {
        return new String[]{"D", this.isDone() ? "1" : "0", this.getItemString(), this.getDateString()};
    }


    @Override
    public String toString() {
        char stateSymbol = this.isDone() ? DONE : NOT_DONE;
        String dateString = Task.formatDateString(this.date);
        return String.format("[D][%s] %s (by: %s)", stateSymbol, this.getItemString(), dateString);
    }


    @Override
    public int compareTo(Task t) {
        if (t instanceof Todo) {
            return 1;
        } else {
            LocalDate thisDate = this.getDate();
            LocalDate otherDate = t.getDate();

            if (thisDate.isBefore(otherDate)) {
                return -1;
            } else if (thisDate.isAfter(otherDate)) {
                return 1;
            } else {
                return this.getItemString().compareTo(t.getItemString());
            }
        }
    }

}
