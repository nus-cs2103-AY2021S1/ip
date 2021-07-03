package botbot.tasks;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Represents a task with a description and completion status.
 */
public abstract class Task {
    private final char type;
    private String description;
    private Optional<LocalDateTime> at;
    private Optional<LocalDateTime> by;
    private TaskStatus status;

    /**
     * Creates a task.
     *
     * @param type Type of task.
     * @param description Description of task.
     */
    public Task(char type, String description) {
        this.type = type;
        this.description = description;
        this.at = Optional.empty();
        this.by = Optional.empty();
        status = TaskStatus.NOT_DONE;
    }

    /**
     * Creates a task.
     *
     * @param type Type of task.
     * @param description Description of task
     * @param at Time of task.
     * @param by Deadline of task.
     */
    public Task(char type, String description, LocalDateTime at, LocalDateTime by) {
        this.type = type;
        this.description = description;
        this.at = Optional.ofNullable(at);
        this.by = Optional.ofNullable(by);
        assert this.at.isPresent() ^ this.by.isPresent() : "'at' and 'by' are both present or both absent";
        status = TaskStatus.NOT_DONE;
    }

    /**
     * Creates a task.
     *
     * @param type Type of task.
     * @param description Description of task.
     * @param status Completion status of task.
     */
    public Task(char type, String description, TaskStatus status) {
        this.type = type;
        this.description = description;
        this.at = Optional.empty();
        this.by = Optional.empty();
        this.status = status;
    }

    /**
     * Creates a task.
     *
     * @param type Type of task.
     * @param description Description of task.
     * @param at Time of task.
     * @param by Deadline of task.
     * @param status Completion status of task.
     */
    public Task(char type, String description, LocalDateTime at, LocalDateTime by, TaskStatus status) {
        this.type = type;
        this.description = description;
        this.at = Optional.ofNullable(at);
        this.by = Optional.ofNullable(by);
        assert this.at.isPresent() ^ this.by.isPresent() : "'at' and 'by' are both present or both absent";
        this.status = status;
    }

    /**
     * Returns the type of the task.
     *
     * @return Type of task.
     */
    public char getType() {
        assert type == Deadline.TYPE_CODE || type == Event.TYPE_CODE || type == Todo.TYPE_CODE
                : "Task type not D, E or T";
        return type;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        assert description != null : "Empty description";
        return description;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return 1 if task is done, 0 otherwise.
     */
    public String getStatus() {
        return status.getStrValue();
    }

    /**
     * Returns the icon representing the completion status of the task.
     *
     * @return Tick if task is done, cross otherwise.
     */
    String getStatusIcon() {
        return status.getStatusIcon();
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        status = TaskStatus.DONE;
    }

    /**
     * Returns the time of the task.
     *
     * @return Time of task.
     */
    public LocalDateTime getAt() {
        return at.orElse(null);
    }

    /**
     * Returns the deadline of the task.
     *
     * @return Deadline of task.
     */
    public LocalDateTime getBy() {
        return by.orElse(null);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAt(LocalDateTime at) {
        this.at = Optional.ofNullable(at);
    }

    public void setBy(LocalDateTime by) {
        this.by = Optional.ofNullable(by);
    }
}
