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
    private boolean done;

    /**
     * Creates an empty <code>Task</code>.
     */
    public Task() {
        this.name = "";
        this.done = false;
    }

    /**
     * Creates an initialised <code>Task</code> with a name.
     *
     * @param name Name of task.
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * Creates a fully detailed <code>Task</code>.
     * For use to create <code>Task</code> from save file.
     *
     * @param name Name of task.
     * @param done Whether task is done.
     */
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    /**
     * Sets <code>Task</code> as done.
     */
    public void doTask() {
        this.done = true;
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
        dict.put("done", this.done ? "true" : "false");
        return dict;
    }

    /**
     * Returns String formatted for representation of task for display.
     *
     * @return Formatted String.
     */
    public String toString() {
        String check;
        if (this.done) {
            check = "\u2713";
        } else {
            check = "\u2718";
        }
        return "[" + check + "] " + this.name;
    }
}
