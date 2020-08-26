import java.time.LocalDate;


public class Event extends Task {

    private static final String DELIMITER = " /at ";

    private final LocalDate date;


    public Event(String itemString) {
        super(Task.getTaskString(itemString, Event.DELIMITER));
        this.date = LocalDate.parse(Task.getDateString(itemString, Event.DELIMITER));
    }


    @Override
    public String toString() {
        char stateSymbol = this.isDone ? DONE : NOT_DONE;
        String dateString = Task.formatDateString(this.date);
        return String.format("[D][%s] %s (at: %s)", stateSymbol, this.itemString, dateString);
    }

}
