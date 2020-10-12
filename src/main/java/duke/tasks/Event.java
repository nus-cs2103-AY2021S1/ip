package duke.tasks;


import java.time.LocalDate;


/**
 * Event class.
 * Subclass of Task.
 * Task that starts on a specific date.
 */
public class Event extends Task implements Comparable<Task> {

    private static final String DELIMITER = " /at ";

    private final LocalDate date;


    /**
     * Creates a new Event task.
     *
     * @param itemString Description string.
     */
    public Event(String itemString) {
        super(Task.parseTaskString(itemString, Event.DELIMITER));
        this.setDateString(Task.parseDateString(itemString, Event.DELIMITER));
        this.date = LocalDate.parse(this.getDateString());
    }


    /**
     * Constructor for event.
     *
     * @param itemString Description string.
     * @param isDone     True if task is done, false otherwise.
     */
    public Event(String itemString, boolean isDone) {
        super(Task.parseTaskString(itemString, Event.DELIMITER), isDone);
        this.setDateString(Task.parseDateString(itemString, Event.DELIMITER));
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
        return new String[]{"E", this.isDone() ? "1" : "0", this.getItemString(), this.getDateString()};
    }


    @Override
    public String toString() {
        char stateSymbol = this.isDone() ? DONE : NOT_DONE;
        String dateString = Task.formatDateString(this.date);
        return String.format("[E][%s] %s (at: %s)", stateSymbol, this.getItemString(), dateString);
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
