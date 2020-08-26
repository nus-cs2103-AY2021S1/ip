import java.time.LocalDate;


public class Event extends Task {

    private static final String DELIMITER = " /at ";

    private final LocalDate date;


    public Event(String itemString) {
        super(Task.getTaskString(itemString, Event.DELIMITER));
        this.date = LocalDate.parse(Task.getDateString(itemString, Event.DELIMITER));
    }


    public Event(String itemString, boolean isDone) {
        super(Task.getTaskString(itemString, Event.DELIMITER), isDone);
        this.date = Task.getDateString(itemString, Event.DELIMITER);
    }


    /**
     * Gets string array for storage.
     *
     * @return string array for storage.
     */
    @Override
    public String[] toStorageStringArr() {
        return new String[]{"E", this.isDone ? "1" : "0", this.itemString, this.date};
    }


    @Override
    public String toString() {
        char stateSymbol = this.isDone ? DONE : NOT_DONE;
        String dateString = Task.formatDateString(this.date);
        return String.format("[E][%s] %s (at: %s)", stateSymbol, this.itemString, dateString);
    }

}
