package duke.task;

/**
 * Represents a general task that has a name and status of completion.
 * More specific task will inherit from this class.
 */
public abstract class Task {
    protected String name;
    protected boolean status;

    public Task(String name) {
        this.name = name;
        this.status = false;
    }

    public boolean isDone() {
        return this.status;
    }

    public void markAsDone() {
        this.status = true;
    }

    public String toString() {
        return "[" + (status ? "✔" : "✘") + "] " + this.name.trim();
    }

    public String getName() {
        return this.name.trim();
    }

    public abstract String convertTxt();

    public abstract String getDate();

}
