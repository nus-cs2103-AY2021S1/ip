package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import duke.tag.Tag;

/** Task */
public class Task {
    private static final String DONE = "[x]";
    private static final String NOT_DONE = "[ ]";

    private final String name;
    private final TaskType taskType;
    private final Optional<LocalDate> date;
    private final List<Tag> tags = new ArrayList<>();
    private boolean isDone;

    /**
     * Creates a task with a date, date set to Optional.empty
     * @param name Name of the task
     * @param type Type of the task
     */
    public Task(String name, TaskType type) {
        this.name = name;
        this.isDone = false;
        this.taskType = type;
        this.date = Optional.empty();
    }

    /**
     * Creates a task with a date
     * @param name Name of the task
     * @param type Type of the task
     * @param date Date of the task
     */
    public Task(String name, TaskType type, LocalDate date) {
        this.name = name;
        this.isDone = false;
        this.taskType = type;
        this.date = Optional.of(date);
    }

    /**
     * Adds a tag to the task's list of tags
     *
     * @param tag to be added
     */
    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    /**
     * Returns the list of tags of the task in string format
     *
     * @return the list of tags of the task in string format
     */
    public String getTagsString() {
        return String.join(", ", this.tags.stream().map(tag -> tag.toString()).collect(Collectors.toList()));
    }

    /**
     * Returns the name of the task
     *
     * @return the name of the task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a String that show the status of the task
     *
     * @return String that shows the status of the task
     */
    public String getStatus() {
        return this.isDone ? Task.DONE : Task.NOT_DONE;
    }

    /**
     * Returns an Optional object containing the date associated with the task
     *
     * @return an Optional object containing the date associated with the task
     */
    public Optional<LocalDate> getDate() {
        return this.date;
    }

    /**
     * Sets the task as done or not done
     *
     * @param isDone a boolean that determines if the task is done (true if done, false if not done)
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        if (this.tags.isEmpty()) {
            return String.format("%s%s %s", this.taskType, this.getStatus(), this.name);
        }

        return String.format("%s%s %s (tags: %s)", this.taskType, this.getStatus(), this.name, this.getTagsString());
    }
}
