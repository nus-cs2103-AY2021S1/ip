package duke.tasks;


import java.time.LocalDate;


/**
 * Todo class.
 * Subclass of Task.
 * Task without any dates attached to it.
 */
public class Todo extends Task implements Comparable<Task> {

    private static final LocalDate DUMMY_DATE = LocalDate.EPOCH;

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
    public LocalDate getDate() {
        return Todo.DUMMY_DATE;
    }


    @Override
    public String toString() {
        char stateSymbol = this.isDone() ? DONE : NOT_DONE;
        return String.format("[T][%s] %s", stateSymbol, this.getItemString());
    }


    @Override
    public int compareTo(Task t) {
        if (t instanceof Todo) {
            return this.getItemString().compareTo(t.getItemString());
        } else {
            // Todos always come before other tasks
            return -1;
        }
    }

}
