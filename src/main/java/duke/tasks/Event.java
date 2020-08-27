package duke.tasks;


import java.time.LocalDate;


/**
 * Event class.
 */
public class Event extends Task {

    private static final String DELIMITER = " /at ";

    private final LocalDate date;


    /**
     * Constructor for event.
     *
     * @param itemString description string.
     */
    public Event(String itemString) {
        super(Task.getTaskString(itemString, Event.DELIMITER));
        this.dateString = Task.getDateString(itemString, Event.DELIMITER);
        this.date = LocalDate.parse(this.dateString);
    }


    /**
     * Constructor for event.
     *
     * @param itemString description string.
     * @param isDone     whether this task is done.
     */
    public Event(String itemString, boolean isDone) {
        super(Task.getTaskString(itemString, Event.DELIMITER), isDone);
        this.dateString = Task.getDateString(itemString, Event.DELIMITER);
        this.date = LocalDate.parse(this.dateString);
    }


    /**
     * Gets string array for storage.
     *
     * @return string array for storage.
     */
    @Override
    public String[] toStorageStringArr() {
        return new String[]{"E", this.isDone ? "1" : "0", this.itemString, this.dateString};
    }


    @Override
    public String toString() {
        char stateSymbol = this.isDone ? DONE : NOT_DONE;
        String dateString = Task.formatDateString(this.date);
        return String.format("[E][%s] %s (at: %s)", stateSymbol, this.itemString, dateString);
    }

}
