package duke.tasks;


public class Todo extends Task {

    /**
     * Constructor for todo.
     *
     * @param itemString description string.
     */
    public Todo(String itemString) {
        super(itemString);
    }


    /**
     * Constructor for todo.
     *
     * @param itemString description string.
     * @param isDone     whether this task is done.
     */
    public Todo(String itemString, boolean isDone) {
        super(itemString, isDone);
    }


    /**
     * Gets string array for storage.
     *
     * @return string array for storage.
     */
    @Override
    public String[] toStorageStringArr() {
        return new String[]{"T", this.isDone ? "1" : "0", this.itemString};
    }


    @Override
    public String toString() {
        char stateSymbol = this.isDone ? DONE : NOT_DONE;
        return String.format("[T][%s] %s", stateSymbol, this.itemString);
    }

}
