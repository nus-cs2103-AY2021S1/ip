package duke;

/**
 * Represents the statistics about the items managed by the App.
 */
public class Statistics {
    private static final String NUM_TASKS_ADDED = "Number of things I've eaten: ";
    private static final String NUM_TASKS_DELETED = "Number of things I've thrown up: ";
    private static final String NUM_TASKS_DONE = "Number of things I've digested: ";
    private int[] stats;

    /**
     * Creates a Statistics instance.
     */
    public Statistics() {
        this.stats = new int[]{0, 0, 0};
    }

    /**
     * Creates a Statistics instance.
     *
     * @param stats An array of statistics about the items managed by the App
     */
    public Statistics(int[] stats) {
        this.stats = stats;
    }

    /**
     * Lists statistics about the items managed by the App.
     *
     * @return A string representation of statistics about the items managed by the App
     */
    public String getStats() {
        return String.format("%s%d\n%s%d\n%s%d", NUM_TASKS_ADDED, stats[0], NUM_TASKS_DELETED, stats[1],
                NUM_TASKS_DONE, stats[2]);
    }

    /**
     * Increase stats on number of tasks added in App by one.
     */
    public void incrementAddedStats() {
        this.stats[0]++;
    }

    /**
     * Increase stats on number of tasks deleted in App by one.
     */
    public void incrementDeletedStats() {
        this.stats[1]++;
    }

    /**
     * Increase stats on number of tasks done in App by one.
     */
    public void incrementDoneStats() {
        stats[2]++;
    }


}
