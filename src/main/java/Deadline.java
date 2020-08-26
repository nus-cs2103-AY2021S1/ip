import java.time.LocalDate;


public class Deadline extends Task {

    private static final String DELIMITER = " /by ";

    private final LocalDate date;


    public Deadline(String itemString) {
        super(Task.getTaskString(itemString, Deadline.DELIMITER));
        this.date = LocalDate.parse(Task.getDateString(itemString, Deadline.DELIMITER));
    }

    // private static


    @Override
    public String toString() {
        char stateSymbol = this.isDone ? DONE : NOT_DONE;
        String dateString = Task.formatDateString(this.date);
        return String.format("[D][%s] %s (by: %s)", stateSymbol, this.itemString, dateString);
    }

}
