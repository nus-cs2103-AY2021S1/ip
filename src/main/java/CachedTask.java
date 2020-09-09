/**
 * Represents a cached task to be added.
 */
public class CachedTask {
    private Task cachedTask;
    private String command;
    private int index;

    /**
     * Constructor for the cached task.
     * @param cachedTask the task stored.
     * @param command the command to undo.
     * @param index the index of the task stored.
     */
    public CachedTask(Task cachedTask, String command, int index) {
        this.cachedTask = cachedTask;
        this.command = command;
        this.index = index;
    }

    /**
     * Returns the cached task stored.
     * @return Cached task.
     */
    public Task getCachedTask() {
        return cachedTask;
    }

    /**
     * Returns the command to undo.
     * @return String representation of the command to undo.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Returns the index of cached task.
     * @return Index of cached task.
     */
    public int getIndexOfCachedTask() {
        return index;
    }
}
