package duke;

import java.util.Arrays;

/**
 * Handles maintenance and display of duke.Task objects.
 */

public class Task {
    /** Separator for task details */
    private static final String SAVE_DELIMITER = "|";
    /** Escaped version of separator */
    private static final String ESCAPED_SAVE_DELIMITER = "\\|";

    /** Name of task */
    protected String name;

    /** Completion state of task */
    protected boolean isCompleted;

    /** Tags associated with task */
    protected String[] tags;

    /**
     * Constructor for Tasks.
     * @param name Description of duke.Task.
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Constructor for Task with tags.
     * @param name Description of duke.Task.
     * @param tags Tags associated with Task.
     */
    public Task(String name, String[] tags) {
        this.name = name;
        this.isCompleted = false;
        this.tags = tags;
    }

    /**
     * Constructor for Tasks with completed state.
     * @param name Description of duke.Task.
     * @param isCompleted Completion state of duke.Task.
     */
    public Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    /**
     * Constructor for Task with completed state and tags.
     * @param name Description of duke.Task.
     * @param isCompleted Completion state of duke.Task.
     * @param tags Tags associated with Task.
     */
    public Task(String name, boolean isCompleted, String[] tags) {
        this.name = name;
        this.isCompleted = isCompleted;
        this.tags = tags;
    }

    /**
     * Accessor for SAVE_DELIMITER.
     * @return SAVE_DELIMITER.
     */
    public static String getDelimiter() {
        return SAVE_DELIMITER;
    }

    /**
     * Accessor for ESCAPED_SAVE_DELIMITER.
     * @return ESCAPED_SAVE_DELIMITER.
     */
    public static String getEscapedSaveDelimiter() {
        return ESCAPED_SAVE_DELIMITER;
    }

    /**
     * Marks duke.Task as complete.
     */
    public void complete() {
        this.isCompleted = true;
    }

    /**
     * Gets appropriate status icon based on completion state of the duke.Task.
     * @return Status icon.
     */
    public String getStatusIcon() {
        return this.isCompleted ? "\u2713" : "\u2718";
    }

    /**
     * Represents duke.Task in format to be saved.
     * @return Saved representation of duke.Task object.
     */
    public String format() {
        String base = this.name + SAVE_DELIMITER + this.isCompleted;
        if (tags == null || tags.length == 0) {
            return base;
        } else {
            return base + SAVE_DELIMITER + Arrays.stream(tags).reduce((a, b) -> a + "," + b).orElse("");
        }
    }

    /**
     * Displays the list of tags for the task.
     * @return List of tags.
     */
    public String showTags() {
        if (tags == null || tags.length == 0) {
            return "";
        }
        System.out.println(Arrays.toString(tags));
        return "#" + Arrays.stream(tags).reduce((a, b) -> a + " #" + b).orElse("");
    }

    /**
     * Represents duke.Task in String form.
     * @return String representation of duke.Task object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.name + " " + showTags();
    }
}
