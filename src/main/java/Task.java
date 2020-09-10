import java.util.ArrayList;

/**
 * Task represents a task to be done.
 */
public abstract class Task {
    String taskName;
    boolean isDone;
    ArrayList<String> tags;

    Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Add a tag to the task.
     */
    public void addTag(String tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    /**
     * Remove a tag from the task.
     */
    public void removeTag(String tag) {
        tags.remove(tag);
    }

    /**
     * Mark the task as done.
     */
    public void setDone() {
        assert !isDone;
        this.isDone = true;
    }

    /**
     * Returns a ticked check-box if done else cross
     */
    public String toString() {
        return (this.isDone ? "[✓] " : "[✘] ") + this.taskName;
    }

    public String tagsFileFormat() {
        return String.join("#", tags);
    }

    /**
     * Return true if task name contains keyword else false
     */
    public boolean containsKeyword(String keyword) {
        assert !keyword.isEmpty();
        return this.taskName.contains(keyword);
    }

    public abstract String toFileFormat();
}
