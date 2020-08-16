public abstract class Task {

    static final char DONE = '\u2713';
    static final char NOT_DONE = '\u2717';

    final String itemString;
    boolean isDone;


    public Task(String itemString) {
        this.itemString = itemString;
        this.isDone = false;
    }


    /**
     * Splits the itemString by the delimiter and returns the task portion.
     *
     * @param taskString Item String.
     * @param delimiter  Delimiter used.
     * @return Task portion of the string.
     */
    static String getTaskString(String taskString, String delimiter) {
        return taskString.split(delimiter)[0];
    }


    /**
     * Splits the taskString by the delimiter and returns the Date portion.
     *
     * @param taskString Item String.
     * @param delimiter  Delimiter used.
     * @return Date portion of the string.
     */
    static String getDateString(String taskString, String delimiter) {
        return taskString.split(delimiter)[1];
    }


    /**
     * Mark this item as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }


    @Override
    public String toString() {
        char stateSymbol = this.isDone ? DONE : NOT_DONE;
        return String.format("[%s] %s", stateSymbol, this.itemString);
    }

}
