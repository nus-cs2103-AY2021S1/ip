package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeInvalidTimeException;

/**
 * Base class for tasks in the chatbot and utilised by the main types of tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected int index;
    protected String tag;
    protected final String done = "[\u2713] ";
    protected final String start = "[\u2718] ";
    protected TaskType type;
    private LocalDateTime time;

    /**
     * Constructor for a new Task, the task is set to incomplete by default.
     *
     * @param description   Activity
     * @param index         Index of task
     */

    public Task(String description, int index) {
        this.description = description;
        isDone = false;
        this.index = index;
    }

    /**
     * Constructor for a new Task, with additional customisation for completion status.
     *
     * @param description   Activity
     * @param index         Index of task
     * @param isOver        Completion status of task
     */

    public Task(String description, int index, boolean isOver) {
        this.description = description;
        isDone = isOver;
        this.index = index;
    }

    /**
     * Constructor for a new Task, with additional customisation for completion status.
     *
     * @param description   Activity
     * @param index         Index of task
     * @param isOver        Completion status of task
     * @param tag           Tag of the task
     */

    public Task(String description, int index, boolean isOver, String tag) {
        this.description = description;
        isDone = isOver;
        this.index = index;
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean hasDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public TaskType getType() {
        return type;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(int idx) throws DukeInvalidTimeException {
        try {
            time = LocalDateTime.parse(description.substring(idx + 4, idx + 20),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new DukeInvalidTimeException();
        }
    }
    public String getIcon(boolean isDone) {
        return isDone ? done : start;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Converts "N" (tasks with no tag) to space for representation.
     * @return String - empty
     */
    public String convert() {
        return tag.equals("N") ? "" : tag;
    }
    /**
     * Returns the text version of task with index.
     *
     * @return String representation of tasks with indexing.
     */

    public String getStatusWithIndex() {
        return String.format("%s. %s", index, toString());
    }

    /**
     * Default toString() method for tasks.
     *
     * @return String representation of tasks.
     */

    public String toString() {
        return String.format("%s%s", isDone ? done : start, description);
    }
}
