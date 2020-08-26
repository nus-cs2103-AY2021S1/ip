package duke.task;

import java.util.HashMap;

/**
 * Representation of a task stored in the app.
 */
public class Task {

    /**
     * Name of task.
     */
    private String name;

    /**
     * Indicates if task is done.
     */
    private boolean isDone;

    /**
     * Creates an empty <code>Task</code>.
     */
    public Task() {
        this.name = "";
        this.isDone = false;
    }

    /**
     * Creates an initialised <code>Task</code> with a name.
     *
     * @param name Name of task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Creates a fully detailed <code>Task</code>.
     * For use to create <code>Task</code> from save file.
     *
     * @param name Name of task.
     * @param isDone Whether task is done.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Sets <code>Task</code> as done.
     */
    public void doTask() {
        this.isDone = true;
    }

    /**
     * Converts <code>Task</code> to <code>HashMap</code> representation.
     * Used for further processing to save file string.
     *
     * @return HashMap representation of properties.
     */
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> dict = new HashMap<>();
        dict.put("type", "duke.task.Task");
        dict.put("name", this.name);
        dict.put("done", this.isDone ? "true" : "false");
        return dict;
    }

    /**
     * Returns String formatted for representation of task for display.
     *
     * @return Formatted String.
     */
    public String toString() {

        String checkMark;

        // Print tick if done and cross if not done
        if (this.isDone) {
            checkMark = "\u2713";
        } else {
            checkMark = "\u2718";
        }

        return "[" + checkMark + "] " + this.name;
    }
}
