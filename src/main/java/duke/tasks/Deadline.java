package duke.tasks;


import java.time.LocalDate;


/**
 * Deadline class.
 */
public class Deadline extends Task {

    private static final String DELIMITER = " /by ";

    private final LocalDate date;


    /**
     * Constructor for deadline.
     *
     * @param itemString description string.
     */
    public Deadline(String itemString) {
        super(Task.getTaskString(itemString, Deadline.DELIMITER));
        this.dateString = Task.getDateString(itemString, Deadline.DELIMITER);
        this.date = LocalDate.parse(this.dateString);
    }


    /**
     * Constructor for deadline.
     *
     * @param itemString description string.
     * @param isDone     whether this task is done.
     */
    public Deadline(String itemString, boolean isDone) {
        super(Task.getTaskString(itemString, Deadline.DELIMITER), isDone);
        this.dateString = Task.getDateString(itemString, Deadline.DELIMITER);
        this.date = LocalDate.parse(this.dateString);
    }


    /**
     * Gets string array for storage.
     *
     * @return string array for storage.
     */
    @Override
    public String[] toStorageStringArr() {
        return new String[]{"D", this.isDone ? "1" : "0", this.itemString, this.dateString};
    }


    @Override
    public String toString() {
        char stateSymbol = this.isDone ? DONE : NOT_DONE;
        String dateString = Task.formatDateString(this.date);
        return String.format("[D][%s] %s (by: %s)", stateSymbol, this.itemString, dateString);
    }

}
