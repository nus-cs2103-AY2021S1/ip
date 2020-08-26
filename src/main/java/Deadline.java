public class Deadline extends Task {

    private static final String DELIMITER = " /by ";

    private final String date;


    public Deadline(String itemString) {
        super(Task.getTaskString(itemString, Deadline.DELIMITER));
        this.date = Task.getDateString(itemString, Deadline.DELIMITER);
    }


    public Deadline(String itemString, boolean isDone) {
        super(Task.getTaskString(itemString, Deadline.DELIMITER), isDone);
        this.date = Task.getDateString(itemString, Deadline.DELIMITER);
    }


    /**
     * Gets string array for storage.
     *
     * @return string array for storage.
     */
    @Override
    public String[] toStorageStringArr() {
        return new String[]{"D", this.isDone ? "1" : "0", this.itemString, this.date};
    }


    @Override
    public String toString() {
        char stateSymbol = this.isDone ? DONE : NOT_DONE;
        return String.format("[D][%s] %s (by: %s)", stateSymbol, this.itemString, this.date);
    }

}
