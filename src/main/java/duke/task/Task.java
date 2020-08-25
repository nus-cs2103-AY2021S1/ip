package duke.task;

public abstract class Task {
    protected final String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void done() {
        this.isDone = true;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[\u2713] " + this.name;
        } else {
            return "[\u2718] " + this.name;
        }
    }
}