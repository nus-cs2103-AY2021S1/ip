public class Deadline extends Task {

    private static final String DELIMITER = " /by ";

    private final String date;


    public Deadline(String itemString) {
        super(Task.getTaskString(itemString, Deadline.DELIMITER));
        this.date = Task.getDateString(itemString, Deadline.DELIMITER);
    }

    // private static


    @Override
    public String toString() {
        char stateSymbol = this.isDone ? DONE : NOT_DONE;
        return String.format("[D][%s] %s (by: %s)", stateSymbol, this.itemString, this.date);
    }

}
