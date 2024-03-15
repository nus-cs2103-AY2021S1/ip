/**
 * Task class that represents a task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor that creates a task object that has a description of the task
     * @param description a String representing the name of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor that creates a task object that has a description of the task
     * and a boolean that shows whether a task has been completed
     * @param description a String representing the name of the task
     * @param isDone a boolean representing whether the task has been completed
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getIcon() {
        return (isDone ? "[✓]" : "[✗]");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDoneString() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    public abstract String toSaveString();

    @Override
    public String toString() {
        return String.format("%s %s", this.getIcon(), description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else {
            Task task = (Task) o;
            return this.description.equals(task.description);
        }
    }
}
