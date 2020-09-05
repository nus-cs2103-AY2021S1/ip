package duke.tasks;


/**
 * Todo class.
 * Subclass of Task.
 * Task without any dates attached to it.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task.
     *
     * @param itemString Description string.
     */
    public Todo(String itemString) {
        super(itemString);
    }


    /**
     * Creates a new Todo task.
     *
     * @param itemString Description string.
     * @param isDone     True if task is done, false otherwise.
     */
    public Todo(String itemString, boolean isDone) {
        super(itemString, isDone);
    }


    /**
     * Gets string array for storage.
     *
     * @return String array for storage.
     */
    @Override
    public String[] toStorageStringArr() {
        return new String[]{"T", this.isDone() ? "1" : "0", this.getItemString()};
    }


    @Override
    public String toString() {
        char stateSymbol = this.isDone() ? DONE : NOT_DONE;
        return String.format("[T][%s] %s", stateSymbol, this.getItemString());
    }

}
