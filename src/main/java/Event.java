public class Event extends Task {

    private static final String DELIMITER = " /at ";

    private final String date;


    public Event(String itemString) {
        super(Task.getTaskString(itemString, Event.DELIMITER));
        this.date = Task.getDateString(itemString, Event.DELIMITER);
    }


    @Override
    public String toString() {
        char stateSymbol = this.isDone ? DONE : NOT_DONE;
        return String.format("[D][%s] %s (at: %s)", stateSymbol, this.itemString, this.date);
    }

}
