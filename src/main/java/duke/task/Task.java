package duke.task; 

public class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    protected void toggleIsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String box = this.isDone ? "\u2713" : "\u2718";
        return String.format("[%s] %s", box, this.description);
    }
}



