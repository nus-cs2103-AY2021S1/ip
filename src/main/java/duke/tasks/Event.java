package duke.tasks;


import java.time.LocalDate;


/**
 * Event class.
 * Subclass of Task.
 * Task that starts on a specific date.
 */
public class Event extends Task {

    private static final String DELIMITER = " /at ";

    private final LocalDate date;


    /**
     * Creates a new Event task.
     *
     * @param itemString Description string.
     */
    public Event(String itemString) {
        super(Task.getTaskString(itemString, Event.DELIMITER));
        this.setDateString(Task.getDateString(itemString, Event.DELIMITER));
        this.date = LocalDate.parse(this.getDateString());
    }


    /**
     * Constructor for event.
     *
     * @param itemString Description string.
     * @param isDone     True if task is done, false otherwise.
     */
    public Event(String itemString, boolean isDone) {
        super(Task.getTaskString(itemString, Event.DELIMITER), isDone);
        this.setDateString(Task.getDateString(itemString, Event.DELIMITER));
        this.date = LocalDate.parse(this.getDateString());
    }


    /**
     * Gets string array for storage.
     *
     * @return String array for storage.
     */
    @Override
    public String[] toStorageStringArr() {
        return new String[]{"E", this.isDone() ? "1" : "0", this.getItemString(), this.getDateString()};
    }


    @Override
    public String toString() {
        char stateSymbol = this.isDone() ? DONE : NOT_DONE;
        String dateString = Task.formatDateString(this.date);
        return String.format("[E][%s] %s (at: %s)", stateSymbol, this.getItemString(), dateString);
    }

}
