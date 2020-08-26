/**
 * Contains task description and completion status of task.
 **/
public class Task {
    protected String description;
    protected boolean isDone;

    //Represents task initials as constants
    public enum TaskType {
        TODO("T"), DEADLINE("D"), EVENT("E");

        private final String initial;

        TaskType(String initial) {
            this.initial = initial;
        }

        public String getInitial() {
            return initial;
        }
    }

    /**
     * Constructor for Task.
     *
     * @param description Task description
     **/
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Retrieves status and returns icon corresponding to status.
     *
     * @return Icon corresponding to status.
     **/
    public String getStatusIcon() {
        return (isDone ? "✓" : "X"); //return tick or X symbols
    }

    /**
     * Gets task description.
     *
     * @return Task description.
     **/
    public String getDescription() {
        return description;
    }

    /**
     * Marks task as done.
     **/
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Checks if task is done.
     *
     * @return If Task is Done.
     **/
    public boolean isDone() {
        return isDone;
    }

    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
