//A task represents an item in Duke's todoList
//A task consists of:
// 1) Content Description
// 2) Status (isDone or not)

public abstract class Task {
    private String description;
    private boolean isDone;

    //Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //return tick or cross symbol accordingly
    public String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
