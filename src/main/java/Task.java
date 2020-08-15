public class Task {

    private static final String DONE = "✓";
    private static final String NOT_DONE = "✗";

    private final String itemString;
    private boolean isDone;


    public Task(String itemString) {
        this.itemString = itemString;
        this.isDone = false;
    }


    /**
     * Mark this item as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }


    @Override
    public String toString() {
        String stateSymbol = this.isDone ? DONE : NOT_DONE;
        return String.format("[%s] %s", stateSymbol, this.itemString);
    }

}
